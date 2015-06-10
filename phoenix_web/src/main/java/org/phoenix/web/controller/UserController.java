package org.phoenix.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.phoenix.web.auth.AuthClass;
import org.phoenix.web.auth.AuthMethod;
import org.phoenix.web.dto.UserDTO;
import org.phoenix.web.model.User;
import org.phoenix.web.service.IScenarioService;
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
	private IScenarioService scenarioService;

	public IUserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IScenarioService getScenarioService() {
		return scenarioService;
	}
    @Resource
	public void setScenarioService(IScenarioService scenarioService) {
		this.scenarioService = scenarioService;
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
		System.out.println(user.getRole());
		user.setRoleName(user.getRole()==0?"管理员":"普通用户");
		user.setCreateDate(new Date());
		userService.add(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Integer id,Model model){
		model.addAttribute(userService.load(id));
		model.addAttribute("userDTO", new UserDTO());
		return "user/edit";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Integer id,@Valid UserDTO userDTO,BindingResult br,Model model) {
		if(br.hasErrors()) {
			return "user/edit";
		}
		User u = userService.load(id);
		u.setNickname(userDTO.getNickname());
		u.setEmail(userDTO.getEmail());
		u.setPassword(userDTO.getPassword());
		u.setUsername(userDTO.getUsername());
		u.setRoleName(userDTO.getRole()==0?"管理员":"普通用户");
		u.setRole(userDTO.getRole());
		userService.update(u);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		scenarioService.deleteByUser(id);
		userService.delete(id);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/self",method=RequestMethod.GET)
	@AuthMethod
	public String self(Model model,HttpSession session) {
		User user = (User)session.getAttribute("loginUser");
		model.addAttribute(user);
		return "user/self";
	}
	@RequestMapping(value="/selfedit",method=RequestMethod.GET)
	@AuthMethod
	public String selfEdit(Model model,HttpSession session) {
		User user = (User)session.getAttribute("loginUser");
		model.addAttribute(user);
		model.addAttribute(new UserDTO());
		return "user/selfedit";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	@AuthMethod
	public String self(@PathVariable Integer id,@Valid UserDTO userDTO,BindingResult br,HttpSession session){
		
		if(br.hasErrors()){
			return "user/selfedit";
		}
		User u = userService.load(id);
		u.setNickname(userDTO.getNickname());
		u.setEmail(userDTO.getEmail());
		u.setPassword(userDTO.getPassword());
		u.setUsername(userDTO.getUsername());
		userService.update(u);
		session.setAttribute("loginUser", u);
		return "redirect:/user/self";
	}

}
