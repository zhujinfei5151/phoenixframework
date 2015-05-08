package org.phoenix.node.action;

import java.util.Date;

import org.phoenix.dao.CaseDao;
import org.phoenix.model.CaseBean;
import org.phoenix.node.dao.TaskDao;
import org.phoenix.node.dto.AjaxObj;
import org.phoenix.node.dto.TaskDataDTO;
import org.phoenix.node.dto.TaskStatusType;
import org.phoenix.node.model.TaskModel;

public class CaseAction implements RunAction{
	@Override
	public AjaxObj action(TaskDataDTO taskDataDTO){
		CaseDao caseDao = new CaseDao();
		AjaxObj ajaxObj = new AjaxObj();
		TaskDao taskDao = new TaskDao();
		ExecuteMethod executeMethod = new ExecuteMethod();
		TaskModel taskModel = taskDao.getTaskModel(taskDataDTO.getTaskId());
		CaseBean caseBean = caseDao.load(Integer.parseInt(taskModel.getTaskData()));
		taskModel.setStartTime(null);
		taskModel.setEndTime(null);
		taskModel.setStartTime(new Date());
		taskModel.setTaskStatusType(TaskStatusType.RUNNING);
		taskDao.update(taskModel);
		String result = executeMethod.runByJavaCode(caseBean.getCodeContent());
		ajaxObj.setMsg(result);
		if("success".equals(result)){
			ajaxObj.setResult(1);
			taskModel.setTaskStatusType(TaskStatusType.SUCCESS);
		} else {
			ajaxObj.setResult(0);
			taskModel.setTaskStatusType(TaskStatusType.FAIL);
			taskModel.setMessage(result);
		}
		taskModel.setEndTime(new Date());
		taskDao.update(taskModel);
		return ajaxObj;
	}
}
