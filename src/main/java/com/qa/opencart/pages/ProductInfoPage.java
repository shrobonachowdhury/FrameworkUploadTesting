package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementsUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementsUtil ele;
	
	public ProductInfoPage(WebDriver driver) {
		
		this.driver = driver;
		ele = new ElementsUtil(driver);
	}
	
	private final By productHeader = By.xpath("//h1");
	private final By productImages = By.xpath("//ul[@class='thumbnails']//li");
	private final By productMetaData = By.xpath("//div[@class='col-sm-4']//ul[1]//li");
	private final By productPriceData = By.xpath("//div[@class='col-sm-4']//ul[2]//li");
	
	private  Map<String,String> productMap;
	
	public String getProductHeader() {
		
		String header = ele.waitForElementVisible(productHeader,DEFAULT_TIMEOUT).getText();
		System.out.println("header of product is "+header);
		return header;
	}
	
	public int getProductImagesCount() {
		
		int images = ele.waitForElementsVisible(productImages,DEFAULT_TIMEOUT).size();
		System.out.println("total images of the product is "+images);
		return images;
	}
	
	public void getProductMetaData() {
		
		
		List<WebElement> metaDataList = ele.waitForElementsVisible(productMetaData, DEFAULT_TIMEOUT);
		for(WebElement meta : metaDataList) {
			
			String productMetaDataKey = (meta.getText().split(":"))[0];
			String productMetaDataValue = (meta.getText().split(":"))[1].trim();
			productMap.put(productMetaDataKey, productMetaDataValue);
		}
		
		
	}
	
	public void getProductPriceData() {
		
		List<WebElement> priceDataList = ele.waitForElementsVisible(productPriceData, DEFAULT_TIMEOUT);
		String productPrice = priceDataList.get(0).getText();
		String exTaxPrice =  priceDataList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);
		
		
	}
	
	public Map<String,String>  getProductDetails() {
		productMap = new HashMap<String,String>();
		productMap.put("productHeader", getProductHeader());
		productMap.put("productImagesCount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("full product details: "+productMap);
		return productMap;
	}

}
