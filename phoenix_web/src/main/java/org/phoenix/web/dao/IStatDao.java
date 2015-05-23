package org.phoenix.web.dao;

import java.util.List;

import org.phoenix.basic.dao.IBaseDao;
import org.phoenix.web.dto.StatisticsDTO;

public interface IStatDao extends IBaseDao<StatisticsDTO>{
	List<StatisticsDTO> getStatusByCaseLogId(int id);
}
