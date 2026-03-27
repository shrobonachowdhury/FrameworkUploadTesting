package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	public static final String LOGINPAGE_TITLE = "Account Login";
	public static final String ACCOUNTPAGE_TITLE = "My Account11";
	public static final String LOGINPAGE_FRACTION_URL = "route=account/login";
	public static final String ACCOUNTPAGE_FRACTION_URL = "route=account/account";
	
	public static final int DEFAULT_TIMEOUT = 20;
	public static final int MAX_TIMEOUT = 40;
	public static final int MIN_TIMEOUT = 15;
	
	public static final List<String> EXPECTEDACCOUNTPAGE_HEADERS = List.of
			("My Account","My Orders","My Affiliate Account", "Newsletter");
	
	public static final String REGISTRATION_SHEET_NAME = "register";
	public static final String PRODUCTINFO_SHEET_NAME = "product";
}
