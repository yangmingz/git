package tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//参数1：需要匹配的元字段，参数2：匹配的正则表达式
public class patternr {
	public static void main(String[] args) {
		String input = "<div style='color:red;'>尊敬的代理商：${t0b4ba9fa7bc4845a97eaaeb01d6dd2fbebeaf7a1577408699ee49221e66f35dmb7b1b7f798047dab051859542d0da87e97ca5b7563f421bb302aef073217b90}</div>"
				+ "<div style='color:red;'>尊敬的代理商：${t1b4ba9fa7bc4845a97eaaeb01d6dd2fbebeaf7a1577408699ee49221e66f35dmb7b1b7f798047dab051859542d0da87e97ca5b7563f421bb302aef073217b90}</div>";
		String regex = "\\$\\{([^\\}]*)\\}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		String[] params = {};
		while (matcher.find()) {
			System.out.println(matcher.group(0));

			String[] temp = new String[params.length + 1];
			System.arraycopy(params, 0, temp, 0, params.length);
			temp[temp.length - 1] = matcher.group(0);
			params = temp;
		}
		// System.out.println(Arrays.toString(params));
	}
}