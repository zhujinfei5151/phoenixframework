package org.phoenix.web.controller;

import javax.servlet.http.HttpSession;

import org.phoenix.web.auth.AuthClass;
import org.phoenix.web.auth.AuthMethod;
import org.phoenix.web.filter.PhoenixSessionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AuthClass("login")
public class AdminController {

	@RequestMapping("/admin")
	@AuthMethod
	public String index() {
		return "admin/index";
	}
	
	@AuthMethod
	@RequestMapping("/admin/logout")
	public String logout(HttpSession session) {
		PhoenixSessionContext.removeSession(session);
		session.invalidate();
		return "redirect:/login";
	}
}
