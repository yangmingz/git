package tools;

import java.io.IOException;

public class closeIconIncenter {
public static void closeIcon() {
	try {
//		点击屏幕中间确定按钮
		Runtime.getRuntime().exec("cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell input tap 60 502");
//		HOME键返回不影响下一次用例执行
		Runtime.getRuntime().exec("cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell input keyevent 3");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
