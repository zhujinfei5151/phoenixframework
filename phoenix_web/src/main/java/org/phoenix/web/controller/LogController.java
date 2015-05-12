package org.phoenix.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.phoenix.web.dto.TaskType;
import org.phoenix.web.model.User;
import org.phoenix.web.service.IBatchLogService;
import org.phoenix.web.service.ICaseLogService;
import org.phoenix.web.service.IUnitLogService;
import org.phoenix.web.util.EnumUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogController {
	private IBatchLogService batchLogService;
	private ICaseLogService caseLogService;
	private IUnitLogService unitLogService;
	
	public IBatchLogService getBatchLogService() {
		return batchLogService;
	}
	@Resource
	public void setBatchLogService(IBatchLogService batchLogService) {
		this.batchLogService = batchLogService;
	}

	public ICaseLogService getCaseLogService() {
		return caseLogService;
	}
	@Resource
	public void setCaseLogService(ICaseLogService caseLogService) {
		this.caseLogService = caseLogService;
	}


	public IUnitLogService getUnitLogService() {
		return unitLogService;
	}
	@Resource
	public void setUnitLogService(IUnitLogService unitLogService) {
		this.unitLogService = unitLogService;
	}

	@RequestMapping("/batchlist")
	public String batchList(Model model,HttpSession session){
		User u = (User) session.getAttribute("loginUser");
		model.addAttribute("types", EnumUtils.enumProp2NameMap(TaskType.class, "name"));
		model.addAttribute("datas", batchLogService.getBatchLogPager(u.getId()));
		return "log/batchlist";
	}
	@RequestMapping("/deletebatch/{id}")
	public String deleteBatchLog(@PathVariable Integer id){
		batchLogService.deleteBatchLog(id);
		return "redirect:/log/batchlist";
	}
	
	@RequestMapping("/WEB_CASE/{id}")
	public String batchCaseList(@PathVariable Integer id,HttpSession session,Model model){
		session.setAttribute("caseloguri", "WEB_CASE");
		session.setAttribute("caselogid", id);
		model.addAttribute("datas", caseLogService.getCaseLogPagerByBatchLog(id));
		return "log/bcaselist";
	}
	@RequestMapping("/WEB_SCENARIO/{id}")
	public String scenCaseList(@PathVariable Integer id,HttpSession session,Model model){
		session.setAttribute("caseloguri", "WEB_SCENARIO");
		session.setAttribute("caselogid", id);
		model.addAttribute("datas", caseLogService.getCaseLogPagerByScenarioLog(id));
		return "log/scaselist";
	}
	@RequestMapping("/deletecaselog/{id}")
	public String deleteCaseLog(@PathVariable Integer id,HttpSession session){
		caseLogService.deleteCaseLog(id);
		return "redirect:/log/"+session.getAttribute("caseloguri")+"/"+session.getAttribute("caselogid");
	}
	@RequestMapping("/unitLogList/{id}")
	public String unitLogList(@PathVariable Integer id,Model model){
		model.addAttribute("datas", unitLogService.getUnitLogPager(id));
		return "log/unitlist";
	}
	
	@RequestMapping("/deleteUnitLog/{caseid}/{unitlogId}")
	public String deleteUnitLog(@PathVariable Integer caseid,@PathVariable Integer unitlogId,HttpSession session){
		unitLogService.deleteUnitLog(unitlogId);
		return "redirect:/log/unitLogList/"+caseid;
	}
}
