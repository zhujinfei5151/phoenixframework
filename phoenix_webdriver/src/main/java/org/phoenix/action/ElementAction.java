package org.phoenix.action;

/**
 * 元素操作的接口，该接口中的所有操作是web和mobile共有的，
 * 一些特殊的操作需要在实现类中独立实现
 * @author mengfeiyang
 *
 */
public interface ElementAction {
	
	/**
	 * 使用Ie打开被测的页面
	 * @param url
	 */
	void openNewWindowByIE(String url);
	
	/**
	 * 使用Chrome打开被测的页面
	 * @param url
	 */
	void openNewWindowByChrome(String url);
	
	/**
	 * 使用Firefox打开被测的页面
	 * @param url
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
	 * @param text
	 */
	void setText(String text);
	
	/**
	 * 获取可视的innerText值
	 * @return
	 */
	String getText();
	
	/**
	 * 获取指定属性的值
	 * @return
	 */
	String getAttrValue(String attr);
	
	/**
	 * 暂停执行的步骤
	 * @param ms
	 */
	void sleep(long ms);

}
