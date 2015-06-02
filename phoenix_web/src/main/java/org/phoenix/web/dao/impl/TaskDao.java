package org.phoenix.web.dao.impl;

import org.phoenix.basic.impl.BaseDao;
import org.phoenix.basic.paging.Pager;
import org.phoenix.web.dao.ITaskDao;
import org.phoenix.web.dto.TaskStatusType;
import org.phoenix.web.model.TaskModel;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDao extends BaseDao<TaskModel> implements ITaskDao{

	@Override
	public Pager<TaskModel> getTaskModelPagerByUser(int uid) {
		return super.find("from TaskModel t where t.user.id="+uid);
	}

	@Override
	public Pager<TaskModel> getTaskModelPagerByStatus(TaskStatusType status) {
		return super.find("from TaskModel where taskStatusType="+status);
	}

	@Override
	public Pager<TaskModel> getTaskModelPagerBySelect(int uid, String status,String taskType) {
		return super.find("from TaskModel where taskStatusType like '%"+status+"%' And taskType like '%"+taskType+"%' And user.id="+uid);
	}

}
