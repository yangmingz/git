package appTest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.lib.ctrip.ScreenshotListener;

@Listeners({ ScreenshotListener.class })
public class Screenshot {
	static AndroidDriver driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	@BeforeClass
	  public void beforeClass() {
			String deviceName = "sunmi-p1-V20116BQ00066";
			String platformVersion = "6.3";
			// String apkName = "com.android.settings.apk";
			String apkName = "SunmiUserFeedBack_v2.2.17_release.apk";
			String appActivity = "com.sunmi.userfeedback.ui.activity.SplashActivity";
			String AppiumServerIP = "http://127.0.0.1:4723/wd/hub";
			// 文件路径,设置参数等
			File apk = new File(System.getProperty("user.dir") + File.separator + "apps" + File.separator + apkName);
			System.err.println("apk file path:"+apk);
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("platformVersion", platformVersion);
			capabilities.setCapability("app", apk);
			capabilities.setCapability("appActivity", appActivity);
			capabilities.setCapability("noSign", true);
			capabilities.setCapability("noReset", true);
	  }

	@Test(timeOut=60000,description="com.sunmi.userfeedback.ui.activity.SplashActivity")
	  public void sunmi_setting_tests() throws ParseException {
		  try {
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				Thread.sleep(2000);
				System.err.println("com.sunmi.userfeedback.ui.activity.SplashActivity启动等待时间...");
//				添加反馈
				driver.findElement(By.id("com.sunmi.userfeedback:id/id_floatingActionButton")).click();;
//				直接提问
				driver.findElement(By.id("com.sunmi.userfeedback:id/btn_skip")).click();
				driver.findElement(By.id("com.sunmi.userfeedback:id/editText1")).clear();
				driver.findElement(By.id("com.sunmi.userfeedback:id/editText1")).sendKeys("dggsdg2423525sgs");
				driver.findElement(By.id("com.sunmi.userfeedback:id/btn_question")).click();
				Assert.assertFalse(true);
				System.err.println("test end ...");
				driver.quit();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	public static AppiumDriver getDriver(){
		return driver;
		} 
	@AfterClass
	public void tearDown() {
	}
}