package org.phoenix.test;

import java.util.LinkedList;

import org.phoenix.action.WebElementActionProxy;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;

public class PhoenixWebDriverTest extends WebElementActionProxy {
	public static int caseName=1;
	public PhoenixWebDriverTest() {
	}
	
	@Override
	public LinkedList<UnitLogBean> run(CaseLogBean caseLogBean){
		init(caseName,caseLogBean);
		webProxy.openNewWindowByIE("http://www.baidu.com");
		webProxy.webElement("set").setText("123");
		String s = webProxy.webElement("click").getAttrValue("value");
		System.out.println(s);
		webProxy.webElement("click").click();
		webProxy.checkPoint().checkIsEqual("百度一下", s);
		webProxy.sleep(1000);
		webProxy.closeWindow();
		
		return getUnitLog();
	}
}
