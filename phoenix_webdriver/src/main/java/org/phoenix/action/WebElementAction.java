package org.phoenix.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.phoenix.aop.CheckPointInvocationHandler;
import org.phoenix.aop.PhoenixLogger;
import org.phoenix.dao.DataDao;
import org.phoenix.dao.LocatorDao;
import org.phoenix.enums.LocatorType;
import org.phoenix.model.CaseLogBean;
import org.phoenix.model.DataBean;
import org.phoenix.model.LocatorBean;
import org.phoenix.model.UnitLogBean;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

/**
 * WebGUI元素的操作类
 * @author mengfeiyang
 *
 */
public class WebElementAction extends WebElementLocator implements ElementAction{
	private LocatorBean locatorBean;
	private LinkedList<UnitLogBean> unitLog;
	private ElementAction webProxy;
	private LocatorDao locatorDao = new LocatorDao();
	private DataDao dataDao = new DataDao();
	private HashMap<String,LocatorBean> locators = new HashMap<String,LocatorBean>();
	private HashMap<String,String> datas = new HashMap<String,String>();
	private CaseLogBean caseLogBean;
	private String ChromeDriverPath;
	private String FirefoxPath;

	public WebElementAction(LinkedList<UnitLogBean> unitLog) {
		new PhoenixLogger();
		this.unitLog = unitLog;
	}
	
	public void addLocatorAndDatas(int caseId,CaseLogBean caseLogBean){
		setCaseLogBean(caseLogBean);
		List<LocatorBean> llist = locatorDao.getModelList(caseId);
		List<DataBean> dlist = dataDao.getModelList(caseId);
		for(LocatorBean locatorBean : llist){
			locators.put(locatorBean.getLocatorDataName(), locatorBean);
		}
		for(DataBean dataBean : dlist){
			datas.put(dataBean.getDataName(), dataBean.getDataContent());
		}
	}
	
	public void addLocatorAndDatas(String caseName,CaseLogBean caseLogBean){
		setCaseLogBean(caseLogBean);
		List<LocatorBean> list = locatorDao.getModelList(caseName);
		List<DataBean> dlist = dataDao.getModelList(caseName);
		for(LocatorBean locatorBean : list){
			locators.put(locatorBean.getLocatorDataName(), locatorBean);
		}
		for(DataBean dataBean : dlist){
			datas.put(dataBean.getDataName(), dataBean.getDataContent());
		}
	}
	
	public CaseLogBean getCaseLogBean() {
		return caseLogBean;
	}

	public void setCaseLogBean(CaseLogBean caseLogBean) {
		this.caseLogBean = caseLogBean;
	}

	public ElementAction webElement(){
		return webProxy;
	}
	/**
	 * 直接使用定位信息而无需将其先录入数据库
	 * @param locatorData
	 * @param locatorType 如果为null，则默认为locatorType 为CSS。Class和id可直接作为Css定位
	 * @return
	 */
	public ElementAction webElement(String locatorData,LocatorType locatorType){
		if(locatorType == null)locatorBean = new LocatorBean(locatorData);
		else locatorBean = new LocatorBean(locatorData,locatorType);
		return webProxy;
	}
	
	/**
	 * 指定一个定位信息的标识，需要先将其录入数据库之后才会有该标识
	 */
	public ElementAction webElement(String name){
		locatorBean = locators.get(name);	
		return webProxy;
	}
	
	/**
	 * 不使用任何定位信息时，如close方法
	 * @return
	 */
	public ElementAction getWebProxy() {
		return webProxy;
	}

	public void setWebProxy(ElementAction webProxy) {
		this.webProxy = webProxy;
	}
	/**
	 * 产生检查点代理
	 */
	public ICheckPoint checkPoint(){
		ICheckPoint checkPoint = (ICheckPoint)new CheckPointInvocationHandler(new CheckPoint(),unitLog,caseLogBean).getProxy();
		return checkPoint;
	}
	/**
	 * 如果将数据已经录入了数据库，则指定该数据的标识
	 */
	@Override
	public String getData(String dataName){
		return datas.get(dataName);
	}
	//WebElementAction.class.getResource("/").getPath().replace("%20", " ")
	@Override
	public void openNewWindowByIE(String url){
			System.setProperty("webdriver.ie.driver", WebElementAction.class.getResource("/").getPath().replace("%20", " ")+"drivers/IEDriverServer64.exe");
			WebDriverRunner.setWebDriver(new InternetExplorerDriver());
			Selenide.open(url);
	}
	@Override
	public void setChromeDriverExePath(String path){
		ChromeDriverPath = path;
	}
	/**
	 * 如果打不开Chrome浏览器或或者报异常，应先使用 setChromeDriverExePath(String path)方法指定chromedriver.exe路径<br>
	 * 该程序的路径必须和Chrome.exe在同一目录下
	 */
	@Override
	public void openNewWindowByChrome(String url) {
		System.setProperty("webdriver.chrome.driver",ChromeDriverPath);
		WebDriverRunner.setWebDriver(new ChromeDriver());
		Selenide.open(url);
	}
	
	@Override
	public void setFirefoxExePath(String path){
		FirefoxPath = path;
	}
	/**
	 * 如果打不开Firefox浏览器或或者报异常，应先使用 setFirefoxExePath(String path)方法指定Firefox主程序路径
	 */
	@Override
	public void openNewWindowByFirefox(String url) {
		System.setProperty("webdriver.firefox.bin", FirefoxPath);
		WebDriverRunner.setWebDriver(new FirefoxDriver());
		Selenide.open(url);
	}

	@Override
	public void closeWindow() {
		Selenide.close();
	}
	
