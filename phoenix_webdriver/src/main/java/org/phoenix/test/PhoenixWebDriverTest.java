package org.phoenix.test;

import java.io.Serializable;
import java.util.LinkedList;

import org.phoenix.action.WebElementAction;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;

public class PhoenixWebDriverTest extends WebElementAction implements Serializable{

	private static final long serialVersionUID = 1L;
	public static int caseName=1;
	public PhoenixWebDriverTest(int caseId) {
		//super(caseId);
	}
	
	public LinkedList<UnitLogBean> run(CaseLogBean caseLogBean){
		init(caseName,caseLogBean);
		openNewWindowByIE("http://www.baidu.com");
		WebElement("set").setText("123");
		String s = WebElement("click").getAttrValue("value");
		System.out.println(s);
		WebElement("click").click();
		sleep(1000);
		closeWindow();
		
		return getUnitLog();
	}
	
	public static void main(String[] args) {
		PhoenixWebDriverTest webDriver = new PhoenixWebDriverTest(caseName);
		webDriver.run(null);
	}
}
