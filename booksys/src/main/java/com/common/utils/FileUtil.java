/**
 * 
 */
package com.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liuyang
 * @date 2017年7月10日 下午7:31:36
 */
public class FileUtil {
	public static List<String> getAllFiles(String path){
		File[] fileList = new File(path).listFiles();
		List<String> fileNameList = new ArrayList<String>();
		for(File file:fileList){
			fileNameList.add(file.getName());
		}
		return fileNameList;
		
	}
	
	public static boolean downloadFile(String file, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/x-msdownload");  
		try{
			request.setCharacterEncoding("UTF-8");
	        String path = request.getSession().getServletContext().getRealPath("/upload/");
	        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file, "UTF-8"));  
	        OutputStream out = response.getOutputStream();  
	        String fileName =path + file;
	        InputStream in = new FileInputStream(fileName);  
	        byte [] buffer = new byte[1024];  
	        int len = 0;  
	          
	        while((len = in.read(buffer)) != -1){  
	            out.write(buffer, 0, len);  
	        }  
	        in.close(); 
	        return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static String getContextPath(HttpServletRequest request){
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath;
	}
}
