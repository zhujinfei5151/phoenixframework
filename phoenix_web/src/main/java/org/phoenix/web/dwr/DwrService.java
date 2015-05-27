package org.phoenix.web.dwr;

import java.util.List;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.phoenix.web.dto.StatisticsDTO;
import org.phoenix.web.service.IStatService;

@RemoteProxy(name="dwrService")
public class DwrService implements IDwrService{
	private IStatService statService;

	public IStatService getStatService() {
		return statService;
	}
	@Resource
	public void setStatService(IStatService statService) {
		this.statService = statService;
	}

	@Override
	@RemoteMethod
	public List<StatisticsDTO> listCaseStatus(int id) {
		List<StatisticsDTO> statusList = statService.getCaseStatistics(id);
		return statusList;
	}
	@Override
	@RemoteMethod
	public List<StatisticsDTO> listScenarioStatus(int id) {
		List<StatisticsDTO> statusList = statService.getScenarioStatistics(id);
		for(StatisticsDTO s : statusList){
			System.out.println(s.getBatchId() +" "+s.getCasename() +"  "+s.getScenarioName()+" "+s.getType()+" "+s.getSuccess()+" "+s.getFail());
		}
		return statusList;
	}

}
