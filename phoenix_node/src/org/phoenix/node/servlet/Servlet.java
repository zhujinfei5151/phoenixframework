package org.phoenix.node.servlet;

import java.io.IOException;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.phoenix.node.action.ActionFactory;
import org.phoenix.node.action.ActionHandler;
import org.phoenix.node.dto.AjaxObj;
import org.phoenix.node.dto.TaskDataDTO;
import org.phoenix.node.dto.TaskType;
import org.phoenix.node.util.WriteResponse;

import com.alibaba.fastjson.JSON;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExecutorService executorService;
	private CompletionService<AjaxObj> threadPool;
	private Future<AjaxObj> future;
	private String contentPath;
    public Servlet() {
        super();
    }
    @Override
	public void init(ServletConfig config) throws ServletException {
		executorService = Executors.newSingleThreadExecutor();		
		threadPool = new ExecutorCompletionService<AjaxObj>(executorService);
		future = threadPool.submit(new ActionHandler());
		contentPath = config.getServletContext().getRealPath("");
	}
	@Override
	public void destroy() {
		executorService.shutdownNow();
	}
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("requestType")==null?"getStatus":request.getParameter("requestType");
		if(status.equals("getStatus")){
			if(future.isDone()) WriteResponse.writeXml(response, JSON.toJSONString(new AjaxObj(1,"当前执行机处于空闲状态")));
			else WriteResponse.writeXml(response, JSON.toJSONString(new AjaxObj(0,"该分机当前正在执行测试任务，请选择其他执行机！")));
		}
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionFactory actionFactory = new ActionFactory();
		TaskDataDTO taskDataDTO = new TaskDataDTO();
		TaskType taskType = Enum.valueOf(TaskType.class, request.getParameter("taskType"));
		taskDataDTO.setTaskId(Integer.parseInt(request.getParameter("taskId")));
		taskDataDTO.setTaskType(taskType);
		taskDataDTO.setContentPath(contentPath);
		actionFactory.setTaskDataDTO(taskDataDTO);
		if(future.isDone()){
			future = threadPool.submit(actionFactory);
			try {
				WriteResponse.writeXml(response, JSON.toJSONString(future.get(500, TimeUnit.MILLISECONDS)));
			} catch (TimeoutException e1) {
				WriteResponse.writeXml(response, JSON.toJSONString(new AjaxObj(2,"任务发布成功")));
			} catch (Exception e) {
				WriteResponse.writeXml(response, JSON.toJSONString(new AjaxObj(0,e.getMessage()==null?"任务执行失败":e.getMessage())));
			} 
		} else {
			WriteResponse.writeXml(response, JSON.toJSONString(new AjaxObj(1,"该分机当前正在执行测试任务，请选择其他执行机！")));
		}
	}
}
