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
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class sunmi_setting_test {
	AndroidDriver driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	swipTo st = new swipTo();

	@Test(dataProvider = "dp", timeOut = 9000000, description = "mi.payment.ui.other.LoginActivity")
	public void sunmi_setting_tests(Integer n, String s) throws ParseException {
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
		System.out.println("test begins.......");
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			this.login(driver);
			Thread.sleep(5000);
			int row=0;
			while(row<300)

			{
			row++;
//			String sd=driver.currentActivity();
			driver.findElement(By.xpath("//android.widget.TextView[@text='收银']")).click();
			Thread.sleep(2000);
			this.inputAmount(driver);
//			收款
			driver.findElement(By.id("mi.payment:id/text")).click();
			Thread.sleep(500);
			this.scanAndCancle(driver);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		TouchAction action = new TouchAction(driver);

	}

	private void login(AndroidDriver driver) {
		driver.findElement(By.id("mi.payment:id/edit_op_code")).sendKeys("02");
		driver.findElement(By.id("mi.payment:id/edit_psd")).sendKeys("0000");
		driver.findElement(By.id("mi.payment:id/text_login")).click();

	}
	private void inputAmount(AndroidDriver driver) {
		driver.findElement(By.xpath("//android.widget.TextView[@text='1']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='2']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='3']")).click();
	}
	private void scanAndCancle(AndroidDriver driver) {
		driver.findElement(By.id("mi.payment:id/wechat_sweep_code_layout")).click();
		driver.findElement(By.id("mi.payment:id/back_ib")).click();
	}
	
	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1, "a" }
				// ,new Object[] { 2, "a" }
		};
	}

	@BeforeClass
	public void BeforeClass() {
		String deviceName = "sunmi-p1-P101173T00174";
		String platformVersion = "6.3";
		// String apkName = "com.android.settings.apk";
		String apkName = "SunmiPayMenmt_v1.0.33_ShengPay_release.apk";
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

	@AfterClass
	public void afterClass() {
	}

}
