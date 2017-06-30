package tools;

import io.appium.java_client.android.AndroidDriver;
//实现上下左右滑动页面 
public class swipTo {
	public static void swipeToUp(AndroidDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);
	}

	public static void swipeToDown(AndroidDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		System.out.println(width);
		System.out.println(height);
		driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, during);
	}

	public static void swipeToLeft(AndroidDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		System.out.println(width);
		System.out.println(height);
		driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, during);
	}

	public static void swipeToRight(AndroidDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		System.out.println(width);
		System.out.println(height);
		driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, during);
	}
}
