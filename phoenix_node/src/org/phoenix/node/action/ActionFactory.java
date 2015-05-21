package org.phoenix.node.action;

import java.util.concurrent.Callable;

import org.phoenix.node.dto.AjaxObj;
import org.phoenix.node.dto.TaskDataDTO;
import org.phoenix.node.dto.TaskType;

/**
 * action工厂
 * @author mengfeiyang
 *
 */
public class ActionFactory implements Callable<AjaxObj>{
	private TaskType taskType;
	private TaskDataDTO taskDataDTO;
	
	public TaskDataDTO getTaskDataDTO() {
		return taskDataDTO;
	}

	public void setTaskDataDTO(TaskDataDTO taskDataDTO) {
		this.taskDataDTO = taskDataDTO;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public RunAction getRunAction(){
		switch(taskType){
		case WEB_CASE:
			return new CaseAction();
		case WEB_SCENARIO:
			return new ScenarioAction();
		default: return null;
		}
	}

	@Override
	public AjaxObj call() throws Exception {
		return getRunAction().action(taskDataDTO);
	}

}
