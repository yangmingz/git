package tools;

import java.io.IOException;

public class moneytest4windows {
public static void moneytest4windows(String adbpath,String pkgname,String outputpath,String times) {
/*	adb shell monkey -p com.woyou.hardwarekeeper --throttle 300 --pct-touch 60 --pct-motion 10 --pct-nav 20 --pct-majornav 5 --pct-syskeys 5 --ignore-crashes -v-v-v 5000>>E:\monkey.txt
	adb shell ps|findstr monkey
	adb shell kill "pid"*/
	String s="adb shell monkey -p "+pkgname+" --throttle 300 --pct-touch 60 --pct-motion 10 --pct-nav 20 --pct-majornav 5 --pct-syskeys 5 --ignore-crashes -v-v-v "+times+">>"+outputpath;
	System.out.println(s);
	try {
		Runtime.getRuntime().exec(adbpath+s);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void main(String[] args) {
//	try {
//		Runtime.getRuntime().exec("cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell monkey -p com.woyou.hardwarekeeper --throttle 300 --pct-touch 60 --pct-motion 10 --pct-nav 20 --pct-majornav 5 --pct-syskeys 5 --ignore-crashes -v-v-v 50>>E:\\monkey.txt");
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	System.out.println(getpid.getpid("monkey"));
}
}
