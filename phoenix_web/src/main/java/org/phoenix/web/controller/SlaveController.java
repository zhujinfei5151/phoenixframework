package org.phoenix.web.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.phoenix.web.model.SlaveModel;
import org.phoenix.web.service.ISlaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 节点控制器
 * @author mengfeiyang
 *
 */
@Controller
@RequestMapping("/slave")
public class SlaveController {
	private ISlaveService slaveService;

	public ISlaveService getSlaveService() {
		return slaveService;
	}
    @Resource
	public void setSlaveService(ISlaveService slaveService) {
		this.slaveService = slaveService;
	}
    
    @RequestMapping("/list")
    public String list(Model model){
    	model.addAttribute("datas",slaveService.getSlaveModelPager());
    	return "slave/list";
    }
    
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String add(Model model){
    	model.addAttribute(new SlaveModel());
    	return "slave/add";
    }
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String add(@Valid SlaveModel slaveModel,BindingResult br){
    	if(br.hasErrors()){
    		return "slave/add";
    	}
    	
    	slaveService.add(slaveModel);
    	return "redirect:/slave/list";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
    	slaveService.delete(id);
    	return "redirect:/slave/list";
    }
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)
    public String update(@PathVariable Integer id,Model model){
    	model.addAttribute(slaveService.getModel(id));
    	return "slave/edit";
    }
    
    @RequestMapping(value="/update/{id}",method=RequestMethod.POST)
    public String update(@PathVariable Integer id,@Valid SlaveModel slaveModel,BindingResult br){
    	if(br.hasErrors()){
    		return "slave/edit";
    	}
    	SlaveModel slaveModelSrc = slaveService.getModel(id);
    	slaveModelSrc.setRemark(slaveModel.getRemark());
    	slaveModelSrc.setSlaveIP(slaveModel.getSlaveIP());
    	slaveService.update(slaveModelSrc);
    	return "redirect:/slave/list";
    }
}
