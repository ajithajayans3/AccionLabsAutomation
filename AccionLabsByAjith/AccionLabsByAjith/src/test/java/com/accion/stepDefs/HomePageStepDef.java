package com.accion.stepDefs;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.accion.common.BaseClass;
import com.accion.pages.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * 
 * @author Ajith A S
 * 
 */

@Test
public class HomePageStepDef extends BaseClass {
	
	public static Logger logger = Logger.getLogger("HomePageStepDef");

	HomePage homePage = new HomePage();

	@Given("^user is able to launch the application$")
	public void user_is_able_to_launch_the_application() throws Throwable {
		homePage.appLaunch();
	}

	@Then("^verify Library Header on home screen$")
	public void verify_Library_Header_on_home_screen() throws Throwable {
		homePage.verifyHeader();
	}

	@And("^count the hyperlinks available in the body$")
	public void count_the_hyperlinks_available_in_the_body() throws Throwable {
		homePage.printTheLinks();
	}

}