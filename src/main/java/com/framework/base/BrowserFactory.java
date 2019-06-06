package com.framework.base;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.framework.exceptions.DriverNotInitializedException;

public class BrowserFactory {
	public static Logger log = Logger.getLogger(BrowserFactory.class);
	private static WebDriver Driver;
	  //public static final String USERNAME = "farheen8";
	 // public static final String AUTOMATE_KEY = "oPxNSDVBm9MqYWbYyztD";
	  //public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	private BrowserFactory() {
	}

	// To initialize the Driver
	public static void initializeBrowser(Browser browser, String appUrl) throws MalformedURLException {
		if (Driver == null) {
			switch (browser) {
			case firefox:
				WebDriverManager.firefoxdriver().setup();
				Driver = new FirefoxDriver();
				break;
			case chrome:
				/* System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")
				 + "/src/test/resources/chromedriver.exe");*/
				WebDriverManager.chromedriver().setup();
				Map<String, Object> prefs = new HashMap<String, Object>();
				String download_folder = "src/test/resources/testdata/downloads";
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				prefs.put("profile.default_content_settings.popups", 0);
				prefs.put("download.default_directory", System.getProperty("user.dir") + "/" + download_folder);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("disable-infobars");
				Driver = new ChromeDriver(options);
				
				/*DesiredCapabilities caps = new DesiredCapabilities();
			    caps.setCapability("browser", "Chrome");
			    caps.setCapability("browser_version", "74.0");
			    caps.setCapability("os", "Windows");
			    caps.setCapability("os_version", "10");
			    caps.setCapability("resolution", "1024x768");
			   // caps.setCapability("name", "Bstack-[Java] Sample Test");  class name was JavaSample
			    Driver = new RemoteWebDriver(new java.net.URL(URL), caps);
			    ((RemoteWebDriver) Driver).setFileDetector(new LocalFileDetector());*/
			    
			    
				break;
			}

			Driver.manage().window().maximize();
			Driver.get(appUrl);
			
			log.info("Browser Initialized...");
		}
	}

	public static WebDriver getDriver() throws DriverNotInitializedException {
		if (Driver != null) {
			return Driver;
		} else {
			throw new DriverNotInitializedException("WebDriver not initialized");
		}
	}

	public static void closeBrowser() {
		if (Driver != null) {
			Driver.close();
			log.info("Browser closed...");
		}
	}

	public static void quitDriver() {
		//if (Driver != null) {
			//Driver.quit();
			//log.info("Driver shut down.. All Browsers closed...");
		//}
	}
}