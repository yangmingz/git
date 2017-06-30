package appuimtest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sun.org.glassfish.external.statistics.Statistic;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.android.AndroidDriver;

public class appuimtest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// 连接多台机器就可以看到
		String deviceName = "sunmi-p1-V20116BQ00066";

		String platformVersion = "6.0";

		// String apkName = "com.android.settings.apk";
		String apkName = "美团.apk";
		String appActivity = "com.sankuai.meituan.activity.Welcome";

		String AppiumServerIP = "http://127.0.0.1:4723/wd/hub";

		AndroidDriver driver;
		// 文件路径,设置参数等
		File apk = new File(System.getProperty("user.dir") + File.separator + "apps" + File.separator + apkName);

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", deviceName);

		capabilities.setCapability("platformVersion", platformVersion);

		capabilities.setCapability("app", apk);

		capabilities.setCapability("appActivity", appActivity);

		capabilities.setCapability("noSign", true);

		capabilities.setCapability("noReset", true);
		// 登录等业务
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			Thread.sleep(3000);

			System.out.println("App启动等待时间");
			driver.findElement(By.id("com.sankuai.meituan:id/nearby_tv")).click();
			for (int i = 0; i < 3; i++) {
				driver.findElement(By.xpath("//android.widget.TextView[@text='首页']")).click();
				driver.findElement(By.xpath("//android.widget.TextView[@text='附近']")).click();
				driver.findElement(By.xpath("//android.widget.TextView[@text='我的']")).click();
			}
			driver.quit();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static By ByAccessibilityId(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}

// demo
/*
 * //find login button WebElement loginButton =
 * driver.findElement(By.id("com.zhihu.android:id/login")); loginButton.click();
 * 
 * //wait for 20s driver.manage().timeouts().implicitlyWait(20,
 * TimeUnit.SECONDS);
 * 
 * //find login userName and password editText List<WebElement> textFieldsList =
 * driver.findElementsByClassName("android.widget.EditText");
 * textFieldsList.get(0).sendKeys("seleniumcookies@126.com");
 * textFieldsList.get(1).sendKeys("cookies123");
 * driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
 * 
 * //find ok button byName driver.findElementByName("OK").click();
 * driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
 * 
 * //find keyword 首页 and verify it is display Assert.assertTrue(
 * driver.findElement(By.name("首页")).isDisplayed());
 */
