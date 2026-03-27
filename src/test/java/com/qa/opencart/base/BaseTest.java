package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterationPage;
import com.qa.opencart.pages.SearchResultsPage;

//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountPage accountPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterationPage registerationPage;
	

	@Parameters({"browser"})
	@BeforeTest
	public void Setup(String browserName) {
		
		df = new DriverFactory();
		prop = df.initProp();
		if(browserName!=null) {
			
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginPage= new LoginPage(driver);
	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		
		if(!result.isSuccess()) {
			
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
	}
	
	@AfterTest
	public void teardown() {
		
		driver.quit();
	}

}
