package org.phoenix.node.action;

import org.phoenix.node.dto.AjaxObj;
import org.phoenix.node.dto.TaskDataDTO;

public interface RunAction {
	AjaxObj action(TaskDataDTO taskDataDTO);
}
