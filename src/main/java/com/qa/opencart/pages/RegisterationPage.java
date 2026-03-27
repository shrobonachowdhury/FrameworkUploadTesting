package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementsUtil;
import com.qa.opencart.util.StringUtils;

import static com.qa.opencart.constants.AppConstants.*;

public class RegisterationPage {
	
	private WebDriver driver;
	private ElementsUtil ele;
	
	public RegisterationPage(WebDriver driver) {
		
		this.driver = driver;
		ele = new ElementsUtil(driver);
		
	}

	private final By firstName = By.name("firstname");
	private final By lastName = By.name("lastname");
	private final By email = By.name("email");
	private final By telephone = By.name("telephone");
	private final By password = By.xpath("//input[@id='input-password']");
	private final By confirmPassword = By.id("input-confirm");
	private final By subscribeYes = By.xpath("(//input[@name='newsletter'])[1]");
	private final By subscribeNo = By.xpath("(//input[@name='newsletter'])[2]");
	private final By termsCheckbox = By.xpath("//input[@name='agree']");
	private final By continueBtn = By.xpath("//input[@value='Continue']");
	private final By successMessage = By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]");
	private final By continueBtnRegister =  By.xpath("//a[contains(text(),'Continue')]");
	private final By MyAccountLink = By.xpath("//span[contains(text(),'My Account')]");
	private final By logoutBtn = By.xpath("//a[contains(text(),'Logout')]");
	private final By registerLink = By.xpath("(//a[contains(text(),'Register')])[2]");
	
	public boolean userRegister(String firstName,String lastName,
			String telephone,String password, String confirmPassword,String subscribe) {
		
		ele.waitForElementVisible(this.firstName, DEFAULT_TIMEOUT).sendKeys(firstName);
		ele.doSendkeys(this.lastName, lastName);
		ele.doSendkeys(this.email, StringUtils.getRandomEmaiID());
		ele.doSendkeys(this.telephone, telephone);
		ele.doSendkeys(this.password, password);
		ele.doSendkeys(this.confirmPassword, confirmPassword);
		
		
		if(subscribe.contains("Yes")) {
			
			ele.doClick(subscribeYes);
		}
		else {
			ele.doClick(subscribeNo);
		}
			
		ele.doClick(this.termsCheckbox);	
		ele.doClick(this.continueBtn);		
		if(ele.waitForElementVisible(successMessage, DEFAULT_TIMEOUT).getText().contains("Your Account Has Been Created")) {
			ele.doClick(MyAccountLink);
			ele.doClick(logoutBtn);
			ele.doClick(registerLink);
			return true;
		}
		return false;
			
	}
}
