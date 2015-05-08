package org.phoenix.web.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.phoenix.action.LocatorType;
import org.phoenix.model.CaseBean;
import org.phoenix.model.LocatorBean;
import org.phoenix.web.auth.AuthClass;
import org.phoenix.web.dto.LocatorDTO;
import org.phoenix.web.service.ICaseService;
import org.phoenix.web.service.ILocatorService;
import org.phoenix.web.util.EnumUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/locator")
@AuthClass("login")
public class LocatorController {
	private ILocatorService locatorService;
	private ICaseService caseService;

	public ICaseService getCaseService() {
		return caseService;
	}
	@Resource
	public void setCaseService(ICaseService caseService) {
		this.caseService = caseService;
	}
	public ILocatorService getLocatorService() {
		return locatorService;
	}
    @Resource
	public void setLocatorService(ILocatorService locatorService) {
		this.locatorService = locatorService;
	}

	public LocatorController() {
	}
	
	@RequestMapping("/case/{id}")
	public String list(@PathVariable Integer id,Model model){
		model.addAttribute("caseBean", caseService.getCaseBean(id));
		model.addAttribute("datas",locatorService.getLoatorBeanPager(id));
		return "locator/list";
	}

	@RequestMapping(value="/add/{id}",method=RequestMethod.GET)
	public String add(@PathVariable Integer id,Model model){
		model.addAttribute("caseId", id);
		model.addAttribute("types", EnumUtils.enumProp2NameMap(LocatorType.class, "name"));
		model.addAttribute("locatorDTO", new LocatorDTO());
		return "locator/add";
	}
	@RequestMapping(value="/add/{id}",method=RequestMethod.POST)
	public String add(@Valid LocatorDTO locatorDTO,BindingResult br){
		if(br.hasErrors()){
			return "locator/add";
		}
		CaseBean caseBean = new CaseBean();
		caseBean.setId(locatorDTO.getCaseId());
		LocatorBean locatorBean = new LocatorBean();
		locatorBean.setCaseBean(caseBean);
		locatorBean.setLocatorData(locatorDTO.getLocatorData());
		locatorBean.setLocatorDataName(locatorDTO.getLocatorDataName());
		locatorBean.setLocatorType(locatorDTO.getLocatorType());
		locatorService.addLocator(locatorBean);
		return "redirect:/locator/case/"+locatorDTO.getCaseId();
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Integer id){
		LocatorBean locatorBean = locatorService.getLocatorBean(id);
		locatorService.delLocator(id);
		return "redirect:/locator/case/"+locatorBean.getCaseBean().getId();
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Integer id,Model model){
		model.addAttribute("types", EnumUtils.enumProp2NameMap(LocatorType.class, "name"));
		model.addAttribute(locatorService.getLocatorBean(id));
		model.addAttribute(new LocatorDTO());
		return "locator/edit";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Integer id,@Valid LocatorDTO locatorDTO,BindingResult br,Model model){
		if(br.hasErrors()){
			return "locator/edit";
		}
		CaseBean caseBean = new CaseBean();
		caseBean.setId(locatorDTO.getCaseId());
		
		LocatorBean locatorBean = locatorService.getLocatorBean(id);
		locatorBean.setCaseBean(caseBean);
		locatorBean.setLocatorData(locatorDTO.getLocatorData());
		locatorBean.setLocatorDataName(locatorDTO.getLocatorDataName());
		locatorBean.setLocatorType(locatorDTO.getLocatorType());
		locatorService.updateLocator(locatorBean);
		return "redirect:/locator/case/"+locatorDTO.getCaseId();
	}
	
	
}
