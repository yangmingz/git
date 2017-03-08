package testGit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.omg.Messaging.SyncScopeHelper;

import tools.byteUnilts;

public class gittest2 {
public static void main(String[] args) {
	byte[] info=new byte[1024];
    String s=null;
        int len = 0;
		try {
			len = System.in.read(info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 通过使用指定的字符集解码指定的 byte 子数组，构造一个新的 String。
        try {
			String strInfo = new String(info, 0, len, "GBK");
			System.out.println(strInfo);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//从控制台读出信息
}
}