package org.phoenix.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.phoenix.web.auth.AuthClass;
import org.phoenix.web.model.AttachModel;
import org.phoenix.web.model.User;
import org.phoenix.web.service.IAttachService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AuthClass("login")
@RequestMapping("/attach")
public class AttachController {
	private IAttachService attachService;
	
	public IAttachService getAttachService() {
		return attachService;
	}
	@Resource
	public void setAttachService(IAttachService attachService) {
		this.attachService = attachService;
	}

	@RequestMapping("/list")
	public String list(Model model,HttpSession session) throws IOException{
		User u = (User)session.getAttribute("loginUser");
		model.addAttribute("datas", attachService.getAttachPager(u.getId()));
		return "attach/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		System.out.println("=======get add========");
		return "attach/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@RequestParam("attachs")MultipartFile[] attachs,HttpServletRequest req){
		System.out.println("======post add=========");
		String realpath = req.getSession().getServletContext().getRealPath("/resources/upload");
		User u = (User)req.getSession().getAttribute("loginUser");
		for(MultipartFile attach:attachs) {
			if(attach.isEmpty()) continue;
			File f = new File(realpath+"/"+attach.getOriginalFilename());
			try {
				FileUtils.copyInputStreamToFile(attach.getInputStream(),f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			attachService.add(new AttachModel(attach.getOriginalFilename(),"http://localhost:8888/phoenix_web/resources/upload/"+attach.getOriginalFilename(),attach.getContentType(),new Date(),u));
		}
		return "redirect:/attach/list";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Integer id){
		attachService.deleteAttach(id);
		return "redirect:/attach/list";
	}
}
