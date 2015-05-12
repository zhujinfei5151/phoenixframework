package org.phoenix.web.dao;

import org.phoenix.basic.dao.IBaseDao;
import org.phoenix.basic.paging.Pager;
import org.phoenix.model.UnitLogBean;

/**
 * 单步日志操作接口
 * @author mengfeiyang
 *
 */
public interface IUnitLogDao extends IBaseDao<UnitLogBean>{
	
	/**
	 * 获取单步日志
	 * @param id
	 * @return
	 */
	UnitLogBean getUnitLogBean(int id);
	
	/**
	 * 获取单步日志列表，包括分页信息
	 * @param caseLogId
	 * @return
	 */
	Pager<UnitLogBean> getUnitLogBeanPager(int caseLogId);
	
	/**
	 * 删除一条记录
	 */
	void deleteUnitLog(int id);
}
