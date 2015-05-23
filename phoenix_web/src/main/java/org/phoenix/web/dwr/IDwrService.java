package org.phoenix.web.dwr;

import java.util.List;

import org.phoenix.web.dto.StatisticsDTO;

public interface IDwrService {

	
	List<StatisticsDTO> listStatus(int id);
	
}
