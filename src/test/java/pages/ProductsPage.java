package pages;

import org.openqa.selenium.WebElement;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utils.ExtentReportsUtility;

public class ProductsPage extends BasePage{
	
	AndroidDriver driver;
	ExtentReportsUtility report=ExtentReportsUtility.getInstance();
	
	public ProductsPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public ProductsPage(IOSDriver driver) {
		super(driver);
	}
	
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\"]")
	private WebElement pgTitle;
	
	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Jordan 6 Rings\"]")
	private WebElement productName;

	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$165.0\"]")
	private WebElement productPrice;
	
	@AndroidFindBy(xpath ="(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")
	private WebElement addToCart;
	
	@AndroidFindBy(xpath ="(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\" and @text=\"ADDED TO CART\"])")
	private WebElement addedToCart;
	
	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/counterText\"]")
	private WebElement cartCount;
	
	@AndroidFindBy(xpath ="//android.widget.ImageButton[@resource-id=\"com.androidsample.generalstore:id/appbar_btn_cart\"]")
	private WebElement cartBtn;
	
	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"PG 3\"]")
	private WebElement product2Name;

	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$110.0\"]")
	private WebElement product2Price;
	
	
	
	
	
	public String getTitle(){
		
		System.out.println("Title from Products page:  "+ pgTitle.getText());		
		
		String title = pgTitle.getText();
		return title;
	}
	
	public void scrollAndFindProduct(String item) {
		

		 String uiSelector = "new UiSelector().textMatches(\""+item+"\")";

		 String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("+ uiSelector + ");";

		WebElement scrolledList = driver.findElement(AppiumBy.androidUIAutomator(command));

	}
	
	
	public String getProductName(){
		
		System.out.println("Product Name from Products page:  "+ productName.getText());				
		String pName = productName.getText();
		return pName;
	}
	
	public String getProductPrice(){
		
		System.out.println("Product price from Products page:  "+ productPrice.getText());				
		
		return productPrice.getText();
	}

	public boolean isProductPriceShown(){
		
		System.out.println("Product Price from Products page:  "+ productPrice.getText());				
			
		if (productPrice.isDisplayed()) {
			System.out.println("Product Price is displayed.");
			report.logTestInfo("Product Price is displayed.");
			return true;
		}else {
			System.out.println("Product Price is not displayed.");
			report.logTestInfo("Product Price is not displayed.");
			return false;
		}
	}
	
	public void isAddToCartShown() {
		
		if (addToCart.isDisplayed() && addToCart.isEnabled())
		{
			System.out.println("AddToCart button displayed.");
			report.logTestInfo("AddToCart button displayed.");
		}	
		else
		{
			System.out.println("AddToCart button not displayed.");
			report.logTestInfo("AddToCart button not displayed.");
		}
	}
	
	public String clickAddToCartAndVerify() {
		
		String addedCart="";
		
		if (addToCart.isDisplayed() && addToCart.isEnabled())
		{
			addToCart.click();
			
			System.out.println("AddToCart button is clicked.");
			report.logTestInfo("AddToCart button is clicked.");
			
			addedCart = addedToCart.getText();
			
		}		
		return addedCart;
	}
	
	public String verifyCartCount() {
		
		System.out.println("Cart count is :"+cartCount.getText());	
		
		return cartCount.getText();
	}
	
	public void clickCartBtn() {
		
		if (cartBtn.isDisplayed() && cartBtn.isEnabled())
		{
			cartBtn.click();
			
			System.out.println("Cart button is clicked.");
			report.logTestInfo("Cart button is clicked.");
		}	
		else
		{
			System.out.println("Cart button is not displayed.");
			report.logTestInfo("Cart button is not displayed.");
		}
	}
	
	public String getProduct2Name(){
		
		System.out.println("Product2 Name from Products page:  "+ product2Name.getText());				
		String pName = product2Name.getText();
		return pName;
	}
	
	public String getProduct2Price(){
		
		System.out.println("Product2 price from Products page:  "+ product2Price.getText());				
		
		return product2Price.getText();
	}
	
	public boolean isProduct2PriceShown(){
		
		System.out.println("Product2 Price from Products page:  "+ product2Price.getText());				
			
		if (product2Price.isDisplayed()) {
			System.out.println("Product2 Price is displayed.");
			report.logTestInfo("Product2 Price is displayed.");
			return true;
		}else {
			System.out.println("Product2 Price is not displayed.");
			report.logTestInfo("Product2 Price is not displayed.");
			return false;
		}
	}
	
}
