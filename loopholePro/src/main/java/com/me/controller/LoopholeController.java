package com.me.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.Now;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.me.service.CnnvdService;
import com.me.util.DownloadUtil;
/** 
 * @author liujun E-mail: 
 * @version 创建时间：2015年8月15日 下午12:19:40 
 */
@Controller  
@RequestMapping("/spider")  
public class LoopholeController {  
    @RequestMapping(value="/a")  
    public ModelAndView add(HttpServletRequest request,  
            HttpServletResponse response){ 
    	Map<String, Object> map=new HashMap<String, Object>();
  //先要生成xxx文件到       webapp/excelFile
    	
   //在调取下载方法 
    	String storeName="1.jpg";
        String contentType = "application/octet-stream";  
        try {
			DownloadUtil.download(request, response, storeName, contentType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;  
    }  
    //测试velocity功能
    @RequestMapping(value = "c")  
    public ModelAndView index(){
		System.out.println(System.getProperty("rootPath"));
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("name", "fjdslaf");
    	return new ModelAndView("views/index1",map);  
        
    }  
    
    @RequestMapping(value="begincnvd")
    public ModelAndView spidding(){
    	CnnvdService.spideAll();
    	return new ModelAndView("views/index1");
    }
    @RequestMapping(value="download")
    public ModelAndView downLoad(HttpServletRequest request,  
            HttpServletResponse response){
    	String t1=request.getParameter("t1");
    	String t2=request.getParameter("t2");
    	String fileName=null;
    	try {
    		fileName=CnnvdService.download(t1,t2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 String contentType = "application/octet-stream";  
         try {
 			DownloadUtil.download(request, response, fileName, contentType);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         return new ModelAndView("views/index1");
    }
    
 
}  