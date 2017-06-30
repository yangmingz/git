package auto.sysapi.test;

import org.testng.annotations.Test;

import tools.ExcelProvider4Test;
import tools.MultipartEntityUtil;
import tools.WriteExcelAndReadExcel;
import tools.closeIconIncenter;
import tools.convertUnicode;
import tools.httpGetAndPost;
import tools.runlogcatAndgettxt;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class demo2 {
	String path=null;
//	回写行数统计
	int r=0;
	boolean caseflg=false;
	@Test(dataProvider = "demo2", alwaysRun = true, description ="test sunmipush-电话短信权限", timeOut =90000)
	public void demo(Map<String, String> data) throws IOException, InterruptedException {
		Thread.sleep(2000);
//		后台操作，安卓机器效果校验，用例期望返回值校验标志位
		r++;
		boolean webflg = false;
		boolean androidflg = false;
		boolean dataflg=false;
		String url = data.get("url").trim();
		String datas=data.get("expresult").trim();
		if (datas.contains("y")) {
			dataflg=true;
		}else {
			dataflg=false;
		}
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
//		等2秒
		Thread.sleep(2000);
		if (rs1.contains("\"code\":1")) {
			webflg = true;
			System.err.println(">>>>>>用例执行结果：执行成功<<<<<");
		} else {
			System.out.println("设置失败，请重新设置");
			System.err.println(">>>>>>用例执行结果：执行失败<<<<<");
		}
		try {
//			检查安卓机器日志是否有匹配的信息
		androidflg=chkphone(data.get("matchmsg").trim());
		if (!androidflg) {
//			执行完关闭一次系统弹出框和HOME返回
			closeIconIncenter.closeIcon();
		}
//		等待10秒看效果
		Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("后台返回：" + rs1);
//		webflg并！异或操作
		caseflg=webflg&&!(androidflg^dataflg);
		Assert.assertTrue(caseflg, "webflg is:"+webflg+"||"+"androidflg is:"+androidflg+"||"+"dataflg is:"+dataflg);
	}
//模拟WEB发送指令后查看安卓系统的logcat
	public boolean chkphone(String matchmsg) throws InterruptedException, IOException {
		boolean flg = false;
		String fs=null;
		// 执行打电话
		String command1 = "cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell am start -a android.intent.action.CALL -d tel:10086";
		try {
		/*	先启动logcat后调用打电话，logcat查找匹配字段，15000次后没找倒返回报错
			waitFor():java程序(A)，需要调用另一个shell script(B)，在B中又去执行一个C++程序.out文件（C），
			如果C运行正常，会通过B向A传回一个返回码。现在的问题是C程序中如果存在死循环，B执行不完，A也拿不到返回码，程序没反应*/
			Runtime.getRuntime().exec(command1).waitFor();
			Thread.sleep(500);
//       但是先启动logcat会一直等待阻塞电话调用，造成超时，所以先启动电话调用到出现提示信息会有2秒，期间启动logcat
			fs=runlogcatAndgettxt.openAndReadlogcat(matchmsg);
//			Runtime.getRuntime().exec(command1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fs!=null&&!"".equals(fs)) {
			flg=true;
		}
//		flg=(fs!=null&&"".equals(fs))?true:false;
		return flg;
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

	@DataProvider(name = "demo2", parallel = false)
	public Iterator<Object[]> data4testngDatadriven4() throws IOException {
		return new ExcelProvider4Test(this, "demo2");
	}
	@AfterMethod
//	每执行一个用例，清空logcat保存日志，按电源键启动，HOME键返回
	public void AfterMethod() throws FileNotFoundException, IOException{
		WriteExcelAndReadExcel W = new WriteExcelAndReadExcel();
//		写在7列R行
		W.WriteExcels(path,String.valueOf(caseflg), "demo2", r, 6);

	}
	@BeforeClass
	public void beforeClass() {
		// 登陆后台账户
		String rs = null;
		try {
			rs = login();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("执行一次登陆登录返回：" + rs);
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
	
}
