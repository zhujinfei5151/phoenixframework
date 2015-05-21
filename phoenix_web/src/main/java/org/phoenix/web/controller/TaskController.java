package org.phoenix.web.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.phoenix.web.dto.AjaxObj;
import org.phoenix.web.dto.TaskModelDTO;
import org.phoenix.web.dto.TaskStatusType;
import org.phoenix.web.dto.TaskType;
import org.phoenix.web.filter.InitServlet;
import org.phoenix.web.model.SlaveModel;
import org.phoenix.web.model.TaskModel;
import org.phoenix.web.model.User;
import org.phoenix.web.service.ISlaveService;
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

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/task")
public class TaskController {
	private ITaskService taskService;
	private ISlaveService slaveService;
	
	public ITaskService getTaskService() {
		return taskService;
	}
    @Resource
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public ISlaveService getSlaveService() {
		return slaveService;
	}
	@Resource
	public void setSlaveService(ISlaveService slaveService) {
		this.slaveService = slaveService;
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
	public String add(Model model,HttpSession session){
		User u = (User)session.getAttribute("loginUser");
		model.addAttribute("types", EnumUtils.enumProp2NameMap(TaskType.class, "name"));
		model.addAttribute("slaves", slaveService.getSlaveModelList(u.getId()));
		model.addAttribute(new TaskModelDTO());
		return "task/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid TaskModelDTO taskModelDTO,BindingResult br,HttpSession httpSession){
		if(br.hasErrors()){
			return "task/add";
		}
		SlaveModel slaveModel = new SlaveModel();
		slaveModel.setId(taskModelDTO.getSlaveId());
		User user = (User)httpSession.getAttribute("loginUser");
		TaskModel taskModel = new TaskModel();
		taskModel.setUser(user);
		taskModel.setSlaveModel(slaveModel);
		taskModel.setTaskName(taskModelDTO.getTaskName());
		taskModel.setTaskParameter(taskModelDTO.getTaskParameter());
		taskModel.setTaskStatusType(TaskStatusType.NOT_RUNNING);
		taskModel.setTaskType(taskModelDTO.getTaskType());
		taskModel.setTaskData(taskModelDTO.getTaskData());
		taskService.add(taskModel);
		return "redirect:/task/list";
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Integer id){
		taskService.delete(id);
		return "redirect:/task/list";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Integer id,Model model,HttpSession session){
		User user = (User)session.getAttribute("loginUser");
		model.addAttribute("types", EnumUtils.enumProp2NameMap(TaskType.class, "name"));
		model.addAttribute("slaves", slaveService.getSlaveModelList(user.getId()));
		model.addAttribute("taskModel",taskService.getTaskModel(id));
		model.addAttribute("taskModelDTO",new TaskModelDTO());
		return "task/edit";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Integer id,@Valid TaskModelDTO taskModelDTO,BindingResult br,Model model){
		if(br.hasErrors()){
			return "task/edit";
		}
		SlaveModel slaveModel = new SlaveModel();
		slaveModel.setId(taskModelDTO.getSlaveId());
		TaskModel taskModelSrc = taskService.getTaskModel(id);
		taskModelSrc.setSlaveModel(slaveModel);
		taskModelSrc.setTaskName(taskModelDTO.getTaskName());
		taskModelSrc.setTaskParameter(taskModelDTO.getTaskParameter());
		taskModelSrc.setTaskStatusType(taskModelDTO.getTaskStatusType());
		taskModelSrc.setTaskType(taskModelDTO.getTaskType());
		taskModelSrc.setTaskStatusType(TaskStatusType.NOT_RUNNING);
		taskModelSrc.setTaskData(taskModelDTO.getTaskData());
		taskService.update(taskModelSrc);
		return "redirect:/task/list";
	}
	
	@RequestMapping(value="/start/{id}",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public @ResponseBody String start(@PathVariable Integer id){
		TaskModel taskModel = taskService.getTaskModel(id);
		String hostIP = taskModel.getSlaveModel().getSlaveIP();
		int taskId = taskModel.getId();
		
		String url = "http://"+hostIP+"/phoenix_node/action.do?taskId="+taskId+"&taskType="+taskModel.getTaskType();
		try {
			return HttpRequestSender.getResponseByPost(url);
		} catch (Exception e) {
			e.printStackTrace();
			return JSON.toJSONString(new AjaxObj(0,"向执行机分配测试任务时发生异常，信息："+e.getMessage()));
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/stop/{id}")
	public @ResponseBody AjaxObj stop(@PathVariable Integer id){
        HashMap<String,String> hm = (HashMap<String, String>) InitServlet.getWc().getBean("constants");
        System.out.println(hm.toString());
		
		return new AjaxObj(1);
	}
}
