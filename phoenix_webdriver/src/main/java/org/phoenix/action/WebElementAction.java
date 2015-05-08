package org.phoenix.action;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.phoenix.aop.PhoenixLogger;
import org.phoenix.model.LocatorBean;

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
	
	public WebElementAction() {
		// TODO Auto-generated constructor stub
	}
	
	public void init(int caseId){
		new PhoenixLogger();
		List<LocatorBean> list = getModelList(caseId);
		for(LocatorBean locatorBean : list){
			locators.put(locatorBean.getLocatorDataName(), locatorBean);
		}
	}
	
	public void init(String caseName){
		new PhoenixLogger();
		List<LocatorBean> list = getModelList(caseName);
		for(LocatorBean locatorBean : list){
			locators.put(locatorBean.getLocatorDataName(), locatorBean);
		}
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
		PhoenixLogger.info("OpenNewWindowByIE 执行成功，url："+url);
	}
	
	@Override
	public void openNewWindowByChrome(String url){
		System.setProperty("webdriver.chrome.driver", WebElementAction.class.getResource("/").getPath().replace("%20", " ")+"drivers/chromedriver.exe");
		WebDriverRunner.setWebDriver(new ChromeDriver());
		Selenide.open(url);
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
		PhoenixLogger.info("sleep 执行成功，时间："+ms);
	}
	
	@Override
	public void closeWindow(){
		Selenide.close();
	}
	
	@Override
	public void click(){
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).click();
		PhoenixLogger.info("对元素 [ "+locatorBean.getLocatorData()+" ] 执行 click 成功");
	}

	@Override
	public void setText(String text) {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).setValue(text);
		PhoenixLogger.info("对元素 [ "+locatorBean.getLocatorData()+" ] 执行 setText 成功,setText的值是："+text);
	}

	@Override
	public String getText() {
		String str = WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getText();
		PhoenixLogger.info("对元素 [ "+locatorBean.getLocatorData()+" ] 执行 getText 成功,setText的值是："+str);
		return str;
	}	
	
	@Override
	public String getAttrValue(String attr){
		String str = WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getAttribute(attr);
		PhoenixLogger.info("对元素 [ "+locatorBean.getLocatorData()+" ] 根据attr:"+attr+" 执行 getAttrValue 成功,getAttrValue的值是："+str);
        return str;
	}
	
	public abstract void run();

}
