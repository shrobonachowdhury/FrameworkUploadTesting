package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest{
	
	@BeforeClass
	public void SearchSetup() {
		
		accountPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void searchTest1() {
		
		searchResultsPage = accountPage.doSearch("mac");
	    int actSearchCount = searchResultsPage.getSearchResultsCount();
	    Assert.assertEquals(actSearchCount, 4);
	}
	
	@Test(priority=2)
	public void searchTest2() {
		
		searchResultsPage = accountPage.doSearch("airtel");
	    int actSearchCount = searchResultsPage.getSearchResultsCount();
	    Assert.assertEquals(actSearchCount, 0);
	}
	
	
	

}
