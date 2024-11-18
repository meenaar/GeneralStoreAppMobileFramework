package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;
import utils.ExtentReportsUtility;
import utils.TestDataUtils;

public class ValidateAddOneProductAndVerifyCart extends BaseTest {

	ExtentReportsUtility report=ExtentReportsUtility.getInstance();
	
	@Test (priority=0)
	public void launchApp_TC1() throws MalformedURLException {
				
		AndroidDriver driver = (AndroidDriver) getDriver("android");		
//		IOSDriver iosDriver =  (IOSDriver) BaseTest.getDriver("ios");
		HomePage hp = new HomePage(driver);		
		System.out.println("General Store Application is launched successfully.");
		report.logTestInfo("General Store Application is launched successfully.");
	}
	
	
	@Test (dependsOnMethods = "launchApp_TC1")
	public void verifyNavToProductsPage_TC4() throws MalformedURLException, InterruptedException {
	
		AndroidDriver driver = (AndroidDriver) getDriver("android");		
//		IOSDriver iosDriver =  (IOSDriver) BaseTest.getDriver("ios");
		
		HomePage hp = new HomePage(driver);
		Thread.sleep(2000);
		hp.scrollAndClickCountry();
		Thread.sleep(2000);
		hp.enterName();
		hp.selectGender();
		hp.clickShopBtn();
		
		ProductsPage pp = new ProductsPage(driver);
		
		String actualPPgTitle = pp.getTitle();
		
		System.out.println("title is :"+ actualPPgTitle);	
		report.logTestInfo("title is :"+ actualPPgTitle);
		
		Assert.assertEquals(actualPPgTitle, TestDataUtils.getProperty("expPPageTitle"), "Page title not matching.");
		
	}	
	
		
	@Test (dependsOnMethods="verifyNavToProductsPage_TC4")
	public void addToCartOneProduct_TC5() throws MalformedURLException {		
		
		AndroidDriver driver = (AndroidDriver) getDriver("android");		
//		IOSDriver iosDriver =  (IOSDriver) BaseTest.getDriver("ios");

		ProductsPage pp = new ProductsPage(driver);
		
		String actualPageTitle = pp.getTitle();
		
		System.out.println("title is :"+ actualPageTitle);		
		report.logTestInfo("title is :"+ actualPageTitle);
		
		Assert.assertEquals(actualPageTitle, TestDataUtils.getProperty("expPPageTitle"), "Page title not matching.");
		
		String product1 = TestDataUtils.getProperty("expProduct1");
		pp.scrollAndFindProduct(product1);
		
		pp.getProductName();
		
		pp.isProductPriceShown();
		
		pp.isAddToCartShown();
		
		String addedCart = pp.clickAddToCartAndVerify();		
		String cartCount = pp.verifyCartCount();
				
		Assert.assertEquals(pp.getProductName(), product1, "Product name to select not matching");			
		Assert.assertEquals(addedCart, TestDataUtils.getProperty("expAddedCart"), "Added to cart test not matching");	
		Assert.assertEquals(cartCount, "1", "cart count is not matching");
		
	}
	
	@Test (dependsOnMethods="addToCartOneProduct_TC5")
	public void verifyCartPageProductPrice_TC6() throws MalformedURLException {	
		
		String actualPageTitle, actualProdName, actualProdPrice;
		
		AndroidDriver driver = (AndroidDriver) getDriver("android");		
//		IOSDriver iosDriver =  (IOSDriver) BaseTest.getDriver("ios");

		ProductsPage pp = new ProductsPage(driver);
		
		pp.clickCartBtn();		
		
		CartPage cp = new CartPage(driver);
		
		actualPageTitle = cp.getTitle();
		
		System.out.println("title is :"+ actualPageTitle);	
		report.logTestInfo("title is :"+ actualPageTitle);	
		
		Assert.assertEquals(actualPageTitle, TestDataUtils.getProperty("expCPageTitle"), "Page title not matching.");
		
		actualProdName = cp.getProductName();		
		actualProdPrice = cp.getProductPrice();
		
		Assert.assertEquals(actualProdName, pp.getProductName(), "product names in products page and cart page not matching.");		
		Assert.assertEquals(actualProdPrice, pp.getProductPrice(), "product price in products page and cart page not matching.");
		
	}
	
	
	@Test (dependsOnMethods="addToCartOneProduct_TC5")	
	public void verifyCartPageTerms_TC08() throws MalformedURLException, InterruptedException{

		AndroidDriver driver = (AndroidDriver) getDriver("android");		
//		IOSDriver iosDriver =  (IOSDriver) BaseTest.getDriver("ios");

		CartPage cp = new CartPage(driver);
		
		System.out.println("From cart page.....");
		
		cp.verifyEmailChkBox();
		
		String actualEmailText = cp.verifyEmailChkBoxText();
		
		cp.longPressTerms();		
		Thread.sleep(4000);
		
		Alert alert = cp.switchToAlert();
		
		System.out.println("******"+cp.getAlertTitle());
		report.logTestInfo("******"+cp.getAlertTitle());
		
		Assert.assertEquals(actualEmailText, TestDataUtils.getProperty("expEmailChkBoxText"), "Email text is not matching.");		
		Assert.assertEquals(cp.getAlertTitle(), TestDataUtils.getProperty("expAlertTitle"), "Alert title is not matching.");		
		
		cp.acceptAlert(alert);
		
	}
	
	
	  @Test (dependsOnMethods="addToCartOneProduct_TC5") public void
	  verifyHybridApp_TC09() throws MalformedURLException{
	  
	  AndroidDriver driver = (AndroidDriver) getDriver("android"); // IOSDriver
	 // iosDriver = (IOSDriver) BaseTest.getDriver("ios");
	  
	  CartPage cp = new CartPage(driver);
	  
	  cp.clickVisitWebsite();	  
	  cp.navigateBackOnMobile();
	  
	  System.out.println("current page title is :"+cp.getTitle());
	  report.logTestInfo("current page title is :"+cp.getTitle());
	  
	  }
	 
}
