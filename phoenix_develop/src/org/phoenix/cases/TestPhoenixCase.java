package org.phoenix.cases;

import java.util.LinkedList;

import org.phoenix.action.WebElementAction;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;

public class TestPhoenixCase extends WebElementAction{
	private static int caseName = 1;
	
	public TestPhoenixCase() {
		
	}

	@Override
	public LinkedList<UnitLogBean> run(CaseLogBean caseLogBean) {
		init(caseName,caseLogBean);
		openNewWindowByIE("http://www.baidu.com");
		WebElement("set").setText("1");
		String s = WebElement("click").getAttrValue("value");
		System.out.println(s);
		WebElement("click").click();
		sleep(1000);
		closeWindow();	
		
		return getUnitLog();
	}
}
