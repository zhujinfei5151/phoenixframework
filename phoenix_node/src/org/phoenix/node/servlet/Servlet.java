package org.phoenix.node.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.phoenix.node.action.ActionFactory;
import org.phoenix.node.action.RunAction;
import org.phoenix.node.dto.AjaxObj;
import org.phoenix.node.dto.TaskDataDTO;
import org.phoenix.node.dto.TaskType;
import org.phoenix.node.util.WriteResponse;

import com.alibaba.fastjson.JSON;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Servlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionFactory actionFactory = new ActionFactory();
		TaskDataDTO taskDataDTO = new TaskDataDTO();
		WriteResponse.writeXml(response, JSON.toJSONString(new AjaxObj(1,"任务已被接受")));
		TaskType taskType = Enum.valueOf(TaskType.class, request.getParameter("taskType"));
		taskDataDTO.setTaskId(Integer.parseInt(request.getParameter("taskId")));
		taskDataDTO.setTaskType(taskType);
		RunAction runAction = actionFactory.getRunAction(taskType);
		runAction.action(taskDataDTO);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionFactory actionFactory = new ActionFactory();
		TaskDataDTO taskDataDTO = new TaskDataDTO();
		WriteResponse.writeXml(response, JSON.toJSONString(new AjaxObj(1,"任务已被接受")));
		TaskType taskType = Enum.valueOf(TaskType.class, request.getParameter("taskType"));
		taskDataDTO.setTaskId(Integer.parseInt(request.getParameter("taskId")));
		taskDataDTO.setTaskType(taskType);
		RunAction runAction = actionFactory.getRunAction(taskType);
		runAction.action(taskDataDTO);	
	}

}
