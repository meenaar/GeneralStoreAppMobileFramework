package pages;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.Origin;

import base.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BasePage{

	AndroidDriver driver;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public CartPage(IOSDriver driver) {
		super(driver);
	}
	
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/toolbar_title\"]")
	private WebElement pgTitle;
	
	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\"]")
	private WebElement productName;

	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\"]")
	private WebElement productPrice;

	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Jordan 6 Rings\"]")
	private WebElement product1Name;

	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$165.0\"]")
	private WebElement product1Price;
		
	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"PG 3\"]")
	private WebElement product2Name;

	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$110.0\"]")
	private WebElement product2Price;
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/termsButton")
	private WebElement termsText;
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/alertTitle")
	private WebElement alertTitle;
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedBtn;
	
	@AndroidFindBy(xpath ="//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")
	private WebElement emailChkBox;
	
	
	
	public String getTitle(){
		
		System.out.println("Title from Cart page:  "+ pgTitle.getText());		
		
		String title = pgTitle.getText();
		return title;
	}
	
		
	public String getProductName(){
		
		System.out.println("Product Name from Cart page:  "+ productName.getText());				
		String pName = productName.getText();
		return pName;
	}
	
	public String getProductPrice(){
		
		System.out.println("Product Price from Cart page:  "+ productPrice.getText());				
			
		return productPrice.getText();
	}
	
	public String getProduct1Name(){
		
		System.out.println("Product1 Name from Cart page:  "+ product1Name.getText());				
		String pName = product1Name.getText();
		return pName;
	}
	
	public String getProduct1Price(){
		
		System.out.println("Product1 Price from Cart page:  "+ product1Price.getText());				
			
		return product1Price.getText();
	}
	
	public String getProduct2Name(){
		
		System.out.println("Product2 Name from Cart page:  "+ product2Name.getText());				
		String pName = product2Name.getText();
		return pName;
	}
	
	public String getProduct2Price(){
		
		System.out.println("Product2 Price from Cart page:  "+ product2Price.getText());				
			
		return product2Price.getText();
	}
	
	public boolean verifyEmailChkBox() {
		
		System.out.println("********"+emailChkBox.isSelected());
		
	  if (emailChkBox.isDisplayed() && !emailChkBox.isSelected()) {
	  System.out.println("Email checkbox is displayed and not selected."); return
	  true; } else return false;
	 
	
	}

	public String verifyEmailChkBoxText() {
		
		System.out.println("Email =="+emailChkBox.getText());
		
		return emailChkBox.getText();
	}
	
	public void longPressTerms() {
		
		new Actions(driver).clickAndHold(termsText).perform();
	}
		
	public Alert switchToAlert() {
		
		Alert alert = driver.switchTo().alert();
		System.out.println("switched to an alert");
		
		return alert;
	}
	
	public String getAlertTitle() {
		
		System.out.println("extracting text in the  alert");
		String title = alertTitle.getText();
		System.out.println("text is extracted from alert box is ==" + title);
		return title;
	}
	

	public void acceptAlert(Alert alert) {
		alert.accept();
		System.out.println("Alert accepted");
	}
	
	public void clickVisitWebsite(){
	
		if (proceedBtn.isDisplayed() && proceedBtn.isEnabled())
		{
			proceedBtn.click();
			System.out.println("Visit Website button is clicked.");
		}	
		else
		{
			System.out.println("Visit Website button not displayed/enabled.");
		}
	
	}
	
	public void navigateBackOnMobile() {
		
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Point tapPoint = new Point(844, 2270);		
		Sequence tapSeq = new Sequence (finger,1);		
		tapSeq.addAction(finger.createPointerMove(Duration.ofSeconds(0), Origin.viewport(), tapPoint.x, tapPoint.y));
		tapSeq.addAction(finger.createPointerDown(0));
		tapSeq.addAction(finger.createPointerUp(0));		
		driver.perform(Arrays.asList(tapSeq));
	}
}
