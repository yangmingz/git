package auto.sysapi.test;

import org.testng.annotations.Test;

import tools.ExcelProvider4Test;
import tools.MultipartEntityUtil;
import tools.convertUnicode;
import tools.httpGetAndPost;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class demo {
	@Test(dataProvider = "demo", alwaysRun = true, description = "test sunmipush-谷歌组件", timeOut = 30000)
	public void demo(Map<String, String> data) throws IOException {
		boolean flg = false;
		String url = data.get("url").trim();
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("timeStamp", new StringBody(data.get("timeStamp").trim()));
		reqEntity.addPart("randomNum", new StringBody(data.get("randomNum").trim()));
		reqEntity.addPart("isEncrypted", new StringBody(data.get("isEncrypted").trim()));
		reqEntity.addPart("params", new StringBody(data.get("params").trim()));
		reqEntity.addPart("sign", new StringBody(data.get("sign").trim()));
		reqEntity.addPart("timezone", new StringBody(data.get("timezone").trim()));
		reqEntity.addPart("lang", new StringBody(data.get("lang").trim()));
		MultipartEntityUtil post = new MultipartEntityUtil();
		// 更新谷歌套件<
		System.err.println(">>>>>>用例编号：" + data.get("caseno").trim() + "<<<<<");
		System.err.println(">>>>>>用例目的和步骤：" + data.get("des").trim() + "<<<<<");
		String rs1 = post.postFile(url, reqEntity);
		if (rs1.contains("\"code\":1")) {
			flg = true;
			System.err.println(">>>>>>用例执行结果：执行成功<<<<<");
		} else {
			System.out.println("谷歌套件设置失败，请重新设置");
			System.err.println(">>>>>>用例执行结果：执行失败<<<<<");

		}
		Assert.assertEquals(flg, true);
		System.err.println("更新谷歌套件返回：" + rs1);
	}

	public String login() throws IOException {
		String res = null;
		MultipartEntityUtil post = new MultipartEntityUtil();
		StringBody timeStamp = new StringBody("1484556761");
		StringBody randomNum = new StringBody("589220");
		StringBody isEncrypted = new StringBody("0");
		StringBody params = new StringBody("{\"userName\":\"test_fxm\",\"passWord\":\"MJKRNv61w+Y0K5M9ex3rCQ==\"}");
		StringBody sign = new StringBody("440f5a2d38a29becf2ab4fad4e8ce966");
		StringBody timezone = new StringBody("GMT+08:00");
		StringBody lang = new StringBody("zh");
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("timeStamp", timeStamp);
		reqEntity.addPart("randomNum", randomNum);
		reqEntity.addPart("isEncrypted", isEncrypted);
		reqEntity.addPart("params", params);
		reqEntity.addPart("sign", sign);
		reqEntity.addPart("timezone", timezone);
		reqEntity.addPart("lang", lang);
		String url = "http://test.webapi.sunmi.com/webapi/partners/web/developers/1.2/?service=Developers.login";
		res = post.postFile(url, reqEntity);
		return res;
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@DataProvider(name = "demo", parallel = false)
	public Iterator<Object[]> data4testngDatadriven4() throws IOException {
		return new ExcelProvider4Test(this, "demo");
	}

	@BeforeClass
	public void beforeClass() {
		// 登陆账户
		String rs = null;
		try {
			rs = login();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("执行一次登陆登录返回：" + rs);
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

}
