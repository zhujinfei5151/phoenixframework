package org.phoenix.web.controller;

import javax.servlet.http.HttpSession;

import org.phoenix.web.auth.AuthClass;
import org.phoenix.web.auth.AuthMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AuthClass("login")
@RequestMapping("/auth")
public class AuthController {
	
	/**
	 * 权限管理的临时替代方案,不过此种方法较简单且易维护
	 */
	@AuthMethod
	@RequestMapping(value="/menu",method=RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public @ResponseBody String getAuth(HttpSession session) {
    	String MENU_ADMIN = "["+
    	        "{"+
    	    	        "\"id\": \"1\","+
    	    	        "\"homePage\": \"1\","+
    	    	        "\"menu\": ["+
    	    	            "{"+
    	    	                "\"text\": \"系统管理\","+
    	    	                "\"items\": ["+
    	    	                    "{"+
    	    	                        "\"id\": \"1\","+
    	    	                        "\"text\": \"版本说明\","+
    	    	                        "\"href\": \"index/welcome\""+
    	    	                    "},"+
    	    	                    "{"+
		    	                        "\"id\": \"2\","+
		    	                        "\"text\": \"用户管理\","+
		    	                        "\"href\": \"user/list\""+
	    	                         "},"+
	    	                         "{"+
		    	                        "\"id\": \"3\","+
		    	                        "\"text\": \"我的信息\","+
		    	                        "\"href\": \"user/self\""+
    	                             "}"+
    	    	                "]"+
    	    	            "}"+
    	    	        "]"+
    	    	    "},"+
    	    	    "{"+
    	    	        "\"id\": \"2\","+
    	    	        "\"homePage\": \"1\","+
    	    	        "\"menu\": ["+
    	    	            "{"+
    	    	                "\"text\": \"业务管理\","+
    	    	                "\"items\": ["+
    	    	                    "{"+
    	    	                        "\"id\": \"1\","+
    	    	                        "\"text\": \"场景管理\","+
    	    	                        "\"href\": \"scenario/list\""+
    	    	                    "},"+
    	    	                    "{"+
		    	                        "\"id\": \"2\","+
		    	                        "\"text\": \"用例管理\","+
		    	                        "\"href\": \"case/list\""+
	    	                         "}"+
    	    	                "]"+
    	    	            "},"+
    	    	            "{"+
    	    	                "\"text\": \"任务管理\","+
    	    	                "\"items\": ["+
    	    	                    "{"+
    	    	                        "\"id\": \"3\","+
    	    	                        "\"text\": \"任务分配\","+
    	    	                        "\"href\": \"task/list\""+
    	    	                    "},"+
    	    	                    "{"+
		    	                        "\"id\": \"4\","+
		    	                        "\"text\": \"任务监控\","+
		    	                        "\"href\": \"task/list\""+
	    	                        "},"+
    	    	                    "{"+
		    	                        "\"id\": \"5\","+
		    	                        "\"text\": \"执行机状态\","+
		    	                        "\"href\": \"slave/status\""+
	    	                        "},"+ 
		    	                    "{"+
		    	                        "\"id\": \"6\","+
		    	                        "\"text\": \"执行机管理\","+
		    	                        "\"href\": \"slave/list\""+
		    	                    "}"+
    	    	                "]"+
    	    	            "},"+
    	    	            "{"+
    	    	                "\"text\": \"日志管理\","+
    	    	                "\"items\": ["+
    	    	                    "{"+
    	    	                        "\"id\": \"6\","+
    	    	                        "\"text\": \"日志查看\","+
    	    	                        "\"href\": \"log/batchlist\""+
    	    	                    "},"+
    	    	                    "{"+
		    	                        "\"id\": \"7\","+
		    	                        "\"text\": \"用例统计图\","+
		    	                        "\"href\": \"chart/WEB_CASE\""+
	    	                        "}"+
    	    	                "]"+
    	    	            "}"+
    	    	        "]"+
    	    	    "}"+
    	    	"]";
    	String MENU_USER = "["+
    	        "{"+
    	    	        "\"id\": \"1\","+
    	    	        "\"homePage\": \"1\","+
    	    	        "\"menu\": ["+
    	    	            "{"+
    	    	                "\"text\": \"系统管理\","+
    	    	                "\"items\": ["+
    	    	                    "{"+
    	    	                        "\"id\": \"1\","+
    	    	                        "\"text\": \"版本说明\","+
    	    	                        "\"href\": \"index/welcome\""+
    	    	                    "},"+
	    	                         "{"+
		    	                        "\"id\": \"2\","+
		    	                        "\"text\": \"我的信息\","+
		    	                        "\"href\": \"user/self\""+
    	                             "}"+
    	    	                "]"+
    	    	            "}"+
    	    	        "]"+
    	    	    "},"+
    	    	    "{"+
    	    	        "\"id\": \"2\","+
    	    	        "\"homePage\": \"1\","+
    	    	        "\"menu\": ["+
    	    	            "{"+
    	    	                "\"text\": \"业务管理\","+
    	    	                "\"items\": ["+
    	    	                    "{"+
    	    	                        "\"id\": \"1\","+
    	    	                        "\"text\": \"场景管理\","+
    	    	                        "\"href\": \"scenario/list\""+
    	    	                    "},"+
    	    	                    "{"+
		    	                        "\"id\": \"2\","+
		    	                        "\"text\": \"用例管理\","+
		    	                        "\"href\": \"case/list\""+
	    	                         "}"+
    	    	                "]"+
    	    	            "},"+
    	    	            "{"+
    	    	                "\"text\": \"任务管理\","+
    	    	                "\"items\": ["+
    	    	                    "{"+
    	    	                        "\"id\": \"3\","+
    	    	                        "\"text\": \"任务分配\","+
    	    	                        "\"href\": \"task/list\""+
    	    	                    "},"+
    	    	                    "{"+
		    	                        "\"id\": \"4\","+
		    	                        "\"text\": \"任务监控\","+
		    	                        "\"href\": \"task/list\""+
	    	                        "},"+
    	    	                    "{"+
		    	                        "\"id\": \"5\","+
		    	                        "\"text\": \"执行机状态\","+
		    	                        "\"href\": \"slave/status\""+
	    	                        "},"+  
	    	                        "{"+
		    	                        "\"id\": \"6\","+
		    	                        "\"text\": \"执行机管理\","+
		    	                        "\"href\": \"slave/list\""+
		    	                   "}"+
    	    	                "]"+
    	    	            "},"+
    	    	            "{"+
    	    	                "\"text\": \"日志管理\","+
    	    	                "\"items\": ["+
    	    	                    "{"+
    	    	                        "\"id\": \"6\","+
    	    	                        "\"text\": \"日志查看\","+
    	    	                        "\"href\": \"log/batchlist\""+
    	    	                    "},"+
    	    	                    "{"+
		    	                        "\"id\": \"7\","+
		    	                        "\"text\": \"用例统计图\","+
		    	                        "\"href\": \"chart/WEB_CASE\""+
	    	                        "}"+
    	    	                "]"+
    	    	            "}"+
    	    	        "]"+
    	    	    "}"+
    	    	"]";
    	String MENU_NORMAL = "["+
    	        "{"+
    	    	        "\"id\": \"1\","+
    	    	        "\"homePage\": \"1\","+
    	    	        "\"menu\": ["+
    	    	            "{"+
    	    	                "\"text\": \"系统管理\","+
    	    	                "\"items\": ["+
    	    	                    "{"+
    	    	                        "\"id\": \"1\","+
    	    	                        "\"text\": \"版本说明\","+
    	    	                        "\"href\": \"index/welcome\""+
    	    	                    "}"+
    	    	                "]"+
    	    	            "}"+
    	    	        "]"+
    	    	    "},"+
    	    	    "{"+
    	    	        "\"id\": \"2\","+
    	    	        "\"homePage\": \"1\","+
    	    	        "\"menu\": ["+
    	    	            "{"+
    	    	                "\"text\": \"业务管理\","+
    	    	                "\"items\": ["+
    	    	                    "{"+
    	    	                        "\"id\": \"1\","+
    	    	                        "\"text\": \"版本说明\","+
    	    	                        "\"href\": \"index/welcome\""+
    	    	                    "}"+
    	    	                "]"+
    	    	            "}"+
    	    	        "]"+
    	    	    "}"+
    	    	"]";
    	
    	if(session.getAttribute("isAdmin")==null){
    		return MENU_NORMAL;
    	} else {
    		if((Boolean) session.getAttribute("isAdmin")){
    			return MENU_ADMIN;
    		} else {
    			return MENU_USER;
    		}
    	}
	}
}
