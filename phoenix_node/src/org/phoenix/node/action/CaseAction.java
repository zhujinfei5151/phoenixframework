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
		taskModel.setStartTime(null);
		taskModel.setEndTime(null);
		taskModel.setStartTime(new Date());
		taskModel.setTaskStatusType(TaskStatusType.RUNNING);
		taskDao.update(taskModel);
		batchLogBean.setBatchId(GetNow.getCurrentTime("yyyyMMddhhmmss"));
		batchLogBean.setUid(taskModel.getUserId());
		batchLogBean.setTaskType(taskModel.getTaskType()+"");
		batchLogDao.add(batchLogBean);
		caseLogBean.setActor(taskModel.getUserId()+"");//暂使用用户id
		caseLogBean.setBatchLogBean(batchLogBean);
		caseLogBean.setClientIP(taskModel.getSlaveModel().getSlaveIP());
		caseLogDao.add(caseLogBean);
		try {
		LinkedList<UnitLogBean> unitLogs = executeMethod.runByJavaCode(caseBean.getCodeContent(),caseLogBean);
		unitLogDao.addBatchData(unitLogs);
		ajaxObj.setMsg("SUCCESS");
		ajaxObj.setResult(1);
		taskModel.setTaskStatusType(TaskStatusType.SUCCESS);
		}catch(Exception e){
			ajaxObj.setResult(0);
			taskModel.setTaskStatusType(TaskStatusType.FAIL);
			taskModel.setMessage("Execute Fail!"+e.getMessage());
		}
		taskModel.setEndTime(new Date());
		taskDao.update(taskModel);
		return ajaxObj;
	}
}
