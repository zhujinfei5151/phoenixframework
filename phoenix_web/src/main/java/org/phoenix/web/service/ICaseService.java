package org.phoenix.web.service;

import java.util.List;

import org.phoenix.basic.paging.Pager;
import org.phoenix.model.CaseBean;

public interface ICaseService {
	
	void addCase(CaseBean caseBean);
	void delCase(int id);
	void updateCase(CaseBean caseBean);
	List<CaseBean> getCaseBeanListByUser(int uid);
	List<CaseBean> getCaseBeanListByScenario(int scenarioId);
	Pager<CaseBean> getCaseBeanPagerByUser(int uid);
	Pager<CaseBean> getCaseBeanPagerByScenario(int scenarioId);
	CaseBean getCaseBean(int id);

}
