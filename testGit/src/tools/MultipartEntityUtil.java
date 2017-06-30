package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * httpclient上传文件
 * @author linwei
 *
 */
public class MultipartEntityUtil {
	BufferedReader instr=null;
	@SuppressWarnings("deprecation")
	public static String postFile(String url,MultipartEntity reqEntity) throws ClientProtocolException, IOException {
		String result = "";
//		FileBody bin = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
//		if(file != null) {
//			bin = new FileBody(file);
//		}
/*
		StringBody timeStamp = new StringBody("1484556761");
		StringBody randomNum = new StringBody("589220");
		StringBody isEncrypted = new StringBody("0");
		StringBody params = new StringBody("{\"userName\":\"test_fxm\",\"passWord\":\"MJKRNv61w+Y0K5M9ex3rCQ==\"}");
		StringBody sign = new StringBody("440f5a2d38a29becf2ab4fad4e8ce966");
		StringBody timezone = new StringBody("GMT+08:00");
		StringBody lang = new StringBody("zh");*/

//请记住，这边传递汉字会出现乱码，解决方法如下,设置好编码格式就好
                new StringBody("汉字",Charset.forName("UTF-8"));  
/*		
	    MultipartEntity reqEntity = new MultipartEntity();
	    reqEntity.addPart("timeStamp", timeStamp);
	    reqEntity.addPart("randomNum", randomNum);
	    reqEntity.addPart("isEncrypted", isEncrypted);
	    reqEntity.addPart("params", params);	    
	    reqEntity.addPart("sign", sign);
	    reqEntity.addPart("timezone", timezone);	    
	    reqEntity.addPart("lang", lang);    
	    */
//	    
//	    reqEntity.addPart("data", bin);
	    
	    
	    httppost.setEntity(reqEntity);
	    System.out.println("执行: " + httppost.getRequestLine());
	    
	    HttpResponse response = httpclient.execute(httppost);
	    System.out.println("statusCode is " + response.getStatusLine().getStatusCode());
	    HttpEntity resEntity = response.getEntity();
	    System.out.println("----------------------------------------");
	    System.out.println(response.getStatusLine());
	    if (resEntity != null) {
	      System.out.println("返回长度: " + resEntity.getContentLength());
	      System.out.println("返回类型: " + resEntity.getContentType());
	      InputStream in = resEntity.getContent();
	       BufferedReader instr = new BufferedReader(
	                      new InputStreamReader(in));
//	      System.out.println("in is " + in);
	      String line;
          while ((line = instr.readLine()) != null) {
              result += line; 
	    if (resEntity != null) {
	      resEntity.consumeContent();
	    }
          }
	}
		return result;
	}
	public static void main(String[] args) throws ClientProtocolException, IOException {
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
//		File file = new File("d:/rss.xml");
		String url = "http://test.webapi.sunmi.com/webapi/partners/web/developers/1.2/?service=Developers.login";
		String res=postFile(url,reqEntity);
		System.out.println(res);
	}
	
	public static MultipartEntity getdata(Map<String, String> data,MultipartEntity reqEntity) throws UnsupportedEncodingException {
		StringBody timeStamp = new StringBody(data.get("timeStamp").trim());
		StringBody randomNum = new StringBody(data.get("randomNum").trim());
		StringBody isEncrypted = new StringBody(data.get("isEncrypted").trim());
		StringBody params = new StringBody(data.get("paras").trim());
		StringBody sign = new StringBody(data.get("sign").trim());
		StringBody timezone = new StringBody(data.get("timezone").trim());
		StringBody lang = new StringBody(data.get("lang").trim());
	    reqEntity.addPart("timeStamp", timeStamp);
	    reqEntity.addPart("randomNum", randomNum);
	    reqEntity.addPart("isEncrypted", isEncrypted);
	    reqEntity.addPart("params", params);	    
	    reqEntity.addPart("sign", sign);
	    reqEntity.addPart("timezone", timezone);	    
	    reqEntity.addPart("lang", lang);
		return reqEntity; 
	}
	
}
  
