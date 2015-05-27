package org.phoenix.node.action;

import java.util.LinkedList;

import org.phoenix.dao.CaseLogDao;
import org.phoenix.dao.UnitLogDao;
import org.phoenix.model.BatchLogBean;
import org.phoenix.model.CaseBean;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.ScenarioLogBean;
import org.phoenix.model.UnitLogBean;
import org.phoenix.node.dto.AjaxObj;
import org.phoenix.node.model.TaskModel;
import org.phoenix.utils.TaskStatusType;

public class CaseAction implements RunAction{
	private CaseBean caseBean;
	private TaskModel taskModel;
	private ScenarioLogBean scenarioLogBean;
	private boolean isScenario = false;
	
	public CaseAction(CaseBean caseBean, TaskModel taskModel,ScenarioLogBean scenarioLogBean,boolean isScenario) {
		super();
		this.caseBean = caseBean;
		this.taskModel = taskModel;
		this.scenarioLogBean = scenarioLogBean;
		this.isScenario = isScenario;
	}

	@Override
	public AjaxObj action(BatchLogBean batchLogBean){
		AjaxObj ajaxObj = new AjaxObj();
		CaseLogDao caseLogDao = new CaseLogDao();
		UnitLogDao unitLogDao = new UnitLogDao();
		CaseLogBean caseLogBean = new CaseLogBean();
		
		ExecuteMethod executeMethod = new ExecuteMethod();
		if(caseBean.getStatus() == 0) return new AjaxObj(0,"当前用例状态为已禁用，不能执行。用例名称："+caseBean.getCaseName()+",用例Id："+caseBean.getId());
		caseLogBean.setActor(caseBean.getUserId()+"");//暂使用用户id
		if(isScenario)caseLogBean.setScenarioLogBeanId(scenarioLogBean.getId());
		else caseLogBean.setBatchLogBean(batchLogBean);
		caseLogBean.setClientIP(taskModel.getSlaveModel().getSlaveIP());
		caseLogBean.setCaseName(caseBean.getCaseName());
		caseLogBean.setAttachPath(taskModel.getSlaveModel().getAttachPath());
		caseLogDao.add(caseLogBean);
		LinkedList<UnitLogBean> unitLogs = new LinkedList<UnitLogBean>();
		try {
			unitLogs = executeMethod.runByJavaCode(caseBean.getCodeContent(),caseLogBean);
			ajaxObj.setMsg("执行完成，可在日志管理模块查看执行结果");
			ajaxObj.setResult(1);
			if(taskModel.getTaskStatusType() != null && taskModel.getTaskStatusType()!= TaskStatusType.FAIL) taskModel.setTaskStatusType(TaskStatusType.SUCCESS);
			if(isScenario && scenarioLogBean.getTaskStatusType() != TaskStatusType.FAIL)scenarioLogBean.setTaskStatusType(TaskStatusType.SUCCESS);
			caseLogBean.setStatus(TaskStatusType.SUCCESS.getName());			
		} catch (Exception e){
        	UnitLogBean unitLog = new UnitLogBean();
        	unitLog.setCaseLogBean(caseLogBean);
        	unitLog.setContent("Compile Fail!"+e.getMessage());
        	unitLog.setStatus("EXCEPTION");
        	unitLog.setStepName("compile");
        	unitLog.setStepType("COMPILE");
        	unitLogs.add(unitLog);
			ajaxObj.setResult(0);
			ajaxObj.setMsg(e.getMessage());
			taskModel.setTaskStatusType(TaskStatusType.FAIL);
			taskModel.setMessage("Execute Fail!"+e.getMessage());
			caseLogBean.setStatus(TaskStatusType.FAIL.getName());
			if(isScenario){
				scenarioLogBean.setTaskStatusType(TaskStatusType.FAIL);
				scenarioLogBean.setMessage("至少有一个用例执行失败！");
			}
		}
		unitLogDao.addBatchData(unitLogs);
		caseLogDao.update(caseLogBean);
		return ajaxObj;
	}
}
