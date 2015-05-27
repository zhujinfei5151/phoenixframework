package org.phoenix.web.dwr;

import java.util.List;

import org.phoenix.web.dto.StatisticsDTO;

public interface IDwrService {

	
	List<StatisticsDTO> listCaseStatus(int id);
	List<StatisticsDTO> listScenarioStatus(int id);
	
}
