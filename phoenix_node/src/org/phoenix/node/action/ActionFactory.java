package org.phoenix.node.action;

import org.phoenix.node.dto.TaskType;

/**
 * action工厂
 * @author mengfeiyang
 *
 */
public class ActionFactory {
	
	public RunAction getRunAction(TaskType taskType){
		switch(taskType){
		case WEB_CASE:
			return new CaseAction();
		case WEB_SCENARIO:
			return new ScenarioAction();
		default: return null;
		}
	}

}
