package auto.Ui;

import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
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
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class testsunmiautotest {
	AndroidDriver driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	swipTo st = new swipTo();
	@Test(dataProvider = "dp", timeOut = 90000, description = "com.sunmi.userfeedback.ui.activity.SplashActivity")
	public void testsunmiautotest(String n, String s) throws ParseException {
		String res = null;
		/*
		 * driver.sendKeyEvent(26); // 按power键锁屏 sleep(3000);
		 * driver.sendKeyEvent(26); // 按power键点亮屏幕 driver.tap(1, 540, 960, 500);
		 * // 触摸屏幕中间，显示图案解锁框 sleep(2000); //图案解锁 TouchAction action = new
		 * TouchAction(driver); action.press(215, 870).moveTo(540,
		 * 870).moveTo(862, 870) .moveTo(540, 1195).moveTo(215,
		 * 1195).moveTo(215, 1518) .moveTo(540, 1518).moveTo(862,
		 * 1518).moveTo(862, 1195) .release().perform();
		 */
		TouchAction action = new TouchAction(driver);
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			WebElement e = driver.findElement(By.xpath("//android.widget.Button[@text='保存 密钥']"));
			System.out.println(e.getText());
			e.click();
			WebElement e1 = driver.findElement(By.xpath("//android.widget.ScrollView[@index='0']"));
			if (e1.isDisplayed()) {
				res = saveKeys(driver);
			}
			Assert.assertEquals(res, "0");
			System.err.println("保存秘钥返回值" + res);
			driver.quit();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String saveKeys(AndroidDriver driver) {
		String result = null;
		/*
		 * 点击菜单栏去掉键盘java_client3.0版本以后使用pressKeyCode方法，之前的版本使用sendKeyEvent方法 1.
		 * 返回：driver.pressKeyCode(AndroidKeyCode.BACK) 2.
		 * HOME键：driver.pressKeyCode(AndroidKeyCode.Home)
		 */
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_key_index")).sendKeys("1");
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_key_type")).sendKeys("3");
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_deciphering_key_index")).sendKeys("1");
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_encryptType")).sendKeys("3");
		swipTo.swipeToUp(driver, 300);
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_mkey")).sendKeys("111111");
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_length")).sendKeys("6");
		driver.findElement(By.name("保存密钥")).click();
		WebElement e=driver.findElement(By.id("com.blanks.test.demo:id/tv_save_key_result"));
		result = e.getText();
		return result;
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "s", "a" }
				// ,new Object[] { 2, "a" }
		};
	}

	@BeforeClass
	public void beforeClass() {
		String deviceName = "sunmi-p1-V20116BQ00066";
		String platformVersion = "6.3";
		// String apkName = "com.android.settings.apk";
		String apkName = "SunmiPayTestDemo.apk";
		String appActivity = "com.blanks.test.demo.MainActivity";
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
//		 　隐藏键盘使用unicodeKeyboard的编码方式来发送字符串，再就是将键盘给隐藏起来，二个参数都为True 即可。 
		capabilities.setCapability("resetKeyboard", true);
		capabilities.setCapability("unicodeKeyboard", true);

	}

	@AfterClass
	public void afterClass() {
	}

}
