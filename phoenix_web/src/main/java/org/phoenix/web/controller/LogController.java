package org.phoenix.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.phoenix.web.dto.TaskType;
import org.phoenix.web.model.User;
import org.phoenix.web.service.IBatchLogService;
import org.phoenix.web.service.ICaseLogService;
import org.phoenix.web.service.IScenarioLogService;
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
	private IScenarioLogService scenarioLogService;
	
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
	
	public IScenarioLogService getScenarioLogService() {
		return scenarioLogService;
	}
	@Resource
	public void setScenarioLogService(IScenarioLogService scenarioLogService) {
		this.scenarioLogService = scenarioLogService;
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
		model.addAttribute("logId", id);
		model.addAttribute("datas", caseLogService.getCaseLogPagerByBatchLog(id));
		return "log/bcaselist";
	}
	
	@RequestMapping("/scenCaseList/{id}")
	public String scenCaseList(@PathVariable Integer id,Model model){
		model.addAttribute("datas", caseLogService.getCaseLogPagerByScenarioLog(id));
		return "log/scaselist";
	}
	
	@RequestMapping("/WEB_SCENARIO/{id}")
	public String scenList(@PathVariable Integer id,Model model){
		model.addAttribute("batchLogId", id);
		model.addAttribute("datas", scenarioLogService.getLogPager(id));
		return "log/scenlist";
	}
	
	@RequestMapping("/deletecaselog/{sid}/{id}")
	public String deleteScenCaseLog(@PathVariable Integer sid,@PathVariable Integer id){
		caseLogService.deleteCaseLog(id);
		return "redirect:/log/scenCaseList/"+sid;
	}
	
	@RequestMapping("/unitLogList/{id}")
	public String unitLogList(@PathVariable Integer id,Model model){
		model.addAttribute("datas", unitLogService.getUnitLogPager(id));
		return "log/unitlist";
	}
}
