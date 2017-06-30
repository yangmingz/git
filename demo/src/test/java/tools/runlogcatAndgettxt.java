package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class runlogcatAndgettxt {
	// 实时读取logcat并判断匹配
	public static String openAndReadlogcat(String matchmsg) throws IOException, InterruptedException {
		String msg = null;
		Process p = null;
		String matchline = null;
		BufferedReader br = null;
		int i=0;
		String command2 = "cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell logcat";
		try {
			p = Runtime.getRuntime().exec(command2);
			// Runtime.getRuntime().exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				i++;
				Thread.sleep(1);
				if (s.contains(matchmsg)) {
					matchline = s;
					System.out.println("matchmsg: " + s);
					// 找到匹配项直接退出，按HOME键
					Runtime.getRuntime().exec("cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell input keyevent 3").waitFor();
//					Runtime.getRuntime().exit(0);
					break;
				}
				if (i>15000) {
					matchline="";
					System.out.println("matchmsg not foundin 20000lines ");
					break;
				}
			}
			br.close();
//			HOME键返回不影响下一次用例执行
			Runtime.getRuntime().exec("cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell input keyevent 3").waitFor();
//			Runtime.getRuntime().exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchline;

	}

	// 非实时读取logcat，先保存
	public static String chkfile(File file, String matchmsg) {
		String msg = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				if (s.contains(matchmsg)) {
					System.out.println("matchmsg: " + s);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static void main(String[] args) {
		try {
			// ADB调用打电话，如果能调用起来的话会返回没有SIM卡/SIM卡错误
			String s = openAndReadlogcat("没有SIM卡/SIM卡错误");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
