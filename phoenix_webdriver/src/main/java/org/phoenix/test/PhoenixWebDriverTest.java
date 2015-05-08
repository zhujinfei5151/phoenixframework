package org.phoenix.test;

import java.io.Serializable;

import org.phoenix.action.WebElementAction;

public class PhoenixWebDriverTest extends WebElementAction implements Serializable{

	private static final long serialVersionUID = 1L;
	public static int caseName=1;
	public PhoenixWebDriverTest(int caseId) {
		//super(caseId);
	}
	
	public void run(){
		init(caseName);
		openNewWindowByIE("http://www.baidu.com");
		WebElement("set").setText("123");
		String s = WebElement("click").getAttrValue("value");
		System.out.println(s);
		WebElement("click").click();
		sleep(1000);
		closeWindow();
	}
	
	public static void main(String[] args) {
		PhoenixWebDriverTest webDriver = new PhoenixWebDriverTest(caseName);
		webDriver.run();
	}
}
