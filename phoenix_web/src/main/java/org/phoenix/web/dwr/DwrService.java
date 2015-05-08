package org.phoenix.web.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.phoenix.model.CaseBean;
import org.phoenix.web.service.ICaseService;

@RemoteProxy(name="dwrService")
public class DwrService implements IDwrService{
	private ICaseService caseService;
	
	public ICaseService getCaseService() {
		return caseService;
	}
	
	@Resource
	public void setCaseService(ICaseService caseService) {
		this.caseService = caseService;
	}

	@Override
	@RemoteMethod
	public void addCaseBean(CaseBean caseBean) {
		caseService.addCase(caseBean);
	}

}
