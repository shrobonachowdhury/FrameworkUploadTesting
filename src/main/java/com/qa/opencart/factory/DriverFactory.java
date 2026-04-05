package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String HighlightElement;
	public static ThreadLocal<WebDriver> tdlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);

		HighlightElement = prop.getProperty("highlight");

		switch (prop.getProperty("browser").toLowerCase().trim()) {

		case "chrome":
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				
				initRemoteDriver("chrome");
			}
			else {
			tdlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			System.out.println("chrome browser is launched");
			break;
		case "firefox":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				
				initRemoteDriver("firefox");
			}
			else {
			tdlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			System.out.println("Fireforx browser is launched");
			break;
		case "edge":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				
				initRemoteDriver("edge");
			}
			else {
			tdlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			System.out.println("Edge browser is launched");
			break;
		case "safari":
			tdlDriver.set(new SafariDriver());
			System.out.println("Safari browser is launched");
			break;
		default:
			System.out.println("please provide a valid browser name!!! " + prop.getProperty("browser"));
			throw new BrowserException("---INVALID BROWSER PROVIDED---");
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}
	
	private void initRemoteDriver(String browserName) {
		
		switch(browserName) {
		
		case "chrome":
		try {
			tdlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getChromeOptions()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		case "firefox":
		try {
			tdlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getFirefoxOptions()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		case "edge":
		try {
			tdlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getEdgeOptions()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		
		default:
			System.out.println("please provide a valid browser name!!! " + prop.getProperty("browser"));
			throw new BrowserException("---INVALID BROWSER PROVIDED---");
	}
	}
	
	public static WebDriver getDriver() {
		
		return tdlDriver.get();
	}

	public Properties initProp() {
		FileInputStream fis = null;
		String envName = System.getProperty("env");
		try {
		if(envName==null) {
			System.out.println("test suite is running in qa environment...");
			fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}
		else {
			System.out.println("test suite is running in "+envName+" environment...");
		switch (envName.toLowerCase().trim()) {
		case "qa":		
			fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
			break;
		case "stage":
			fis = new FileInputStream("./src/test/resources/config/stage.config.properties");
			break;
		case "prod":
			fis = new FileInputStream("./src/test/resources/config/prod.config.properties");
			break;
		}
		}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
	
	
	public static File getScreenshotFile() {
		
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		return src;
	}

}
