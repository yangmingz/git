package tools;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class execShellCmd {
	/** 
	 * 执行shell命令 
	 *  
	 * @param cmd 
	 */  
	public static void execShellCmd(String cmd) {  
	  
	    try {  
	        // 申请获取root权限，这一步很重要，不然会没有作用  ,在安卓里边执行，windows不是这样挑用
	        Process process = Runtime.getRuntime().exec("su");  
	        // 获取输出流  
	        OutputStream outputStream = process.getOutputStream();  
	        DataOutputStream dataOutputStream = new DataOutputStream(  
	                outputStream);  
	        dataOutputStream.writeBytes(cmd);  
	        dataOutputStream.flush();  
	        dataOutputStream.close();  
	        outputStream.close();  
	    } catch (Throwable t) {  
	        t.printStackTrace();  
	    }  
	} 
	public static void main(String[] args) {
	    execShellCmd("getevent -p");  
	    execShellCmd("sendevent /dev/input/event0 1 158 1");  
	    execShellCmd("sendevent /dev/input/event0 1 158 0");  
	    execShellCmd("input keyevent 3");//home  
	    execShellCmd("input text  'helloworld!' ");  
	    execShellCmd("input tap 168 252");  
	    execShellCmd("input swipe 100 250 200 280");  
	}
}
