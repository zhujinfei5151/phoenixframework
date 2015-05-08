package org.phoenix.node.action;

import java.util.List;

import org.phoenix.dao.CaseDao;
import org.phoenix.dao.IModelDao;
import org.phoenix.model.CaseBean;
import org.phoenix.node.dao.TaskDao;
import org.phoenix.node.dto.AjaxObj;
import org.phoenix.node.dto.TaskDataDTO;
import org.phoenix.node.model.TaskModel;

import com.alibaba.fastjson.JSONObject;

public class ScenarioAction implements RunAction{

	@Override
	public AjaxObj action(TaskDataDTO taskDataDTO) {
		TaskDao taskDao = new TaskDao();
		TaskModel taskModel = taskDao.getTaskModel(taskDataDTO.getTaskId());
		IModelDao<CaseBean> caseDao = new CaseDao();
		ExecuteMethod executeMethod = new ExecuteMethod();
		JSONObject jsonObj = new JSONObject();
		AjaxObj ajaxObj = new AjaxObj();
		List<CaseBean> caseList = caseDao.getModelList(Integer.parseInt(taskModel.getTaskData()));
		for(int i=0;i<caseList.size();i++){
			try{
				String result = executeMethod.runByJavaCode(caseList.get(i).getCodeContent());
				if("success".equals(result)){
					jsonObj.put(i+"",new AjaxObj(1,result));
				} else {
					jsonObj.put(i+"",new AjaxObj(0,result));
				}
			}catch(Exception e){
				jsonObj.put(i+"",new AjaxObj(0,e.getMessage()+",请确保脚本没有语法错误，且包结构完整！"));
			}
		}
		ajaxObj.setObj(jsonObj);
		return ajaxObj;
	}
}
