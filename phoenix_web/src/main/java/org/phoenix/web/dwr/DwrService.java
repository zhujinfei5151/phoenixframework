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
	public List<StatisticsDTO> listStatus(int id) {
		List<StatisticsDTO> statusList = statService.getStatusByCaseLogId(id);
		return statusList;
	}

}
