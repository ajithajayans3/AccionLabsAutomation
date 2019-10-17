package com.accion.main;

import java.io.IOException;
import java.sql.Driver;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.accion.common.BaseClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "./src/test/resources/features", glue = {"com.accion.stepDefs"}, monochrome = true)

public class TestRunner extends AbstractTestNGCucumberTests {

	BaseClass baseClass = new BaseClass();
	public static Logger logger = Logger.getLogger("TestRunner");
	@BeforeClass
	public void init() throws IOException {
		logger.info("Initializing BaseClass");
		baseClass.initialize();
	}
	
	@BeforeTest
	public void initLogs() {
		try {
			baseClass.initLogs(this.getClass());

		} catch (Exception e) {
			logger.info("Error in Initial Log in initLogs " + e.getMessage());
		}
	}
	
	@AfterClass
	public void close() throws IOException {
		logger.info("Closing the browser");
		baseClass.close();
	}
	
}
