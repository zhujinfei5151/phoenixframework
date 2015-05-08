package org.phoenix.action;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.phoenix.dao.LocatorDao;

import com.codeborne.selenide.SelenideElement;

public class WebElementLocator extends LocatorDao{
	
	protected SelenideElement WebElement(String locator, LocatorType locatorType){
		switch (locatorType) {
			case CSS:  return $(locator);
			case NAME: return $(By.name(locator));
			case XPATH: return $(By.xpath(locator));
		default:;
	    }
		return null;
	}
}
