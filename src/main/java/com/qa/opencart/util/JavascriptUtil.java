package com.qa.opencart.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavascriptUtil(WebDriver driver) {
		
		this.driver = driver;
		js = (JavascriptExecutor)this.driver;
	}
	
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");//blue
		for (int i = 0; i < 7; i++) {
			changeColor("rgb(0, 200, 0)", element);// green
			changeColor(bgcolor, element);// blue
		}
	}
	
	private void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);//GBGBGBGBGBGB

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

}
