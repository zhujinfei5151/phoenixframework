package org.phoenix.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.phoenix.web.filter.PhoenixSessionContext;
import org.phoenix.web.model.User;
import org.phoenix.web.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}
	
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "public/login";  //对应admin文件夹下的/login.jsp文件.
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,Model model,HttpSession session) {
		User loginUser = userService.login(username, password);
		session.setAttribute("loginUser", loginUser);
		if(loginUser != null){			
			boolean isAdmin = loginUser.getRole() == 0;
			session.setAttribute("isAdmin", isAdmin);
			PhoenixSessionContext.addSessoin(session);
			return "redirect:/index";
		} else {
			model.addAttribute("errorInfo", "用户名或密码不正确，请重新输入");
			return "public/login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		PhoenixSessionContext.removeSession(session);
		session.invalidate();
		return "redirect:/index";
	}
}
