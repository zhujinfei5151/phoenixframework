package org.phoenix.web.dao;

import org.phoenix.basic.dao.IBaseDao;
import org.phoenix.basic.paging.Pager;
import org.phoenix.model.ScenarioBean;

/**
 * 场景数据服务接口
 * @author mengfeiyang
 *
 */
public interface IScenarioDao extends IBaseDao<ScenarioBean>{
       
	/**
	 * 获取多批及分页信息
	 * @param uid
	 * @return
	 */
	Pager<ScenarioBean> getScenarioBeanPager(int uid);
}
