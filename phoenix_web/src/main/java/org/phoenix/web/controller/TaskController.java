package org.phoenix.web.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.phoenix.web.dto.AjaxObj;
import org.phoenix.web.dto.TaskType;
import org.phoenix.web.filter.InitServlet;
import org.phoenix.web.model.TaskModel;
import org.phoenix.web.model.User;
import org.phoenix.web.service.ITaskService;
import org.phoenix.web.util.EnumUtils;
import org.phoenix.web.util.HttpRequestSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/task")
public class TaskController {
	private ITaskService taskService;
	
	public ITaskService getTaskService() {
		return taskService;
	}
    @Resource
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public TaskController() {
	}
	
	@RequestMapping("/list")
	public String list(Model model,HttpSession httpSession){
		User u = (User)httpSession.getAttribute("loginUser");
		model.addAttribute("datas",taskService.getTaskModelPagerByUser(u.getId()));
		return "task/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("types", EnumUtils.enumProp2NameMap(TaskType.class, "name"));
		model.addAttribute(new TaskModel());
		return "task/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid TaskModel taskModel,BindingResult br,HttpSession httpSession){
		if(br.hasErrors()){
			return "task/add";
		}
		User user = (User)httpSession.getAttribute("loginUser");
		taskModel.setUser(user);
		taskService.add(taskModel);
		return "redirect:/task/list";
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Integer id){
		taskService.delete(id);
		return "redirect:/task/list";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Integer id,Model model){
		model.addAttribute("types", EnumUtils.enumProp2NameMap(TaskType.class, "name"));
		model.addAttribute(taskService.getTaskModel(id));
		return "task/edit";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Integer id,@Valid TaskModel taskModel,BindingResult br,Model model){
		if(br.hasErrors()){
			return "task/edit";
		}
		TaskModel taskModelSrc = taskService.getTaskModel(id);
		taskModelSrc.setSlaveIP(taskModel.getSlaveIP());
		taskModelSrc.setTaskName(taskModel.getTaskName());
		taskModelSrc.setTaskParameter(taskModel.getTaskParameter());
		taskModelSrc.setTaskStatusType(taskModel.getTaskStatusType());
		taskModelSrc.setTaskType(taskModel.getTaskType());
		taskService.update(taskModelSrc);
		return "redirect:/task/list";
	}
	
	@RequestMapping(value="/start/{id}",method=RequestMethod.POST)
	public @ResponseBody AjaxObj start(@PathVariable Integer id){
		TaskModel taskModel = taskService.getTaskModel(id);
		String hostIP = taskModel.getSlaveIP();
		int taskId = taskModel.getId();
		
		final String  url = "http://"+hostIP+"/phoenix_node/action.do?taskId="+taskId+"&taskType="+taskModel.getTaskType();

			new Thread(){
				public void run(){
					try {
						HttpRequestSender.getResponseByPost(url);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		return new AjaxObj(1);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/stop/{id}")
	public @ResponseBody AjaxObj stop(@PathVariable Integer id){
        HashMap<String,String> hm = (HashMap<String, String>) InitServlet.getWc().getBean("constants");
        System.out.println(hm.toString());
		
		return new AjaxObj(1);
	}
}
