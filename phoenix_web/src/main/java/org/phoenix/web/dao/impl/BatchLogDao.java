package org.phoenix.web.dao.impl;

import java.util.List;

import org.phoenix.basic.impl.BaseDao;
import org.phoenix.basic.paging.Pager;
import org.phoenix.model.BatchLogBean;
import org.phoenix.web.dao.IBatchLogDao;
import org.springframework.stereotype.Repository;

@Repository
public class BatchLogDao extends BaseDao<BatchLogBean> implements IBatchLogDao{

	@Override
	public Pager<BatchLogBean> getBatchLogPager(int uid) {
		return super.find("from BatchLogBean where uid="+uid);
	}

	@Override
	public void deleteBatchLog(int id) {
		super.delete(id);
	}
	/*
	 * (non-Javadoc)
	 * @see org.phoenix.web.dao.IBatchLogDao#getBathLogListByTaskType(String, int)
	 */
	@Override
	public List<BatchLogBean> getBathLogListByTaskType(String type,int uid) {
		return super.list("from BatchLogBean where taskType like '%"+type+"' And uid="+uid);
	}

	@Override
	public BatchLogBean getBatchLogBean(int id) {
		return super.load(id);
	}

}
