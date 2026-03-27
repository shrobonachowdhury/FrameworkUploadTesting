package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

public class AccountPageTest extends BaseTest{
	
	

	@BeforeClass
	public void AccountPageSetup() {
		
		accountPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(description = "Verifying Title of Account Page")
	public void accountPageTitleTest() {
		
		String title = accountPage.getAccountPageTitle();
		ChainTestListener.log("Actual title is "+title);
		Assert.assertEquals(title,ACCOUNTPAGE_TITLE);
	}
	
	@Test(enabled=false, description="Testing URL of Account Page")
	public void accountPageUrlTest() {
		
		String url = accountPage.getAccountPageUrl();
		ChainTestListener.log("Actual Url is "+url);
		Assert.assertTrue(url.contains(ACCOUNTPAGE_FRACTION_URL));
	}
	
	@Test(description="checking Account Page Headers")
	public void VerifyaccountPageHeaders() {
		
		List<String> actualAccountPageHeaders = accountPage.getAccountPageHeaders();
		System.out.println("actual header list is "+actualAccountPageHeaders);
		Assert.assertEquals(actualAccountPageHeaders, EXPECTEDACCOUNTPAGE_HEADERS);
	}
	

}
