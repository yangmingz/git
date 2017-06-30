package tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AdbUtils {

	public static void main(String[] args) {
		// 通过环境变量获取adb程序的路径，
		String adbPath = System.getenv("ANDROID_HOME") + "/platform-tools/adb.exe";
		File file = new File(adbPath);
		if (file.exists()) {
			System.out.println("请输入adb命令:");
			Scanner scanner = new Scanner(System.in);
			String command = scanner.nextLine();
			// 开始执行命令
			execCommand(command);
		} else {
			System.out.println("尚未配置AndroidSDK");
		}
	}

	// 执行命令函数
	public static void execCommand(String cmd) {
		BufferedReader inputStream = null;
		BufferedReader errorStream = null;
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			// 获取正确的输入流
			inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
			// 起新线程来将结果输出
			readLine(inputStream);
			// 获取错误的输入流
			errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			// 起新线程读取错误流
			readLine(errorStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 读取返回的结果
	private static void readLine(final BufferedReader br) {
		new Thread(new Runnable() {
			public void run() {
				String line = null;
				StringBuffer str = new StringBuffer();
				try {
					// 循环读取输入流
					while ((line = br.readLine()) != null) {
						System.out.println(line);
						str.append(line + "\r\n");
					}
					br.close();
					// 写入日志文件
					if (str.length() > 0) {
						str.append("****" + formatDate("") + "*****\r\n");
						writeToFile(str.toString());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 文件最大长度
	private static long maxLength = 1024 * 200;

	// 写入文件内容
	private static void writeToFile(String content) throws IOException {
		File sourceFile = new File("D:\\adblog.txt");
		System.err.println("sourceFile 路径："+sourceFile.getAbsolutePath());
		// 文件不存在则创建
		if (!sourceFile.exists()) {
			sourceFile.createNewFile();
		}
		// 文件超过200k 则归档
		if (sourceFile.length() >= maxLength) {
			File targetFile = new File("log-" + formatDate("yyyyMMddhhmm") + ".txt");
			// 创建新文件
			targetFile.createNewFile();
			// 拷贝文件内容
			copyFile(sourceFile, targetFile);
			// 删除当前文件
			sourceFile.delete();
		}
		// 写入文件
		FileWriter fw = new FileWriter(sourceFile, true);
		BufferedWriter writer = new BufferedWriter(fw);
		writer.append(content);
		writer.close();
		fw.close();
	}

	// 拷贝文件
	private static void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 定义输入输出流
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			byte[] b = new byte[1024];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新缓冲区的输出流
			outBuff.flush();
		} finally {
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	// 格式化日期
	private static String formatDate(String pattern) {
		if (pattern.length() == 0) {
			pattern = "yyyy-MM-dd hh:mm";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}

}