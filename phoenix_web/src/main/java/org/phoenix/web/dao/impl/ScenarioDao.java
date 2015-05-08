package org.phoenix.web.dao.impl;

import org.phoenix.basic.impl.BaseDao;
import org.phoenix.basic.paging.Pager;
import org.phoenix.model.ScenarioBean;
import org.phoenix.web.dao.IScenarioDao;
import org.springframework.stereotype.Repository;

@Repository
public class ScenarioDao extends BaseDao<ScenarioBean> implements IScenarioDao{

	@Override
	public Pager<ScenarioBean> getScenarioBeanPager(int uid) {
		return super.find("from ScenarioBean where userId="+uid);
	}

}
