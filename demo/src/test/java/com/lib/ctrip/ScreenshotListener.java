package com.lib.ctrip;
import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import appTest.Screenshot;

public class ScreenshotListener extends TestListenerAdapter{
@Override
public void onTestFailure(ITestResult tr){
AppiumDriver driver = Screenshot.getDriver();
File location = new File("screenshots");
String screenShotName = location.getAbsolutePath()+File.separator+tr.getMethod().getMethodName()+".png";
System.err.println("失败用例图片保存路径："+screenShotName);
File screenShot = driver.getScreenshotAs(OutputType.FILE);
try{
FileUtils.copyFile(screenShot, new File(screenShotName));
}
catch(IOException e){
e.printStackTrace();
}

}
public static void main(String[] args) {
	System.out.println(File.separator);
	Map<String, String> m=new HashMap<String, String>();
	m=System.getenv();
	for (Map.Entry<String, String> mset : m.entrySet()) {
		System.out.println("key:"+mset.getKey()+"||"+"value:"+mset.getValue());
	}

}
}