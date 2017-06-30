package auto.interfaces;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tools.ExcelProvider4Test;
import tools.httpGetAndPost;

public class haocaishangchang4Android {
	@Test(dataProvider = "haocaishangcheng", enabled = true, timeOut = 5000, groups = { "testapi",
			"" }, description = "耗材商城接口测试")
	public void haocaishangcheng(Map<String, String> testdata) {
		httpGetAndPost post = new httpGetAndPost();
	String s = post.sendPost("http://test.api.sunmi.com/api/sunmi/app/accounts/1.0/",
				"service=/updateReceivingInfo&uId=77&rId=998&pId=289&cId=9382&aId=1839&consigneeAddress=中国上海市杨浦区政立路272号&consigneeMobile=13671611577&consigneeName=唐小文&defaultAddress=1&msn=V101164500718&machineModel=V1");
		System.err.println("以post形式发送："+s);
		
		String s1 = post.sendGet("http://test.api.sunmi.com/api/sunmi/app/accounts/1.0/",
				"service=/updateReceivingInfo&uId=77&rId=998&pId=289&cId=9382&aId=1839&consigneeAddress=中国上海市杨浦区政立路272号&consigneeMobile=13671611577&consigneeName=唐小文&defaultAddress=1&msn=V101164500718&machineModel=V1");
		System.err.println("以get形式发送："+s1);
	}

	/*
	 * @BeforeMethod public void beforeMethod() { }
	 * 
	 * @AfterMethod public void afterMethod() { }
	 * 
	 * @BeforeClass public void beforeClass() { }
	 * 
	 * @AfterClass public void afterClass() { }
	 * 
	 * @BeforeTest public void beforeTest() { }
	 * 
	 * @AfterTest public void afterTest() { }
	 */
	@DataProvider(name = "haocaishangcheng", parallel = false)
	public Iterator<Object[]> data4testngDatadriven() throws IOException {
		return new ExcelProvider4Test(this, "haocaishangcheng");
	}
}
