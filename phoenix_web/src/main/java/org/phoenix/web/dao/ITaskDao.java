package org.phoenix.web.dao;

import org.phoenix.basic.dao.IBaseDao;
import org.phoenix.basic.paging.Pager;
import org.phoenix.web.dto.TaskStatusType;
import org.phoenix.web.model.TaskModel;

/**
 * 任务数据层
 * @author mengfeiyang
 *
 */
public interface ITaskDao extends IBaseDao<TaskModel>{
	/*
	 * 获取当前用户下的任务
	 */
     Pager<TaskModel> getTaskModelPagerByUser(int uid);
     
     /*
      * 根据任务类型筛选
      */
     Pager<TaskModel> getTaskModelPagerByStatus(TaskStatusType status);
}
