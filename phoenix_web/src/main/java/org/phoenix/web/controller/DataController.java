package org.phoenix.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.phoenix.model.CaseBean;
import org.phoenix.model.DataBean;
import org.phoenix.web.auth.AuthClass;
import org.phoenix.web.dto.DataDTO;
import org.phoenix.web.service.ICaseService;
import org.phoenix.web.service.IDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/data")
@AuthClass("login")
public class DataController {
	private IDataService dataService;
	private ICaseService caseService;
	
	public IDataService getDataService() {
		return dataService;
	}
	@Resource
	public void setDataService(IDataService dataService) {
		this.dataService = dataService;
	}

	public ICaseService getCaseService() {
		return caseService;
	}
	@Resource
	public void setCaseService(ICaseService caseService) {
		this.caseService = caseService;
	}
	@RequestMapping("/list/{id}")
	public String list(@PathVariable Integer id,Model model){
		model.addAttribute(caseService.getCaseBean(id));
		model.addAttribute("datas", dataService.getDataPager(id));
		return "data/list";
	}
	@RequestMapping(value="/add/{id}",method=RequestMethod.GET)
	public String add(@PathVariable Integer id, Model model,HttpSession session){
		model.addAttribute(new DataDTO());
		model.addAttribute(caseService.getCaseBean(id));
		return "data/add";
	}
	@RequestMapping(value="/add/{id}",method=RequestMethod.POST)
	public String add(@PathVariable Integer id,@Valid DataDTO dataDTO,BindingResult br,Model model){
		if(br.hasErrors()){
			model.addAttribute(caseService.getCaseBean(id));
			return "data/add";
		}
		CaseBean caseBean = new CaseBean();
		DataBean dataBean = new DataBean();
		caseBean.setId(dataDTO.getCaseId());
		dataBean.setCaseBean(caseBean);
		dataBean.setDataContent(dataDTO.getDataContent());
		dataBean.setDataName(dataDTO.getDataName());
		dataService.addData(dataBean);
		return "redirect:/data/list/"+dataDTO.getCaseId();
	}
	
	@RequestMapping(value="/delete/{cid}/{id}")
	public String delete(@PathVariable Integer cid,@PathVariable Integer id){
		dataService.deleData(id);
		return "redirect:/data/list/"+cid;
	}
	
	@RequestMapping(value="/update/{cid}/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Integer cid,@PathVariable Integer id,Model model,HttpSession session){
		model.addAttribute(new DataDTO());
		model.addAttribute(dataService.getData(id));
		model.addAttribute(caseService.getCaseBean(cid));
		return "data/edit";
	}
	@RequestMapping(value="/update/{cid}/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Integer cid,@PathVariable Integer id,@Valid DataDTO dataDTO,BindingResult br,Model model){
		if(br.hasErrors()){
			model.addAttribute(caseService.getCaseBean(cid));
			return "data/edit";
		}
		DataBean dataBean = dataService.getData(id);
		dataBean.setDataContent(dataDTO.getDataContent());
		dataBean.setDataName(dataDTO.getDataName());
		dataService.updateData(dataBean);
		return "redirect:/data/list/"+cid;
	}
	
}
