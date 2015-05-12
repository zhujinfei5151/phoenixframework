package org.phoenix.web.service;

import org.phoenix.basic.paging.Pager;
import org.phoenix.model.UnitLogBean;

/**
 * 单步日志操作服务
 * @author mengfeiyang
 *
 */
public interface IUnitLogService {
	
	/**
	 * 删除一条记录
	 * @param id
	 */
	void deleteUnitLog(int id);
	
	/**
	 * 获取一条记录信息
	 * @param id
	 * @return
	 */
	UnitLogBean getUnitLog(int id);
	
	/**
	 * 获取日志列表，包含分页信息
	 * @param caseLogId
	 * @return
	 */
	Pager<UnitLogBean> getUnitLogPager(int caseLogId);

}
