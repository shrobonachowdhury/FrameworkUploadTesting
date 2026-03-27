package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.ACCOUNTPAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.LOGINPAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGINPAGE_TITLE;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Feature("F 50: OpenCart - Login Feature")
@Epic("Epic 100: Design pages for Opencart Login functionality")
@Story("US 101: implement login page for Opencart application")

public class LoginPageTest extends BaseTest{
	
	@Description("This test validates if Title of Login Page is displayed correctly")
	@Severity(SeverityLevel.MINOR)
	@Owner("Shrobona Chowdhury")
	@Test(description="checking title of login page")
	public void loginPageTitleTest() {
		
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title,LOGINPAGE_TITLE);
	}
	
	@Description("This test validates if url of ligin page displayed correctly")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Shrobona Chowdhury")
	@Test(description="checking url of login page")
	public void loginPageUrlTest() {
		
		String url = loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(LOGINPAGE_FRACTION_URL));
	}
	
	@Description("This test validates if Forgot password link displayed")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Shrobona Chowdhury")
	@Test(enabled = true,description = "Checking if Forgot Password Link exists")
	public void isForgotPasswordExistsTest() {
		
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Description("This test validates if user is able to login")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Shrobona Chowdhury")
	@Test(priority=Short.MAX_VALUE,description="checking if user is able to login")
	public void loginTest() throws InterruptedException {
		
		Thread.sleep(5000);
		accountPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(5000);
		Assert.assertEquals(accountPage.getAccountPageTitle(),ACCOUNTPAGE_TITLE);
	}

}
