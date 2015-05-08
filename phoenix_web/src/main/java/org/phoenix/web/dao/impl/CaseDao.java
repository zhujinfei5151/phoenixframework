package org.phoenix.web.dao.impl;

import java.util.List;

import org.phoenix.basic.impl.BaseDao;
import org.phoenix.basic.paging.Pager;
import org.phoenix.model.CaseBean;
import org.phoenix.web.dao.ICaseDao;
import org.springframework.stereotype.Repository;

@Repository("caseDao")
public class CaseDao extends BaseDao<CaseBean> implements ICaseDao{
    
	@Override
	public List<CaseBean> getCaseBeanListByUser(int uid){
		return super.list("from CaseBean where userId="+uid);
	}

	@Override
	public List<CaseBean> getCaseBeanListByScenario(int scenarioId) {
		return super.list("from CaseBean where scenarioBean.id="+scenarioId);
	}

	@Override
	public Pager<CaseBean> getCaseBeanPagerByUser(int uid) {
		return super.find("from CaseBean where userId=?", uid);
	}

	@Override
	public Pager<CaseBean> getCaseBeanPagerByScenario(int scenarioId) {
		return super.find("from CaseBean where scenarioBean.id=?", scenarioId);
	}
}
