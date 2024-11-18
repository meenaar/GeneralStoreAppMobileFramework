package utils;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.Origin;

public class ActionUtils {
	public static final Origin VIEW = Origin.viewport();
	public static final Duration ZERO_SEC = Duration.ZERO;
	
	public static void pinchZoom(AndroidDriver driver) {
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);
		swipe.addAction(finger.createPointerMove(ZERO_SEC, VIEW,  500, 1000));
		swipe.addAction(finger.createPointerDown(0));
		swipe.addAction(finger.createPointerMove(Duration.ofSeconds(3), VIEW, 500, 500));
		swipe.addAction(finger.createPointerUp(0));
		
		PointerInput finger2 = new PointerInput(Kind.TOUCH, "finger2");
		Sequence swipe2 = new Sequence(finger2, 1);
		swipe2.addAction(finger2.createPointerMove(ZERO_SEC, VIEW,  400, 1200));
		swipe2.addAction(finger2.createPointerDown(0));
		swipe2.addAction(finger2.createPointerMove(Duration.ofSeconds(3), VIEW, 400, 2000));
		swipe2.addAction(finger2.createPointerUp(0));
		driver.perform(Arrays.asList(swipe, swipe2));
	}
	
	public static void dragAndDrop(AndroidDriver driver) {
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Sequence dnd = new Sequence(finger, 1);
		dnd.addAction(finger.createPointerMove(ZERO_SEC, VIEW, 0,0));
		dnd.addAction(finger.createPointerDown(0));
		dnd.addAction(new Pause(finger, Duration.ofSeconds(2)));
		dnd.addAction(finger.createPointerMove(Duration.ofSeconds(3), VIEW, 500, 500));
		dnd.addAction(finger.createPointerUp(0));
		driver.perform(Arrays.asList(dnd));
	}
	
	public static void doubleTap(AndroidDriver driver) {
		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Sequence dt = new Sequence(finger, 1);
		dt.addAction(finger.createPointerMove(ZERO_SEC, VIEW, 0,0));
		dt.addAction(finger.createPointerDown(0));
		dt.addAction(finger.createPointerUp(0));
		dt.addAction(finger.createPointerDown(0));
		dt.addAction(finger.createPointerUp(0));
		driver.perform(Arrays.asList(dt));
	}
	
	

}
