package base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.TestDataUtils;


import Constants.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	static AppiumDriver driver = null;
	AppiumDriverLocalService service;
	
	@BeforeSuite
	public void startServer() {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\mohan\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
	    TestDataUtils.loadProperties(Constants.TESTDATA_PROPERTIES_FILE);
	}
	
	@AfterSuite
	public void stopServer() {
		service.stop();
	}
	
	
	public static AppiumDriver getDriver(String driverName) throws MalformedURLException {

		String name = driverName.toLowerCase();
		switch (name) {
		case "android":
			URL url = new URL("http://127.0.0.1:4723/");
			UiAutomator2Options uio = new UiAutomator2Options();
			
			uio.setDeviceName("RFCW4182FVK");
			uio.setPlatformName("Android");			
			uio.setPlatformVersion("13");
			uio.setAutomationName("UIAutomator2");
			uio.setAppPackage(Constants.PACKAGE_NAME);
			uio.setAppActivity(Constants.ACTIVITY_NAME);
			uio.setNoReset(true);
			//Already installed the apk file on device through the below code
			//uio.setApp(System.getProperty("user.dir") + "/src/test/resources/apkFiles/General-Store.apk");
			
			driver = new AndroidDriver(url, uio);
			break;

		case "ios":
			URL url1 = new URL("http://127.0.0.1:4723/");
			XCUITestOptions xct = new XCUITestOptions();
			xct.setPlatformName("android");
			xct.setDeviceName("Samsung");
			xct.setPlatformVersion("13");
			xct.setAutomationName("XCUITDriver");
			xct.setApp("");
			driver = new IOSDriver(url1, xct);
			break;

		default:
			break;
		}
		return driver;
	}

}
