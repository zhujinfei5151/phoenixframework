package org.phoenix.cases;

import java.util.LinkedList;

import org.phoenix.action.WebElementActionProxy;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.UnitLogBean;
/**
 * 使用数据库中的数据进行参数化
 * @author mengfeiyang
 *
 */
public class TestPhoenixCaseByDBData extends WebElementActionProxy{
	private static int caseName = 2;
	
	public TestPhoenixCaseByDBData() {
		
	}

	@Override
	public LinkedList<UnitLogBean> run(CaseLogBean caseLogBean) {
		init(caseName,caseLogBean);
		
		webProxy.openNewWindowByIE(webProxy.getData("输入数据3"));
		webProxy.webElement("set").setText(webProxy.getData("输入数据1"));
		String s = webProxy.webElement("click").getAttrValue(webProxy.getData("输入数据2"));
		System.out.println(s);
		webProxy.checkPoint().checkIsEqual(webProxy.getData("输入数据2"), s);
		webProxy.webElement("click").click();
		webProxy.checkPoint().checkIsFalse(s!=null);
		webProxy.sleep(Long.parseLong(webProxy.getData("输入数据1")));
		webProxy.closeWindow();	
		
		return getUnitLog();
	}
	
	public static void main(String[] args) {
		TestPhoenixCaseByDBData t = new TestPhoenixCaseByDBData();
		LinkedList<UnitLogBean> ll = t.run(new CaseLogBean());
		for(UnitLogBean l : ll){
			System.out.println(l.getContent());
		}
	}
}
