package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.ACCOUNTPAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.ACCOUNTPAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementsUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementsUtil ele;
	
	public AccountPage(WebDriver driver) {
		
		this.driver=driver;
		ele = new ElementsUtil(driver);
	}
	
	private final By AccountPageHeaders = By.xpath("//div[@id='content']//h2");
	private final By searchTextbox = By.name("search");
	private final By searchIcon = By.xpath("//div[@id='search']//button");
	
	public String getAccountPageTitle() {
		
		String title = ele.waitForTitleIs(ACCOUNTPAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("title of account page is "+title);
		return title;
	}
	
	public String getAccountPageUrl() {
		
		String url = ele.waitForUrlContains(ACCOUNTPAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		return url;
	}
	
	public List<String> getAccountPageHeaders() {
		
		List<WebElement> headersList = ele.waitForElementsVisible(AccountPageHeaders, DEFAULT_TIMEOUT);
		List<String> pageHeadersList = new ArrayList<String>();
		for(WebElement list : headersList) {
			pageHeadersList.add(list.getText());
		}
		
		return pageHeadersList;
	}
	
	public SearchResultsPage doSearch(String productName) {
		
		ele.doSendkeys(searchTextbox, productName);
		ele.doClick(searchIcon);
		
		return new SearchResultsPage(driver);
	}

}
