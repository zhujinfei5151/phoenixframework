package org.phoenix.web.dao.impl;

import java.util.List;

import org.phoenix.basic.impl.BaseDao;
import org.phoenix.web.dao.IStatDao;
import org.phoenix.web.dto.StatisticsDTO;
import org.springframework.stereotype.Repository;

@Repository
public class StatDao extends BaseDao<StatisticsDTO> implements IStatDao{

	@Override
	public List<StatisticsDTO> getStatusByCaseLogId(int id) {
		String hql = "select b.batchId as batchId,c.startTime as startTime,c.caseName as casename, l.stepType as type,count(*) as total,sum(case when l.`status`='SUCCESS' then 1 else 0 end) as success,sum(case when l.`status`='FAIL' then 1 else 0 end) as fail from l_web_batch b,l_web_case c,l_web_unit l WHERE b.id=c.batchLogId And c.id=l.caseLogId And b.id='"+id+"' group by l.stepType";
		return super.listBySql(hql, StatisticsDTO.class, false);
	}

}
