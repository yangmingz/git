package appuimtest;

import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import tools.swipTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

//测试sumpipay稳定性初步
public class pay_Uitest {
	public  static AndroidDriver driver;
	public static DesiredCapabilities capabilities = new DesiredCapabilities();
	swipTo st = new swipTo();

	@Test(timeOut = 60000000, description = "mi.payment.ui.other.LoginActivity")
	public void sunmi_setting_tests() throws ParseException {
		/*
		 * driver.sendKeyEvent(26); // 按power键锁屏 sleep(3000);
		 * driver.sendKeyEvent(26); // 按power键点亮屏幕
		 * 
		 * driver.tap(1, 540, 960, 500); // 触摸屏幕中间，显示图案解锁框 sleep(2000);
		 * 
		 * //图案解锁 TouchAction action = new TouchAction(driver);
		 * action.press(215, 870).moveTo(540, 870).moveTo(862, 870) .moveTo(540,
		 * 1195).moveTo(215, 1195).moveTo(215, 1518) .moveTo(540,
		 * 1518).moveTo(862, 1518).moveTo(862, 1195) .release().perform();
		 */
		TouchAction action = new TouchAction(driver);
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			Thread.sleep(1000);
			System.err.println("启动等待时间...");
//				app有点buG，尝试签到5次
			 
				login(driver);
				Thread.sleep(200);

//			退货 
				
				for (int i = 0; i < 1000; i++) {
					try {
						Runtime.getRuntime().exec("cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell input tap 200 300");
						Thread.sleep(100);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					返回按钮
					driver.pressKeyCode(111);
					Thread.sleep(100);
				}
			driver.quit();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
		String deviceName = "sunmi-p1-V20116BQ00066";
		String platformVersion = "6.3";
		// String apkName = "com.android.settings.apk";
		String apkName = "app-debug.apk";
		String appActivity = "mi.payment.ui.other.LoginActivity";
		String AppiumServerIP = "http://127.0.0.1:4723/wd/hub";
		// 文件路径,设置参数等
		File apk = new File(System.getProperty("user.dir") + File.separator + "apps" + File.separator + apkName);
		System.err.println("apk file path:" + apk);
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("app", apk);
		capabilities.setCapability("appActivity", appActivity);
		capabilities.setCapability("noSign", true);
		capabilities.setCapability("noReset", true);
	}

	public void login(AndroidDriver driver) {
		driver.findElement(By.id("mi.payment:id/edit_op_code")).clear();
		driver.findElement(By.id("mi.payment:id/edit_op_code")).sendKeys("02");
		driver.findElement(By.id("mi.payment:id/edit_psd")).clear();
		driver.findElement(By.id("mi.payment:id/edit_psd")).sendKeys("0000");		
		driver.findElement(By.id("mi.payment:id/text_login")).click();
	}

}
