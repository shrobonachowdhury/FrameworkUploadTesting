package com.qa.opencart.util;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementsUtil {
	
	private WebDriver driver;
	private JavascriptUtil jsUtil;
	
	public ElementsUtil(WebDriver driver) {
		
		this.driver = driver;
		jsUtil = new JavascriptUtil(driver);
	}
	
	public WebElement waitForElementPresence(By locator,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	@Step("fetching element using : {0}")
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		ChainTestListener.log("locator is "+element.toString());
		highlightElement(element);
		return element;
	}
	
	private void highlightElement(WebElement element) {
		if(Boolean.parseBoolean(DriverFactory.HighlightElement)) {
			jsUtil.flash(element);
		}
	}	
	
	@Step("waiting for element visible using locator: {0} and time: {1}")
	public WebElement waitForElementVisible(By locator,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		highlightElement(element);
		return element;
	}
	
	public void clickWithWait(By locator,int timeOut) {
		
		waitForElementVisible(locator,timeOut).click();
	}
	
	public void sendKeysWithWait(By locator, int timeOut,CharSequence...value) {
		
		waitForElementVisible(locator,timeOut).sendKeys(value);
	}
	
	@Step("clicking on element: {0}")
	public void doClick(By locator) {
		
		getElement(locator).click();
		
	}
	
	@Step("entering value: {1} in field: {0}")
	public void doSendkeys(By locator,String value) {
		
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	public String waitForTitleContains(String titleFraction,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleContains(titleFraction));
			return driver.getTitle();
		}catch(TimeoutException e) {
			return null;
		}
	}
	
	public String waitForTitleIs(String title,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();
		}catch(TimeoutException e) {
			return null;
		}
	}
	
	public String waitForUrlContains(String fractionUrl, int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.urlContains(fractionUrl));
			return driver.getCurrentUrl();
		}catch(TimeoutException e) {
			return null;
		}
	}
	
	public String waitForUrlIs(String Url, int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		try {
		wait.until(ExpectedConditions.urlToBe(Url));
		return driver.getCurrentUrl();
		}catch(TimeoutException e) {
			return null;
		}
}
	
	public boolean isElementDisplayed(By locator) {
		
		return getElement(locator).isDisplayed();
	}
	
	@Step("waiting for list of elements visible using locator: {0} and time: {1}")
	public List<WebElement> waitForElementsVisible(By locator,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}catch(Exception e) {
			return Collections.EMPTY_LIST;
		}
	}
	
	@Step("clicking element: {0} in time: {1}")
	public void clickWhenReady(By locator,int timeOut) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	
}
