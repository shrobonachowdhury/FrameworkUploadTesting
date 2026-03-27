package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.CSVUtil;
import com.qa.opencart.util.ExcelUtil;
import static com.qa.opencart.constants.AppConstants.*;

public class RegistrationTest extends BaseTest {
	
	@BeforeClass
	public void RegisterationSetup() {
		
		registerationPage = loginPage.NavigateToRegistrationPage();
		
	}
	
	@DataProvider
	public Object[][] getUserRegData() {
		
		return new Object[][] {
			{"raman","bhalla","6464545","test123","test123","Yes"},
			{"meenu","bhalla","6433225","te21343","te21343","Yes"},
			{"sheila","bhalla","6433225","tesfty1343","tesfty1343","no"}
			
		};
	}
	
	@DataProvider
	public Object[][] getUserData() {
		
		Object[][] registrationData = ExcelUtil.getTestData(AppConstants.REGISTRATION_SHEET_NAME);
		return registrationData;
	}
	
	@DataProvider
	public Object[][] getUserDataFromCSV() {
		
		return CSVUtil.getTestDataFromCSVFile("RegisterData");
	}
	
	@Test(dataProvider="getUserDataFromCSV")
	public void RegisterUser(String firstName,String lastName,String telephone,String password,String confirmPassword,String subscribe) {
		
		Assert.assertTrue(registerationPage.
				userRegister(firstName, lastName,telephone,password,confirmPassword,subscribe));
	}

}
