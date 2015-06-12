package org.phoenix.cases;

import java.util.LinkedList;

import org.phoenix.action.WebElementActionProxy;
import org.phoenix.enums.LocatorType;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;

/**
 * 定位信息使用本地数据的方法
 * @author mengfeiyang
 *
 */
public class TestPhoenixCaseUseLocator extends WebElementActionProxy{
	private static String caseName = "消息测试用例";//用例的名称
	//private static int caseName = 5;//用例的id
	
	public TestPhoenixCaseUseLocator() {
		
	}

	@Override
	public LinkedList<UnitLogBean> run(CaseLogBean caseLogBean) {
		init(caseName,caseLogBean);
		webProxy.openNewWindowByIE("http://www.baidu.com");
		webProxy.webElement("#kw",null).setText("1");
		String s = webProxy.webElement("#su",null).getAttrValue("value");
		System.out.println(s);
		webProxy.checkPoint().checkIsEqual("百度一下", s);
		webProxy.webElement("#su",LocatorType.CSS).click();
		webProxy.checkPoint().checkIsFalse(s!=null);
		webProxy.sleep(100);
		webProxy.closeWindow();	
		
		return getUnitLog();
	}
	
	public static void main(String[] args) {
		TestPhoenixCaseUseLocator t = new TestPhoenixCaseUseLocator();
		LinkedList<UnitLogBean> ll = t.run(new CaseLogBean());
		for(UnitLogBean l : ll){
			System.out.println(l.getContent());
		}
	}
}
