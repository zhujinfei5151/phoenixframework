package org.phoenix.cases;

import java.util.LinkedList;

import org.phoenix.action.WebElementActionProxy;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;

public class TestPhoenixCase extends WebElementActionProxy{
	private static int caseName = 2;
	
	public TestPhoenixCase() {
		
	}

	@Override
	public LinkedList<UnitLogBean> run(CaseLogBean caseLogBean) {
		init(caseName,caseLogBean);
		
		webProxy.openNewWindowByIE("http://www.baidu.com");
		webProxy.webElement("set").setText("1");
		String s = webProxy.webElement("click").getAttrValue("value");
		System.out.println(s);
		webProxy.checkPoint().checkIsEqual("百度一下", s);
		webProxy.webElement("click").click();
		webProxy.checkPoint().checkIsFalse(s!=null);
		webProxy.sleep(100);
		webProxy.closeWindow();	
		
		return getUnitLog();
	}
	
	public static void main(String[] args) {
		TestPhoenixCase t = new TestPhoenixCase();
		LinkedList<UnitLogBean> ll = t.run(new CaseLogBean());
		for(UnitLogBean l : ll){
			System.out.println(l.getContent());
		}
	}
}
