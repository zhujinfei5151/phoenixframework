package org.phoenix.web.service;

import org.phoenix.basic.paging.Pager;
import org.phoenix.web.dto.TaskStatusType;
import org.phoenix.web.model.TaskModel;

/**
 * 任务服务接口
 * @author mengfeiyang
 *
 */
public interface ITaskService {
	
	/**
	 * 任务添加
	 * @param taskModel
	 */
	void add(TaskModel taskModel);
	/**
	 * 任务删除
	 * @param id
	 */
	void delete(int id);
	/**
	 * 任务更新
	 * @param taskModel
	 */
	void update(TaskModel taskModel);
	
	/**
	 * 加载一个任务
	 * @param id
	 * @return
	 */
	TaskModel getTaskModel(int id);
	/**
	 * 根据用户加载任务，并加载分页信息
	 * @param uid
	 * @return
	 */
	Pager<TaskModel> getTaskModelPagerByUser(int uid);
	
	/**
	 * 根据任务状态加载，并加载分页信息
	 * @param taskStatusType
	 * @return
	 */
	Pager<TaskModel> getTaskModelPagerByStatus(TaskStatusType taskStatusType);

}
