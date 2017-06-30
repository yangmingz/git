package tools;

import java.util.ArrayList;
import java.util.List;

public class chkResult {
	public static boolean chkResult(String expactString, String actualString) {
		boolean flg = false;
		String[] ls = expactString.split("&");
		for (String s : ls) {
			if (actualString.contains(s)) {
				flg = true;
			} else {
				flg = false;
			}
		}
		return flg;
	}

	public static void main(String[] args) {
		String expactString = "123&223&336";
		String actualString1 = "123223336";
		String actualString2 = "123223337";
		System.out.println(chkResult.chkResult(expactString, actualString1));
		System.out.println(chkResult.chkResult(expactString, actualString2));

	}
}
