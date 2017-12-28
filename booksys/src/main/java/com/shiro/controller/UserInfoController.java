/**
 * 
 */
package com.shiro.controller;

import com.shiro.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @date 2017年7月13日 下午5:20:41
 */
@Controller
@RequestMapping(value = "/user/info")
public class UserInfoController {

//    @Autowired
//    private UserInfoService userInfoService;

    @RequestMapping(value = "/login")
    public String login(UserInfo userInfo, Model model) {
        if (SecurityUtils.getSubject().getSession() != null)
            SecurityUtils.getSubject().logout();

        String info = loginUser(userInfo);
        if (!"SUCC".equals(info)) {
            model.addAttribute("msg", info);
            return "error";
        }
        model.addAttribute("account", userInfo.getAccount());
        // 这里使用重定向，是为了避免浏览器一直地址栏定位在登录方法，这样每次刷新页面就等于是重新登录了一次
        return "redirect:/jsp/success.jsp";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/user/info/toLogin";
    }

    private String loginUser(UserInfo user) {
        if (isRelogin(user)) return "SUCC";// 如果已经登录，则无需再登录

        return shiroLogin(user);
    }

    private String shiroLogin(UserInfo user) {
        // 组装 token，包括用户名、密码、角色、权限等等
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword().toCharArray(), null);
        token.setRememberMe(true);

        // shiro 验证登录
        try {
            SecurityUtils.getSubject().login(token);;
        } catch (UnknownAccountException ex){
            return "帐号或密码错误";
        } catch (IncorrectCredentialsException ex){
            return "帐号不存在或者密码错误";
        } catch (AuthenticationException ex) {
            return ex.getMessage();
        } catch (Exception e) {
            return "内部错误，请重新尝试";
        }

        return "SUCC";
    }

    private boolean isRelogin(UserInfo user) {
        Subject subject = SecurityUtils.getSubject();

        return subject.isAuthenticated();// true：参数未改变，无需重新登录，默认为已经登录成功；false：需重新登录
    }
    
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/admin/login";
    }

}