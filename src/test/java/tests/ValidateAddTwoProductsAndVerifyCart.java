package tests;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import pages.CartPage;
import pages.HomePage;
import pages.ProductsPage;
import utils.ExtentReportsUtility;
import utils.TestDataUtils;

public class ValidateAddTwoProductsAndVerifyCart extends BaseTest{

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
	public void addToCartTwoProducts_TC7() throws MalformedURLException, InterruptedException {		
		
		String actualPPgTitle, addedCart, cartCount, actualCPgTitle;
		String expProd1 = TestDataUtils.getProperty("expProduct1");
		String expProd2 = TestDataUtils.getProperty("expProduct2");
		
		AndroidDriver driver = (AndroidDriver) getDriver("android");		
	//  IOSDriver iosDriver =  (IOSDriver) BaseTest.getDriver("ios");

		ProductsPage pp = new ProductsPage(driver);
		
		actualPPgTitle = pp.getTitle();
		
		System.out.println("title is :"+ actualPPgTitle);
		report.logTestInfo("title is :"+ actualPPgTitle);
		
		Assert.assertEquals(actualPPgTitle, TestDataUtils.getProperty("expPPageTitle"), "Page title not matching.");
		
		//Scroll to find the product "Jordan 6 Rings" 
	//	String item1 ="Jordan 6 Rings";
		pp.scrollAndFindProduct(expProd1);
		
		String actualProd1NamePP = pp.getProductName();
		String actualProd1PricePP =pp.getProductPrice();
		
		pp.isProductPriceShown();		
		pp.isAddToCartShown();
		
		addedCart = pp.clickAddToCartAndVerify();		
		cartCount = pp.verifyCartCount();
		
		Assert.assertEquals(actualProd1NamePP, expProd1, "Product1 name to select not matching");	
		Assert.assertEquals(addedCart, TestDataUtils.getProperty("expAddedCart"), "Added to cart test not matching");
		Assert.assertEquals(cartCount, "1", "cart count is not matching");
		
		//Scroll to find the product "PG 3" 
		//String item2 ="PG 3";
		pp.scrollAndFindProduct(expProd2);		
		Thread.sleep(3000);
		
		String actualProd2NamePP = pp.getProduct2Name();	
		String actualProd2PricePP =pp.getProduct2Price();
		
		pp.isProduct2PriceShown();		
		pp.isAddToCartShown();
		
		addedCart = pp.clickAddToCartAndVerify();
		cartCount = pp.verifyCartCount();
		
		Assert.assertEquals(actualProd2NamePP, expProd2, "Product2 name to select not matching");		
		Assert.assertEquals(addedCart, TestDataUtils.getProperty("expAddedCart"), "Added to cart test not matching");		
		Assert.assertEquals(cartCount, "2", "cart count is not matching");
		
		pp.clickCartBtn();
				
		//verification of both products in Cart page
		CartPage cp = new CartPage(driver);
		
		actualCPgTitle = cp.getTitle();
		
		System.out.println("title is :"+ actualCPgTitle);
		report.logTestInfo("title is :"+ actualCPgTitle);
		
		
		Assert.assertEquals(actualCPgTitle, TestDataUtils.getProperty("expCPageTitle"), "Page title not matching.");		
		Assert.assertEquals(cp.getProduct1Name(), actualProd1NamePP, "product1 names in products page and cart page not matching.");		
		Assert.assertEquals(cp.getProduct1Price(), actualProd1PricePP, "product1 price in products page and cart page not matching.");
		Assert.assertEquals(cp.getProduct2Name(), actualProd2NamePP, "product2 names in products page and cart page not matching.");		
		Assert.assertEquals(cp.getProduct2Price(), actualProd2PricePP, "product2 price in products page and cart page not matching.");

	}
	
	
}
