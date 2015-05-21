package org.phoenix.node.action;

import java.util.Date;
import java.util.LinkedList;

import org.phoenix.dao.BatchLogDao;
import org.phoenix.dao.CaseDao;
import org.phoenix.dao.CaseLogDao;
import org.phoenix.dao.UnitLogDao;
import org.phoenix.model.BatchLogBean;
import org.phoenix.model.CaseBean;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;
import org.phoenix.node.dao.TaskDao;
import org.phoenix.node.dto.AjaxObj;
import org.phoenix.node.dto.TaskDataDTO;
import org.phoenix.node.dto.TaskStatusType;
import org.phoenix.node.model.TaskModel;
import org.phoenix.node.util.GetNow;

public class CaseAction implements RunAction{
	@Override
	public AjaxObj action(TaskDataDTO taskDataDTO){
		CaseDao caseDao = new CaseDao();
		AjaxObj ajaxObj = new AjaxObj();
		TaskDao taskDao = new TaskDao();
		BatchLogDao batchLogDao = new BatchLogDao();
		CaseLogDao caseLogDao = new CaseLogDao();
		UnitLogDao unitLogDao = new UnitLogDao();
		BatchLogBean batchLogBean = new BatchLogBean();
		CaseLogBean caseLogBean = new CaseLogBean();
		
		
		ExecuteMethod executeMethod = new ExecuteMethod();
		TaskModel taskModel = taskDao.getTaskModel(taskDataDTO.getTaskId());
		CaseBean caseBean = caseDao.load(Integer.parseInt(taskModel.getTaskData()));
		if(caseBean.getStatus() == 0) return new AjaxObj(0,"当前用例状态为已禁用，不能执行。用例名称："+caseBean.getCaseName()+",用例Id："+caseBean.getId());
		taskModel.setStartTime(null);
		taskModel.setEndTime(null);
		taskModel.setStartTime(new Date());
		taskModel.setTaskStatusType(TaskStatusType.RUNNING);
		taskDao.update(taskModel);
		batchLogBean.setBatchId(GetNow.getCurrentTime("yyyyMMddhhmmss"));
		batchLogBean.setUid(taskModel.getUserId());
		batchLogBean.setTaskType(taskModel.getTaskType()+"");
		batchLogBean.setCreateDate(new Date());
		batchLogDao.add(batchLogBean);
		caseLogBean.setActor(taskModel.getUserId()+"");//暂使用用户id
		caseLogBean.setBatchLogBean(batchLogBean);
		caseLogBean.setClientIP(taskModel.getSlaveModel().getSlaveIP());
		caseLogBean.setCaseName(caseBean.getCaseName());
		caseLogDao.add(caseLogBean);
		LinkedList<UnitLogBean> unitLogs = new LinkedList<UnitLogBean>();
		try {
			unitLogs = executeMethod.runByJavaCode(caseBean.getCodeContent(),caseLogBean);
			ajaxObj.setMsg("执行完成，可在日志管理模块查看执行结果");
			ajaxObj.setResult(1);
			taskModel.setTaskStatusType(TaskStatusType.SUCCESS);
			caseLogBean.setStatus(TaskStatusType.SUCCESS+"");			
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
			caseLogBean.setStatus(TaskStatusType.FAIL+"");
		}
		taskModel.setEndTime(new Date());
		unitLogDao.addBatchData(unitLogs);
		taskDao.update(taskModel);
		caseLogDao.update(caseLogBean);
		return ajaxObj;
	}
}
