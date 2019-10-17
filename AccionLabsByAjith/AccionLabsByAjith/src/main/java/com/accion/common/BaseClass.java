package com.accion.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * 
 * This is the base class for AccionLab test and initializing all
 * files as config.properties, xpath properties file, log4j file, webdriver,
 * implicit waits etc
 * 
 * @author Ajith A S
 * 
 */

public class BaseClass {

	public static Properties config = null;
	public static Logger APPLICATION_LOG = null;
	public static Properties objectRepository = null;
	public static WebDriver driver = null;
	public static boolean isLoggedIn = false;
	public static Actions actions = null;
	public static JavascriptExecutor js = null;
	public static FluentWait<WebDriver> fwait = null;
	public static FluentWait<WebDriver> wait = null;
	public static FluentWait<WebDriver> waitForLessPolling = null;
	public static FluentWait<WebDriver> waitForLongTime = null;
	public static String parentWindow;
	private static BrowserTypes browserType;
	public static String Login_URL = null;
	public FileInputStream fn = null;

	public void initialize() throws IOException {
		/**
		 * No need initialize every time, if driver is null then only will initialize
		 */
		if (driver == null) {
			try {
				// Config Property file
				config = new Properties();
				String environment = System.getProperty("project.env");
				String machinename = System.getProperty("machine");
				fn = null;
				System.out.println(environment);

				if (environment.equalsIgnoreCase("TEST_ENV")) {

					fn = new FileInputStream(System.getProperty("user.dir")
							+ "/src/test/resources/properties/accionProperty.properties");

				} else if (environment.equalsIgnoreCase("DEV_ENV")) {

					fn = new FileInputStream(System.getProperty("user.dir")
							+ "/src/test/resources/properties/accionProperty.properties");
				}
				config.load(fn);
				System.out.println(config.getProperty("testSiteName"));
				System.out.println(config.getProperty("browser"));
				System.out.println(config.getProperty("binaryRootFolder"));

				// Xpath Property File
				objectRepository = new Properties();
				fn = new FileInputStream(System.getProperty("user.dir")
						+ "/src/test/resources/objectRepository/ObjectRepository.properties");
				objectRepository.load(fn);

				// set up browse type
				setUpBrowseType();

				/*
				 * if (machinename.equalsIgnoreCase("LOCAL")) { driver = loadWebDriver(); }
				 */

				driver = loadWebDriver();

				// Wait for 30 seconds to load next element
				wait = new FluentWait<WebDriver>(driver).withTimeout(300, TimeUnit.SECONDS)
						.pollingEvery(500, TimeUnit.MILLISECONDS)
						.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

				// offShore team initialized the below two properties
				waitForLessPolling = new FluentWait<WebDriver>(driver).withTimeout(100, TimeUnit.SECONDS)
						.pollingEvery(1, TimeUnit.SECONDS)
						.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
				waitForLongTime = new FluentWait<WebDriver>(driver).withTimeout(1000, TimeUnit.SECONDS)
						.pollingEvery(500, TimeUnit.MILLISECONDS)
						.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

				driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
				driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				actions = new Actions(driver);

			} catch (Throwable t) {
				System.out.println(t.getMessage());

			}
		}
	}

	// To initialize the logs through out the application
	public void initLogs(Class<?> class1) throws Exception {
		String fileName = null;
		fileName = System.getProperty("user.dir") + "//target//Logs";
		clearFile(fileName);
		FileAppender appender = new FileAppender();
		// configure the appender here, with file location, etc
		appender.setFile(fileName + "//SeleniumLogs_" + ".log");
		appender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		appender.setAppend(false);
		appender.activateOptions();

		APPLICATION_LOG = Logger.getRootLogger();
		APPLICATION_LOG.setLevel(Level.INFO);
		APPLICATION_LOG.addAppender(appender);
	}

	// To delete old log file
	public static void clearFile(String fileLocation) throws Exception {
		File file = new File(fileLocation);
		String[] myFiles;
		if (file.isDirectory()) {
			myFiles = file.list();
			for (int i = 0; i < myFiles.length; i++) {
				File myFile = new File(file, myFiles[i]);
				myFile.delete();
			}
		}
	}

	public static void setUpBrowseType() {
		for (BrowserTypes browser : BrowserTypes.values()) {
			if (browser.toString().toLowerCase().equals(config.getProperty("browser").toLowerCase())) {
				browserType = browser;
			}
		}
	}

	private static DesiredCapabilities generateDesiredCapabilities(BrowserTypes capabilityType) {
		DesiredCapabilities capabilities;

		switch (capabilityType) {
		case CHROME:
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
			HashMap<String, String> chromePreferences = new HashMap<String, String>();
			chromePreferences.put("profile.password_manager_enabled", "false");
			chromePreferences.put("handlesAlerts", "true");
			capabilities.setCapability("chrome.prefs", chromePreferences);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability("javascriptEnabled", "true");
			capabilities.setCapability("takesScreenshot", "true");
			break;
		case FIREFOX:
			capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("firefox");
			capabilities.setPlatform(Platform.VISTA);
			HashMap<String, String> firefoxPreferences = new HashMap<String, String>();
			firefoxPreferences.put("profile.password_manager_enabled", "false");
			firefoxPreferences.put("handlesAlerts", "true");
			capabilities.setCapability("chrome.prefs", firefoxPreferences);
			capabilities.setCapability("javascriptEnabled", "true");
			capabilities.setCapability("takesScreenshot", "true");
			break;

		case CHROME_MOBILE:
			capabilities = DesiredCapabilities.android();
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
			capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "My Mobile");
			capabilities.setCapability(MobileCapabilityType.VERSION, "9");
			capabilities.setCapability(MobileCapabilityType.UDID, "M1805D1SI");
			break;

		default:
			capabilities = DesiredCapabilities.htmlUnit();
			capabilities.setCapability("javascriptEnabled", "true");
		}
		return capabilities;
	}

	@SuppressWarnings({ "incomplete-switch", "deprecation", "rawtypes" })
	// private static WebDriver loadWebDriver()
	public WebDriver loadWebDriver() throws MalformedURLException {
		System.out.println("Current Operating System: " + System.getProperties().getProperty("os.name"));
		System.out.println("Current Architecture: " + System.getProperties().getProperty("os.arch"));
		System.out.println("Current Browser Selection: " + browserType);

		// Load stand alone executable if required
		switch (browserType) {
		case CHROME:
			if (System.getProperties().getProperty("os.arch").toLowerCase().equals("x86_64")
					|| System.getProperties().getProperty("os.arch").toLowerCase().equals("amd64")) {

				if (System.getProperties().getProperty("os.name").toLowerCase().contains("windows")) {
					System.setProperty("webdriver.chrome.driver",
							config.getProperty("binaryRootFolder") + "/googlechrome/chromedriver.exe");
				} else if (System.getProperties().getProperty("os.name").toLowerCase().contains("linux")) {
					System.setProperty("webdriver.chrome.driver",
							config.getProperty("binaryRootFolder") + "/googlechrome/chromedriver");
				}
			}
			
			
			switch (browserType) {
			case FIREFOX:
				return new FirefoxDriver(generateDesiredCapabilities(browserType));
				
			case CHROME:
				return new ChromeDriver(generateDesiredCapabilities(browserType));
			
			case CHROME_MOBILE:

			URL url = new URL("http://127.0.0.1.4723/wd/hub");
			return new AppiumDriver(url, generateDesiredCapabilities(browserType));
		}
	}
	
	return driver;
	
	}
	

	public void close() {

		driver.quit();
	}
}