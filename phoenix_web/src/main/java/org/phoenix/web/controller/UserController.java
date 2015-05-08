package org.phoenix.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.phoenix.web.auth.AuthClass;
import org.phoenix.web.auth.AuthMethod;
import org.phoenix.web.model.User;
import org.phoenix.web.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 基于方法的权限控制，即每个被请求的方法都会有权限控制。颗粒度比较细
 * @author mengfeiyang
 *
 */
@Controller
@RequestMapping("/user") 
@AuthClass("login") 
public class UserController {
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/list") 
	public String list(Model model) {
		model.addAttribute("datas",userService.findUser());
		return "user/list"; 
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET) 
	public String add(Model model) {
		model.addAttribute(new User());
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid User user,BindingResult br,Model model) {
		if(br.hasErrors()) {
			return "user/add";
		}
		user.setRoleName(user.getRole()==0?"管理员":"普通用户");
		user.setCreateDate(new Date());
		userService.add(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Integer id,Model model){
		model.addAttribute(userService.load(id));
		model.addAttribute("userDTO", new User());
		return "user/edit";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Valid User user,BindingResult br,Model model) {
		if(br.hasErrors()) {
			return "user/edit";
		}
		User ou = userService.load(id);
		ou.setNickname(user.getNickname());
		ou.setEmail(user.getEmail());
		ou.setPassword(user.getPassword());
		ou.setUsername(user.getUsername());
		ou.setRoleName(user.getRole()==0?"管理员":"普通用户");
		ou.setRole(user.getRole());
		userService.update(ou);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(userService.load(id));
		return "user/show";
	}
	
	@RequestMapping("/showSelf")
	@AuthMethod
	public String showSelf(Model model,HttpSession session) {
		User user = (User)session.getAttribute("loginUser");
		model.addAttribute(user);
		return "user/show";
	}
	
	@RequestMapping(value="/updatePwd",method=RequestMethod.GET)
	@AuthMethod
	public String updatePwd(Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		model.addAttribute(u);
		return "user/updatePwd";
	}
	
	@RequestMapping(value="/update/self",method=RequestMethod.GET)
	@AuthMethod
	public String updateSelf(Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		model.addAttribute(u);
		return "user/updateSelf";
	}
	
	@RequestMapping(value="/update/self",method=RequestMethod.POST)
	@AuthMethod
	public String updateSelf(@Valid User user,BindingResult br,Model model,HttpSession session) {
		if(br.hasErrors()) {
			return "user/updateSelf";
		}
		User ou = userService.load(user.getId());
		ou.setNickname(user.getNickname());
		ou.setEmail(user.getEmail());
		userService.update(ou);
		session.setAttribute("loginUser", ou);
		return "redirect:/admin/user/showSelf";
	}
}
