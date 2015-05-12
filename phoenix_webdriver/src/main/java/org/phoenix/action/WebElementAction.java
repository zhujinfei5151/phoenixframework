package org.phoenix.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.phoenix.aop.PhoenixLogger;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.LocatorBean;
import org.phoenix.model.UnitLogBean;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

/**
 * WebGUI元素的操作类
 * @author mengfeiyang
 *
 */
public abstract class WebElementAction extends WebElementLocator implements ElementAction{
	private LocatorBean locatorBean;
	private HashMap<String,LocatorBean> locators = new HashMap<String,LocatorBean>();
	private LinkedList<UnitLogBean> unitLog = new LinkedList<UnitLogBean>();
	private CaseLogBean caseLogBean = new CaseLogBean();
	public abstract LinkedList<UnitLogBean> run(CaseLogBean caseLogBean);
	public WebElementAction() {
		new PhoenixLogger();
	}
	
	public void init(int caseId,CaseLogBean caseLogBean){
		setCaseLogBean(caseLogBean);
		List<LocatorBean> list = getModelList(caseId);
		for(LocatorBean locatorBean : list){
			locators.put(locatorBean.getLocatorDataName(), locatorBean);
		}
	}
	
	public void init(String caseName,CaseLogBean caseLogBean){
		setCaseLogBean(caseLogBean);
		List<LocatorBean> list = getModelList(caseName);
		for(LocatorBean locatorBean : list){
			locators.put(locatorBean.getLocatorDataName(), locatorBean);
		}
	}
	
	public CaseLogBean getCaseLogBean() {
		return caseLogBean;
	}

	public void setCaseLogBean(CaseLogBean caseLogBean) {
		this.caseLogBean = caseLogBean;
	}

	public LinkedList<UnitLogBean> getUnitLog() {
		return unitLog;
	}

	public void setUnitLog(LinkedList<UnitLogBean> unitLog) {
		this.unitLog = unitLog;
	}

	public WebElementAction WebElement(){
		return this;
	}
	
	public WebElementAction WebElement(String name){
		locatorBean = locators.get(name);	
		return this;
	}
	
	//WebElementAction.class.getResource("/").getPath().replace("%20", " ")
	@Override
	public void openNewWindowByIE(String url){
		System.setProperty("webdriver.ie.driver", WebElementAction.class.getResource("/").getPath().replace("%20", " ")+"drivers/IEDriverServer64.exe");
		WebDriverRunner.setWebDriver(new InternetExplorerDriver());
		Selenide.open(url);
		unitLog.add(new UnitLogBean("OpenNewWindowByIE 执行成功，url："+url,"openNewWindowByIE","","SUCCESS","",caseLogBean));
		PhoenixLogger.info("OpenNewWindowByIE 执行成功，url："+url);
	}
	
	@Override
	public void openNewWindowByChrome(String url){
		System.setProperty("webdriver.chrome.driver", WebElementAction.class.getResource("/").getPath().replace("%20", " ")+"drivers/chromedriver.exe");
		WebDriverRunner.setWebDriver(new ChromeDriver());
		Selenide.open(url);
		unitLog.add(new UnitLogBean("openNewWindowByChrome 执行成功，url："+url,"openNewWindowByChrome","","SUCCESS","",caseLogBean));
	}
	
	@Override
	public void openNewWindowByFirefox(String url){
		System.setProperty("webdriver.firefox.bin", WebElementAction.class.getResource("/").getPath()+"drivers/chromedriver.exe");
		WebDriverRunner.setWebDriver(new FirefoxDriver());
		Selenide.open(url);
	}
	
	@Override
	public void sleep(long ms){
		Selenide.sleep(ms);
		unitLog.add(new UnitLogBean("sleep 执行成功，时间："+ms,"sleep",ms+"ms","SUCCESS","",caseLogBean));
		PhoenixLogger.info("sleep 执行成功，时间："+ms);
	}
	
	@Override
	public void closeWindow(){
		Selenide.close();
		unitLog.add(new UnitLogBean("closeWindow 执行成功","closeWindow","","SUCCESS","",caseLogBean));
	}
	
	@Override
	public void click(){
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).click();
		unitLog.add(new UnitLogBean("对元素 [ "+locatorBean.getLocatorData()+" ] 执行 click 成功","click","","SUCCESS","",caseLogBean));
		PhoenixLogger.info("对元素 [ "+locatorBean.getLocatorData()+" ] 执行 click 成功");
	}

	@Override
	public void setText(String text) {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).setValue(text);
		unitLog.add(new UnitLogBean("对元素 [ "+locatorBean.getLocatorData()+" ] 执行 setText 成功,setText的值是："+text,"setText","","SUCCESS","",caseLogBean));
		PhoenixLogger.info("对元素 [ "+locatorBean.getLocatorData()+" ] 执行 setText 成功,setText的值是："+text);
	}

	@Override
	public String getText() {
		String str = WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getText();
		unitLog.add(new UnitLogBean("对元素 [ "+locatorBean.getLocatorData()+" ] 执行 getText 成功,setText的值是："+str,"getText","","SUCCESS","",caseLogBean));
		PhoenixLogger.info("对元素 [ "+locatorBean.getLocatorData()+" ] 执行 getText 成功,setText的值是："+str);
		return str;
	}	
	
	@Override
	public String getAttrValue(String attr){
		String str = WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getAttribute(attr);
		unitLog.add(new UnitLogBean("对元素 [ "+locatorBean.getLocatorData()+" ] 根据attr:"+attr+" 执行 getAttrValue 成功,getAttrValue的值是："+str,"getAttrValue","获取的值是："+str,"SUCCESS","",caseLogBean));
		PhoenixLogger.info("对元素 [ "+locatorBean.getLocatorData()+" ] 根据attr:"+attr+" 执行 getAttrValue 成功,getAttrValue的值是："+str);
        return str;
	}
}