	@Override
	public void click(){
			WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).click();
	}

	@Override
	public String getText() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getText();
	}	

	@Override
	public String innerText() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).innerText();
	}

	@Override
	public String innerHtml() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).innerHtml();
	}

	@Override
	public String name() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).name();
	}

	@Override
	public boolean exists() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).exists();
	}

/*	@Override
	public SelenideElement $(String cssSelector) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).$(cssSelector);
	}

	@Override
	public SelenideElement $(String cssSelector, int index) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).$(cssSelector, index);
	}

	@Override
	public SelenideElement $(By selector) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).$(selector);
	}

	@Override
	public SelenideElement $(By selector, int index) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).$(selector, index);
	}

	@Override
	public ElementsCollection $$(String cssSelector) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).$$(cssSelector);
	}*/

	/*
	 * 根据定位信息获取对象列表
	 * @see org.phoenix.action.ElementAction#getElements()
	 */
	@Override
	public ElementsCollection getElements() {
		return WebElements(locatorBean.getLocatorData(),locatorBean.getLocatorType());
	}

	@Override
	public void selectOption(String text) {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).selectOption(text);
	}

	@Override
	public void selectOptionByValue(String value) {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).selectOptionByValue(value);
	}

	@Override
	public String getSelectedValue() {
		String str = WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getSelectedValue();
		return str;
	}

	@Override
	public String getSelectedText() {
		String str = WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getSelectedText();
		return str;
	}

	@Override
	public File download() throws FileNotFoundException {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).download();
	}

	@Override
	public SelenideElement contextClick() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).contextClick();
	}

	@Override
	public SelenideElement hover() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).hover();
	}

	@Override
	public SelenideElement dragAndDropTo(String targetCssSelector) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).dragAndDropTo(targetCssSelector);
	}

	@Override
	public boolean isImage() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).isImage();
	}
	
	@Override
	public SelenideElement parent() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).parent();
	}

	@Override
	public SelenideElement waitWhile(Condition condition,
			long timeoutMilliseconds) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).waitWhile(condition, timeoutMilliseconds);
	}

	@Override
	public boolean isDisplayed() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).isDisplayed();
	}
	
	@Override
	public void submit() {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).submit();		
	}

	@Override
	public void sendKeys(String str) {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).sendKeys(str);
	}

	@Override
	public void clear() {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).clear();
	}

	@Override
	public String getTagName() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getTagName();
	}

	@Override
	public String getAttribute(String name) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getAttribute(name);
	}

	@Override
	public boolean isSelected() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).isSelected();
	}

	@Override
	public boolean isEnabled() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).isEnabled();
	}

	@Override
	public String getCssValue(String propertyName) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getCssValue(propertyName);
	}

	@Override
	public SelenideElement getSelectedOption() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getSelectedOption();
	}

	@Override
	public SelenideElement scrollTo() {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).scrollTo();
	}

	@Override
	public void setText(String text) {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).setValue(text);
	}

	@Override
	public String getAttrValue(String attr) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).getAttribute(attr);
	}

	@Override
	public void sleep(long ms) {
		Selenide.sleep(ms);
	}

	@Override
	public void append(String str) {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).append(str);		
	}

	@Override
	public void pressEnter() {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).pressEnter();
	}

	@Override
	public void pressTab() {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).pressTab();
	}

	@Override
	public void setSelected(boolean selected) {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).setSelected(selected);
	}

	@Override
	public void waitUntil(Condition condition, long timeoutMilliseconds) {
		WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).waitUntil(condition, timeoutMilliseconds);
	}

	@Override
	public File uploadFile(String filePath) {
		return WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()).uploadFile(new File(filePath));
	}
	
	@Override
	public void switchToWindow(String title) {
		Selenide.switchToWindow(title);
	}

	@Override
	public void switchToWindow(int index) {
		Selenide.switchToWindow(index);
	}
	
	@Override
	public void switchToFrame(String nameOrId){
		WebDriverRunner.getWebDriver().switchTo().frame(nameOrId);
	}
	
	public void switchToParent(){
		WebDriverRunner.getAndCheckWebDriver().switchTo().parentFrame();
	}
	@Override
	public String getPageSource(){
		return WebDriverRunner.getAndCheckWebDriver().getPageSource();
	}
	@Override
	public String screenshot(String fileName){
		return Selenide.screenshot(fileName);
	}
	
	@Override
	public void back() {
		Selenide.back();
	}

	@Override
	public void forward() {
		Selenide.forward();
	}

	@Override
	public String title() {
		return Selenide.title();
	}
	
	@Override
	public void refresh(){
		Selenide.refresh();
	}
	
	public void doubleClick(){
		Selenide.actions().doubleClick(WebElement(locatorBean.getLocatorData(),locatorBean.getLocatorType()));
	}

	@Override
	public void confirm(String expectedDialogText) {
		Selenide.confirm(expectedDialogText);
	}

	@Override
	public SelenideElement selectRadio(String value) {
		return Selenide.selectRadio(by(locatorBean.getLocatorData(),locatorBean.getLocatorType()), value);
	}

	@Override
	public SelenideElement getSelectedRadio() {
		return Selenide.getSelectedRadio(by(locatorBean.getLocatorData(),locatorBean.getLocatorType()));
	}

	@Override
	public void dismiss(String expectedDialogText) {
		Selenide.dismiss(expectedDialogText);
	}

	@Override
	public List<String> getJavascriptErrors() {
		return Selenide.getJavascriptErrors();
	}

	@Override
	public List<String> getWebDriverLogs(String logType, Level logLevel) {
		return Selenide.getWebDriverLogs(logType, logLevel);
	}

}
