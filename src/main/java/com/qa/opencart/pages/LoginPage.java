package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.LOGINPAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGINPAGE_TITLE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementsUtil;
import com.qa.opencart.util.JavascriptUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	
	private WebDriver driver;
	private ElementsUtil ele;

	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		ele = new ElementsUtil(driver);
	
	}
	
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.xpath("//a[contains(text(),'Forgotten Password')]");
	private final By registerLink = By.xpath("(//a[contains(text(),'Register')])[2]");
	
	@Step("getting login page title")
	public String getLoginPageTitle() {
		
		String title = ele.waitForTitleIs(LOGINPAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("title of loginPage is--"+title);
		return title;
	}
	
	@Step("getting login page url")
	public String getLoginPageUrl() {
		
		String url = ele.waitForUrlContains(LOGINPAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("url of the loginPage is--"+url);
		return url;
	}
	
	@Step("checking forgot password link if exists")
	public boolean isForgotPasswordLinkExist() {
		
		return ele.isElementDisplayed(forgotPwdLink);
	}
	
	@Step("login using username: {0} and password: {1} ")
	public AccountPage doLogin(String username,String pwd) {
		
		System.out.println("user credentials are "+username+" and "+pwd);
		ele.doSendkeys(email, username);
		ele.doSendkeys(password, pwd);
		ele.doClick(loginBtn);
		
		/*String title = driver.getTitle();
		System.out.println("title of page is--"+title);*/
		return new AccountPage(driver);
	}
	
	@Step("navigating to Registration Page")
	public RegisterationPage NavigateToRegistrationPage() {
			
		ele.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegisterationPage(driver);
	}
	
	

}
