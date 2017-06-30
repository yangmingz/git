package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class getPidAndKill {
	public String getpid(String pkgname) throws IOException {
		BufferedReader br = null;
		Process p = null;
		boolean flg = false;
		// String pidStringline=null;
		String pid = null;
		String command = "cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell ps";

		try {
			p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 读取执行ADB命令后返回信息并获取有用信息
		br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.contains(pkgname)) {
				System.out.println(line);
				// 获取包含传入包名的一行字符
				pid = line.substring(8, 15).toString().trim();
				// System.err.println("包含pid的行："+pid);
				break;
			}
		}
		return pid;
	}

	public void killPid(String pid) throws IOException {
		boolean flg = false;
		BufferedReader br = null;
		String line = null;
		Process p = null;
		String command = "cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell kill " + pid;
		try {
			p=Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}

	public static void main(String[] args) throws IOException {
		String pid = null;
		getPidAndKill get = new getPidAndKill();
		try {
			pid = get.getpid("com.android.phone");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("pid:" + pid);
		get.killPid(pid);
		
	}
}
