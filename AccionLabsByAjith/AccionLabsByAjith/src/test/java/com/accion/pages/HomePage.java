package com.accion.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.accion.common.BaseClass;

/**
 * 
 * @author Ajith A S
 * 
 */

public class HomePage extends BaseClass {

	public static Logger logger = Logger.getLogger("HomePage");

	@FindBy(xpath = "//span[contains(text(),'Library')]")
	public WebElement header;

	@FindBy(xpath = "//a[contains(@id,'practiceTestLink')]")
	public List<WebElement> hyperLinks;

	public void appLaunch() {

		System.out.println("Running appLaunch method now");

		try {

			Thread.sleep(2000);

			driver.get(config.getProperty("testSiteName"));
			logger.info("The application launched successfully");

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void verifyHeader() {

		System.out.println("Running verifyHeader method now");

		try {

			Thread.sleep(2000);
			/*
			 * String headerName =
			 * driver.findElement(By.xpath(objectRepository.getProperty("libraryHeader"))).
			 * getText();
			 */
			String headerName = driver.findElement(By.xpath("//span[contains(text(),'Library')]")).getText();
			Assert.assertEquals("Library", headerName);
			logger.info("Header verification completed and the header name is " + headerName);
			System.out.println("Header verification completed and the header name is " + headerName);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void printTheLinks() {

		System.out.println("Running printTheLinks method now");
		/*
		 * hyperLinks =
		 * driver.findElements(By.xpath(objectRepository.getProperty("hyperLinks")));
		 */
		hyperLinks = driver.findElements(By.xpath("//a[contains(@id,'practiceTestLink')]"));

		for (WebElement links : hyperLinks) {

			logger.info(links.getText() + " - " + links.getAttribute("href"));
			System.out.println(links.getText() + " - " + links.getAttribute("href"));

		}
	}
}
