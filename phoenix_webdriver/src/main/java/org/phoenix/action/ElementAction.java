package org.phoenix.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.phoenix.enums.LocatorType;
import org.phoenix.model.CaseLogBean;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

/**
 * 元素操作的接口，该接口中的所有操作是web和mobile共有的，
 * 一些特殊的操作需要在实现类中独立实现
 * @author mengfeiyang
 *
 */
public interface ElementAction {
	/**
	 * 加载数据与定位信息
	 * @param caseId
	 * @param caseLogBean
	 */
	void addLocatorAndDatas(int caseId,CaseLogBean caseLogBean);
	/**
	 * 加载数据与定位信息
	 * @param caseName
	 * @param caseLogBean
	 */
	void addLocatorAndDatas(String caseName,CaseLogBean caseLogBean);
	/**
	 * 不使用任何定位信息时，如close方法
	 * @return
	 */
	ElementAction webElement();
	/**
	 * 指定一个定位信息的标识，需要先将其录入数据库之后才会有该标识
	 * @param name
	 * @return
	 */
	ElementAction webElement(String name);
	/**
	 * 直接使用定位信息而无需将其先录入数据库
	 * @param locatorData
	 * @param locatorType 如果为null，则默认为locatorType 为CSS。Class和id可直接作为Css定位
	 * @return
	 */
	ElementAction webElement(String locatorData,LocatorType locatorType);
	/**
	 * 链式查询方法，直接调用SelenideElement，使用 了此方法后，后续的操作不能被记录日志
	 * @param locatorData
	 * @param locatorType 如果定位方式为非CSS，则需要手动指定定位类型，如LocatorType.XPATH
	 * @return
	 */
	SelenideElement webElementLinkFinder(String locatorData,LocatorType locatorType);
	/**
	 * 链式查询方法，直接调用SelenideElement，使用 了此方法后，后续的操作不能被记录日志
	 * @param locatorData 默认定位方式是CSS
	 * @return
	 */
	SelenideElement webElementLinkFinder(String locatorData);
	/**
	 * 获取检查点代理方法
	 * @return
	 */
	ICheckPoint checkPoint();
	/**
	 * 如果将数据已经录入了数据库，则指定该数据的标识
	 * @param dataName
	 * @return
	 */
	String getData(String dataName);
	WebDriver getCurrentDriver();
	void setChromeDriverExePath(String path);
	void setFirefoxExePath(String path);
	void setWebProxy(ElementAction webProxy);
	/**
	 * 使用Phantomjs驱动执行被测用例，Phantomjs执行用例时没有界面，url的加载，元素的定位等<br>
	 * 都在内存中完成。在js兼容性方面比httpunit要好。
	 * @param url
	 */
	void openNewWindowByPhantomJs(String url);
	/*
	 * 使用Ie打开被测的页面
	 */
	void openNewWindowByIE(String url);
	
	/*
	 * 使用Chrome打开被测的页面
	 */
	void openNewWindowByChrome(String url);
	
	/*
	 * 使用Firefox打开被测的页面
	 */
	void openNewWindowByFirefox(String url);
	
	/**
	 * 如果有打开的浏览器窗口，则关闭
	 */
	void closeWindow();
	
	/**
	 * 对元素进行点击操作
	 */
	void click();
	/**
	 * 对可输入的输入框输入值
	 */
	void setText(String text);
	
	/**
	 * 获取可视的innerText值
	 */
	String getText();
	
	/**
	 * 获取指定属性的值
	 */
	String getAttrValue(String attr);
	
	/**
	 * 暂停执行的步骤
	 */
	void sleep(long ms);
	
	/**
	 * 在文本域中追加字符
	 */
	void append(String str);
	
	/**
	 * 在元素上按Enter键
	 */
	void pressEnter();
	
	/**
	 * 在元素上按Tab键
	 */
	void pressTab();
	
	
	String innerText();
	
	/**
	 * 返回指定元素html信息
	 */
	String innerHtml();
	
	/**
	 * 返回指定元素的name值
	 */
	String name();
	
	/**
	 * 判断元素是否存在
	 */
	boolean exists();
	
	/**
	 * 选中或取消选中
	 */
	void setSelected(boolean selected);
	
	/**
	 * 在指定时间等待操作
	 */
	void waitUntil(Condition condition, long timeoutMilliseconds);
	

/*	SelenideElement $(String cssSelector);
	SelenideElement $(String cssSelector, int index);
	SelenideElement $(By selector);
	SelenideElement $(By selector, int index);
	ElementsCollection $$(String cssSelector);*/
	ElementsCollection getElements();
	
	/**
	 * 上传一个文件
	 */
	File uploadFile(String filePath);
	
	/**
	 * 根据指定的字符选择
	 */
	void selectOption(String text);
	
	/**
	 * 根据下拉框的value选择
	 */
	void selectOptionByValue(String value);
	
	/**
	 * 获取下拉框已被选择的值
	 */
	String getSelectedValue();
	
	/**
	 * 获取下拉框已被选择的数据
	 * @return
	 */
	String getSelectedText();
	
	/**
	 * 根据指定的对象下载文件
	 */
	File download() throws FileNotFoundException;
	
	/**
	 * 右键单击鼠标
	 */
	SelenideElement contextClick();
	
	/**
	 * 将鼠标悬浮在一个元素上
	 */
	SelenideElement hover();
	
	/**
	 * 将指定元素拖拽到指定的位置，目标定位方式为css
	 */
	SelenideElement dragAndDropTo(String targetCssSelector);
	
	/**
	 * 判断是否是图像
	 */
	boolean isImage();
	
	SelenideElement parent();
	
	SelenideElement waitWhile(Condition condition, long timeoutMilliseconds);
	
	boolean isDisplayed();

	SelenideElement scrollTo();

	SelenideElement getSelectedOption();

	String getCssValue(String propertyName);

	boolean isEnabled();

	boolean isSelected();

	String getAttribute(String name);

	String getTagName();

	void clear();

	void sendKeys(String str);

	void submit();
	
	void switchToWindow(String title);
	
	void switchToWindow(int index);
	
	void confirm(String expectedDialogText);
	
	SelenideElement selectRadio(String value);
	
	SelenideElement getSelectedRadio();
	
	void dismiss(String expectedDialogText);
	
	List<String> getJavascriptErrors();
	
	List<String> getWebDriverLogs(String logType, Level logLevel);
	
	void refresh();
	
	void switchToFrame(String nameOrId);
	
	void switchToParent();
	
	void back();
	
	void forward();
	
	String title();
	
	String screenshot(String fileName);
	
	String getPageSource();
	
	public void doubleClick();

}
