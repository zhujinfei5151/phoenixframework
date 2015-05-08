package org.phoenix.cases;

import java.io.Serializable;

import org.phoenix.action.WebElementAction;

public class TestPhoenixCase extends WebElementAction implements Serializable{
	private static final long serialVersionUID = 1L;
	private static int caseName = 1;
	
	public TestPhoenixCase() {
		
	}

	@Override
	public void run() {
		init(caseName);
		openNewWindowByIE("http://www.baidu.com");
		WebElement("set").setText("1");
		String s = WebElement("click").getAttrValue("value");
		System.out.println(s);
		WebElement("click").click();
		sleep(1000);
		closeWindow();	
	}
}
