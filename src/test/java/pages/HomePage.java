package pages;

import org.openqa.selenium.WebElement;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BasePage {
	
	AndroidDriver driver;
	
	public HomePage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
	}
	public HomePage(IOSDriver driver) {
		super(driver);
	}
	
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement pageTitle;
	
	@AndroidFindBy(xpath ="//android.widget.TextView[@text=\"Select the country where you want to shop\"]")
	private WebElement dropdownLabel;
	
	@AndroidFindBy(xpath = "//android.widget.Spinner[@resource-id=\"com.androidsample.generalstore:id/spinnerCountry\"]")
	private WebElement ddCountry;
	
	@AndroidFindBy(id = "android:id/text1")
	private WebElement ddCountryName;
	
	@AndroidFindBy(xpath ="//android.widget.TextView[@text=\"Your Name\"]")
	private WebElement nameLabel;
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/nameField")
	//@AndroidFindBy(xpath ="//android.widget.EditText[text(),\"Enter name here\"]")
	private WebElement placeHolderLbl;
			
	@AndroidFindBy(xpath ="//android.widget.TextView[@text=\"Gender\"]")
	private WebElement genderLabel;
	
	
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	
	@AndroidFindBy(xpath ="//android.widget.Toast[@text=\"Please enter your name\"]")
	private WebElement toastMsg;
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleGenderEle;
	
	
	public String getTitle(){
		
		System.out.println("Title from Home page:  "+ pageTitle.getText());		
		
		String title = pageTitle.getText();
		return title;
	}
	
	public String getDropdownLabel(){
		
		System.out.println("\nLabel from Home page:  "+ dropdownLabel.getText());		
		
		String label = dropdownLabel.getText();
		return label;
	}
	
	public String getSelectedCountryName(){
		
	//	System.out.println("dd locator*********"+ddCountry.getAttribute("value"));
		
		System.out.println("text locator *********"+ddCountryName.getText());
//		Select select = new Select(ddCountry);
		
		String actualSelCountry = ddCountryName.getText();
		
		System.out.println("\nActual selected country is : "+actualSelCountry);
	

		return actualSelCountry;
	}
	
	public String getNameLabel(){
		
		System.out.println("\nLabel from Home page:  "+ nameLabel.getText());		
		
		String label = nameLabel.getText();
		return label;
	}
	
	
	public String getPlaceHolderLbl(){
		
		System.out.println("\nPlace Holder Label from Home page:  "+ placeHolderLbl.getText());		
		
		String label = placeHolderLbl.getText();
		return label;
	}
	
	public String getGenderLabel(){
		
		System.out.println("\nLabel from Home page:  "+ genderLabel.getText());		
		
		String label = genderLabel.getText();
		return label;
	}
	
	
	public String getBtnLabel(){
		
		System.out.println("\nLabel from Home page:  "+ shopButton.getText());		
		
		String btnLabel = shopButton.getText();
		
		return btnLabel;
	}
	
	public void isBtnClickable() {
		if (shopButton.isEnabled()) {			
			
			System.out.println("Yes, "+shopButton.getText()+"button is enabled");
		
	//		extentReportUtility.reportTestInfo(objectName+ " is clicked");
		}
		else{
			System.out.println(shopButton.getText()+"button is not enabled");
		//	extentReportUtility.reportTestFailed(objectName+" is not clicked");
		}
	}
	
	public String getToastMsg() {
		
		System.out.println("Error msg is : "+toastMsg.getText());
		
		return toastMsg.getText();
		
	}
	
	public String verifyMandateFields() {
		
		String toastMsg ="";
		if (placeHolderLbl.getText().equals("Enter name here") && shopButton.isEnabled()){
	
			shopButton.click();
			System.out.println("button is clicked");
			toastMsg = getToastMsg();
		}
		return toastMsg;
	}
	
	public void scrollAndClickCountry() {
		

		 String uiSelector = "new UiSelector().textMatches(\"India\")";

		 String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("+ uiSelector + ");";

		WebElement scrolledCountryEle = driver.findElement(AppiumBy.androidUIAutomator(command));

		scrolledCountryEle.click();

	}
	
	
	public void enterName(){
		
		placeHolderLbl.sendKeys("Meena");
		
		System.out.println("\nEntered Name from Home page:  "+ placeHolderLbl.getText());		
	}
	
	
	public void selectGender() {
		
		femaleGenderEle.click();
	}
	
	
	public void clickShopBtn() {
		
		if (shopButton.isEnabled()) {			
			shopButton.click();
			System.out.println("button is clicked");			
		}
		else {			
			System.out.println("button is not enabled");
		}
		
		
	}

	
	
	

}
