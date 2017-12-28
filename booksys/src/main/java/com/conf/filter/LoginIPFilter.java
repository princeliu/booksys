package com.conf.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 该过滤器用于过滤非指定列表中的IP不能访问指定功能权限。
 * 
 * @author <a href="mailto:luzhich@cn.ibm.com">Lucas</a>
 * 
 * @version 1.0 2011-11-29
 */
public class LoginIPFilter implements Filter {

	protected Logger log = Logger.getLogger(LoginIPFilter.class);

	// 当访问IP违规时重定向至哪个URL
	private String redirectURL = null;
	// 允许的IP访问列表
	private Set<String> ipList = new HashSet<String>();
	// 不在IP访问列表中的访问者不允许访问的URL列表
	private List<String> protectedURL = new ArrayList<String>();
	private Pattern pattern = Pattern
			.compile("(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\."
					+ "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\."
					+ "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\."
					+ "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})");

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		// 获取请求IP
		InetAddress inet = null;
		String ip = request.getRemoteAddr();
		try {
			inet = InetAddress.getLocalHost();
			if (ip.equals("127.0.0.1"))
				ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		// 获取请求URL
		String reurl = request.getRequestURI();
		String query = request.getQueryString();
		if (query != null && !"".equals(query))
			reurl += query;

		if (reurl.indexOf("/WebMail/") > -1)
			reurl = reurl.replaceFirst("/WebMail/", "");

		if (reurl.indexOf("&") > -1)
			reurl = reurl.substring(0, reurl.indexOf("&"));

		// 对访问的IP进行过滤和URL白名单进行交叉匹配
		// 不在IP白名单里
		if (!checkLoginIP(ip)) {
			// 并且访问URL在控制列表里，跳转
			if (checkRequestURL(reurl)) {
				log.warn("Receive a request which is request IP "
						+ "without allowIPList, and requested "
						+ "a protected URL. detail:" + ip + ", request URL:"
						+ reurl);
				response.sendRedirect(request.getContextPath() + redirectURL);
				return;
			}
		}
		chain.doFilter(arg0, arg1);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		redirectURL = arg0.getInitParameter("RedirectURL");
		String url = arg0.getInitParameter("ProtectedURLList");
		protectedURL = Arrays.asList(url.split(";"));
		String allowIp = arg0.getInitParameter("AllowIPList");
		init(allowIp);

		log.info("Allow IP list:" + ipList);
		log.info("Protected URL:" + protectedURL);
	}

	private void init(String allowIp) {
		for (String allow : allowIp.replaceAll("\\s", "").split(";")) {
			if (allow.indexOf("*") > -1) {
				String[] ips = allow.split("\\.");
				String[] from = new String[] { "0", "0", "0", "0" };
				String[] end = new String[] { "255", "255", "255", "255" };
				List<String> tem = new ArrayList<String>();
				for (int i = 0; i < ips.length; i++)
					if (ips[i].indexOf("*") > -1) {
						tem = complete(ips[i]);
						from[i] = null;
						end[i] = null;
					} else {
						from[i] = ips[i];
						end[i] = ips[i];
					}

				StringBuffer fromIP = new StringBuffer();
				StringBuffer endIP = new StringBuffer();
				for (int i = 0; i < 4; i++)
					if (from[i] != null) {
						fromIP.append(from[i]).append(".");
						endIP.append(end[i]).append(".");
					} else {
						fromIP.append("[*].");
						endIP.append("[*].");
					}
				fromIP.deleteCharAt(fromIP.length() - 1);
				endIP.deleteCharAt(endIP.length() - 1);

				for (String s : tem) {
					String ip = fromIP.toString().replace("[*]",
							s.split(";")[0])
							+ "-"
							+ endIP.toString().replace("[*]", s.split(";")[1]);
					if (validate(ip))
						ipList.add(ip);
				}
			} else {
				if (validate(allow))
					ipList.add(allow);
			}
		}
	}

	/**
	 * 对单个IP节点进行范围限定
	 * 
	 * @param arg
	 * @return 返回限定后的IP范围，格式为List[10;19, 100;199]
	 */
	private List<String> complete(String arg) {
		List<String> com = new ArrayList<String>();
		if (arg.length() == 1) {
			com.add("0;255");
		} else if (arg.length() == 2) {
			String s1 = complete(arg, 1);
			if (s1 != null)
				com.add(s1);
			String s2 = complete(arg, 2);
			if (s2 != null)
				com.add(s2);
		} else {
			String s1 = complete(arg, 1);
			if (s1 != null)
				com.add(s1);
		}
		return com;
	}

	private String complete(String arg, int length) {
		String from = "";
		String end = "";
		if (length == 1) {
			from = arg.replace("*", "0");
			end = arg.replace("*", "9");
		} else {
			from = arg.replace("*", "00");
			end = arg.replace("*", "99");
		}
		if (Integer.valueOf(from) > 255)
			return null;
		if (Integer.valueOf(end) > 255)
			end = "255";
		return from + ";" + end;
	}

	/**
	 * 在添加至白名单时进行格式校验
	 * 
	 * @param ip
	 * @return
	 */
	private boolean validate(String ip) {
		for (String s : ip.split("-"))
			if (!pattern.matcher(s).matches()) {
				return false;
			}
		return true;
	}

	private boolean checkRequestURL(String url) {
		if (protectedURL.isEmpty())
			return false;

		for (String allow : protectedURL) {
			if (allow.indexOf(url) > -1) {
				return true;
			}
		}
		return false;
	}

	private boolean checkLoginIP(String ip) {
		if (ipList.isEmpty() || ipList.contains(ip))
			return true;
		else {
			for (String allow : ipList) {
				log.info("Allow IP list:" + ipList);
				if (allow.indexOf("-") > -1) {
					String[] from = allow.split("-")[0].split("\\.");
					String[] end = allow.split("-")[1].split("\\.");
					String[] tag = ip.split("\\.");
					
					// 对IP从左到右进行逐段匹配
					boolean check = true;
					for (int i = 0; i < 4; i++) {
						int s = Integer.valueOf(from[i]);
						int t = Integer.valueOf(tag[i]);
						int e = Integer.valueOf(end[i]);
						if (!(s <= t && t <= e))
							check = false;
					}
					if (check)
						return true;
				}
			}
		}
		return false;
	}
}