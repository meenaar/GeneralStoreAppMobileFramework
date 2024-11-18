package tests;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ExtentReportsUtility;

import utils.TestDataUtils;
import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import pages.HomePage;


public class HomeTest extends BaseTest{
	
	ExtentReportsUtility report=ExtentReportsUtility.getInstance();
	
	@Test (priority=0)
	public void launchApp_TC1() throws MalformedURLException {		
		
		AndroidDriver driver = (AndroidDriver) getDriver("android");		
//		IOSDriver iosDriver =  (IOSDriver) BaseTest.getDriver("ios");
		
		System.out.println("General Store Application is launched successfully.");
		report.logTestInfo("General Store Application is launched successfully.");
	}
	
	
	@Test (dependsOnMethods = "launchApp_TC1")
	public void verifyHomePageFields_TC2() throws MalformedURLException, InterruptedException {
		
		AndroidDriver driver = (AndroidDriver) getDriver("android");		
//		IOSDriver iosDriver =  (IOSDriver) BaseTest.getDriver("ios");
		
		String actualTitle, actualDDLabel, actualCountryName, actualNameLbl, actualPlaceHolderLbl, actualBtnLbl;
		
		HomePage hp = new HomePage(driver);
		
		Thread.sleep(5000);
		
		  actualTitle = hp.getTitle();
		  
		  actualDDLabel =  hp.getDropdownLabel();	
		  System.out.println("Actual dd label is :"+ actualDDLabel);		  
		  report.logTestInfo("Actual dd label is :"+ actualDDLabel);		
		  
		  actualCountryName=   hp.getSelectedCountryName();		  
		  System.out.println("Actual selected country is :"+actualCountryName); 
		  report.logTestInfo("Actual selected country is :"+actualCountryName);
		  	
		  actualNameLbl = hp.getNameLabel();		  
		  System.out.println("Actual Name Label is :"+ actualNameLbl);		 
		  report.logTestInfo("Actual Name Label is :"+ actualNameLbl);	
		  
		  actualPlaceHolderLbl = hp.getPlaceHolderLbl();
		  System.out.println("Actual place holder name field is :"+ actualPlaceHolderLbl);
		  report.logTestInfo("Actual place holder name field is :"+ actualPlaceHolderLbl);
			
		  actualBtnLbl = hp.getBtnLabel();
		  System.out.println("Actual btn label is :"+ actualBtnLbl);
		  report.logTestInfo("Actual btn label is :"+ actualBtnLbl);
		  
		  Assert.assertEquals(actualTitle, TestDataUtils.getProperty("expHomePageTitle"), "Home page Title not matching.");		  
		  Assert.assertEquals(actualDDLabel, TestDataUtils.getProperty("expCountryLabel"), "Dropdown label not matching.");		  
		  Assert.assertEquals(actualCountryName, TestDataUtils.getProperty("expSelectedCountry"), "Selected Country not matching.");			
		  Assert.assertEquals(actualNameLbl, TestDataUtils.getProperty("expNameLabel"), "Name text box label not matching.");
		  Assert.assertEquals(actualPlaceHolderLbl, TestDataUtils.getProperty("expNamePlaceHolder"), "Name Place Holder not matching.");			
		  Assert.assertEquals(actualBtnLbl, TestDataUtils.getProperty("expBtnLabel"), "Button label not matching.");
			
		  hp.isBtnClickable();		  
	}
	
	
	@Test (dependsOnMethods = "launchApp_TC1")
	public void verifyMandatoryFields_TC3() throws MalformedURLException {	
	
		AndroidDriver driver = (AndroidDriver) getDriver("android");		
//		IOSDriver iosDriver =  (IOSDriver) BaseTest.getDriver("ios");
		
		HomePage hp = new HomePage(driver);
		
		String actualErrMsg = hp.verifyMandateFields();
		
		System.out.println("Error msg  : "+actualErrMsg);		
		report.logTestInfo("Error msg  : "+actualErrMsg);
		
		Assert.assertEquals(actualErrMsg, TestDataUtils.getProperty("expToastMsg"), "Error message not matching.");			
	}
	
}
