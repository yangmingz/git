package tools;

public class httpFactory {
	// String urlNameString = url + "?" + param;
	public static String gethttpFactory(String type, String url, String param) {
		String result = null;
		if (type != null && type.equals("post")) {
			result=httpGetAndPost.sendPost(url, param);
		}
		if (type != null && type.equals("get")) {
			result=httpGetAndPost.sendGet(url, param);
		}
		return result;

	}
	public static void main(String[] args) {
		String url="http://121.42.58.44/api/datacollect/app/datacollect/1.0/";
		String param="service=/flow3gall&dId=419&timebegin=1462032000&timeend=1462809600";
		String res="";
		res=gethttpFactory("get", url, param);
		System.err.println("返回值：	"+res);
	}
}
