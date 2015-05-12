package org.phoenix.web.service;

import org.phoenix.basic.paging.Pager;
import org.phoenix.model.BatchLogBean;

/**
 * 日志批次操作服务
 * @author mengfeiyang
 *
 */
public interface IBatchLogService {
	/**
	 * 删除一个批次
	 */
	void deleteBatchLog(int id);
	
	/**
	 * 获取日志批次列表，包含分页信息
	 * @param uid
	 * @return
	 */
	Pager<BatchLogBean> getBatchLogPager(int uid);
}
