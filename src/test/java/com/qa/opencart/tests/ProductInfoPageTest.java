package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.ExcelUtil;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppConstants.*;

public class ProductInfoPageTest extends BaseTest{

	
	@BeforeClass
	public void productInfoSetup() {
		
		accountPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		
		return new Object[][] {
			
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	

	
	@Test(dataProvider="getProductTestData")
	public void productHeaderTest(String searchKey,String productName) {
		
		searchResultsPage = accountPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actProductHeader, productName);
	}
	
	@DataProvider
	public Object[][] getProductImageData() {
		
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}
		};
	}
	
	@DataProvider
	public Object[][] getProductInfoData() {
		
		Object[][] productInforData = ExcelUtil.getTestData(AppConstants.PRODUCTINFO_SHEET_NAME);
		return productInforData;
	}
	
	@Test(dataProvider="getProductInfoData")
	public void productImagesCountTest(String searchKey,String productName,String imageCount) {
		
		searchResultsPage = accountPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actProductImgCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(String.valueOf(actProductImgCount),imageCount);
	}
	
	@Test
	public void productDetailsTest() {
		
		searchResultsPage = accountPage.doSearch("mac");
		productInfoPage = searchResultsPage.selectProduct("iMac");
		Map<String,String> actualProductDetails =productInfoPage.getProductDetails();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualProductDetails.get("Brand"),"Apple");
		softAssert.assertEquals(actualProductDetails.get("Product Code"),"Product 14");
		softAssert.assertEquals(actualProductDetails.get("Availability"),"Out Of Stock");
		softAssert.assertEquals(actualProductDetails.get("productPrice"),"$122.00");
		softAssert.assertEquals(actualProductDetails.get("exTaxPrice"),"$100.00");
		
		softAssert.assertAll();
	}

}
