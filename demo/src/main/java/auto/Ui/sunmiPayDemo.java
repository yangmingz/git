package auto.Ui;

import org.testng.annotations.Test;

import com.lib.ctrip.ScreenshotListener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import tools.ExcelProvider4Test;
import tools.WriteExcelAndReadExcel;
import tools.swipTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
//@Listeners({ ScreenshotListener.class })
public class sunmiPayDemo {
	String path=null;
	int rowno=0;
	boolean flg=false;
	static AndroidDriver driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	swipTo st = new swipTo();
	@Test(dataProvider = "sunmiPayDemo", timeOut = 90000, description = "com.sunmi.userfeedback.ui.activity.SplashActivity")
	public void sunmi_setting_tests(Map<String, String> data) throws ParseException {
		String res = null;
		driver.findElement(By.xpath("//android.widget.Button[@text='初始化']")).click();
		// 进入保存秘钥功能
		WebElement e = driver.findElement(By.xpath("//android.widget.Button[@text='保存 密钥']"));
		System.out.println(e.getText());
		e.click();
		// 如果能够进入的话调用保存秘钥操作
		WebElement e1 = driver.findElement(By.xpath("//android.widget.ScrollView[@index='0']"));
		if (e1.isDisplayed()) {
			res = saveKeys(driver, data);
		}
		// 这部分接数据驱动
		boolean flg = res.contains(data.get("exp").trim());
		Assert.assertEquals(true, flg);
		rowno++;
		System.err.println("保存秘钥返回值" + res);
	}
//	public static AppiumDriver getDriver(){
//		return driver;
//		} 
	public String saveKeys(AndroidDriver driver, Map<String, String> data) {
		String result = null;
		/*
		 * 点击菜单栏去掉键盘java_client3.0版本以后使用pressKeyCode方法，之前的版本使用sendKeyEvent方法 1.
		 * 返回：driver.pressKeyCode(AndroidKeyCode.BACK) 2.
		 * HOME键：driver.pressKeyCode(AndroidKeyCode.Home)
		 */
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_key_index"))
				.sendKeys(data.get("et_input_key_index").trim());
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_key_type"))
				.sendKeys(data.get("et_input_key_type").trim());
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_deciphering_key_index"))
				.sendKeys(data.get("et_input_deciphering_key_index").trim());
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_encryptType"))
				.sendKeys(data.get("et_input_encryptType").trim());
		swipTo.swipeToUp(driver, 300);
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_mkey")).sendKeys(data.get("et_input_mkey").trim());
		driver.findElement(By.id("com.blanks.test.demo:id/et_input_length"))
				.sendKeys(data.get("et_input_length").trim());
		swipTo.swipeToUp(driver, 300);
		driver.findElement(By.name("保存密钥")).click();
		WebElement e = driver.findElement(By.id("com.blanks.test.demo:id/tv_save_key_result"));
		result = e.getText();
		return result;

	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@AfterMethod
	public void afterMethod() throws FileNotFoundException, IOException {
/*			WriteExcelAndReadExcel W = new WriteExcelAndReadExcel();
//			写在8列R行
			W.WriteExcels(path,String.valueOf(flg), "sunmiPayDemo", rowno,7);*/
		/*
		 * java_client3.0版本以后使用pressKeyCode方法，之前的版本使用sendKeyEvent方法
		 * 
		 * 1. 返回：driver.pressKeyCode(AndroidKeyCode.BACK)
		 * 
		 * 2. HOME键：driver.pressKeyCode(AndroidKeyCode.Home)
		 */
		driver.pressKeyCode(AndroidKeyCode.BACK);
		try {
//			等待返回页面
			Thread.sleep(2000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
		// 隐藏键盘使用unicodeKeyboard的编码方式来发送字符串，再就是将键盘给隐藏起来，二个参数都为True 即可。
		capabilities.setCapability("resetKeyboard", true);
		capabilities.setCapability("unicodeKeyboard", true);
		try {
			// 一个class,session只能一个
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		获取path
		try {
			path = new File("./").getCanonicalPath()
					+ "/src/main/resources/data/excel/"
					+ this.getClass().getName().replaceAll("\\.", "/") + ".xls";
			 System.err.println("当前数据驱动路径为： "+path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@DataProvider(name = "sunmiPayDemo", parallel = false)
	public Iterator<Object[]> data4testngDatadriven4() throws IOException {
		return new ExcelProvider4Test(this, "sunmiPayDemo");
	}
}
