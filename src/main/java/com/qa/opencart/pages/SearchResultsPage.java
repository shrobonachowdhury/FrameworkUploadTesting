package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementsUtil;
import static com.qa.opencart.constants.AppConstants.*;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementsUtil ele;
	
	public SearchResultsPage(WebDriver driver) {
		
		this.driver = driver;
		ele = new ElementsUtil(driver);
	}
	
	private final By productResults = By.xpath("//div[@class='product-thumb']");
	
	
	public int getSearchResultsCount() {
		
		int searchCount = ele.waitForElementsVisible(productResults, DEFAULT_TIMEOUT).size();
		System.out.println("total number of search products are "+searchCount);
		return searchCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		
		ele.doClick(By.linkText(productName));
		System.out.println("clicked on search product "+productName);
		return new ProductInfoPage(driver);
	}
}
