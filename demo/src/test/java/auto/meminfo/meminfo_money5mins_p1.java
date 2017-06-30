package auto.meminfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tools.WriteExcelAndReadExcel;
import tools.moneytest4windows;
import tools.ExcelProvider4Test;

//测试初始化POS内存性能
public class meminfo_money5mins_p1 {
	int r = 0;
	static String pid = null;
	WriteExcelAndReadExcel W = new WriteExcelAndReadExcel();
	private String path = "";
	private int waittime=10;//等待monkey的执行时间，时间要估算
	@Test(dataProvider = "meminfotest", description = "meminfotest for P1 monkeytest", enabled = true,invocationCount=2)
	public void meminfotest(Map<String, String> testdata) {
		ArrayList<String> al = new ArrayList();
		// 先清除上一次循环数据
		al.clear();
		String commandsa = null;
		String commandsb = null;
		int total = 0;
		BufferedReader br = null;
		System.err.println("case"+testdata.get("no")+" test begin........");
		long t = System.currentTimeMillis();
		try {
			commandsa = testdata.get("commands").trim();
			commandsb = testdata.get("txt").trim();
			// 调用moneytest times次，下次调用前清除monkey进程可以放在after里边
			String pkgname = testdata.get("pkgname");
			String outputpath = testdata.get("monkeylog");
			String times = testdata.get("times");
			String cs = "cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell monkey -p " + pkgname
					+ " --throttle 300 --pct-touch 60 --pct-motion 10 --pct-nav 20 --pct-majornav 5 --pct-syskeys 5 --ignore-crashes -v-v-v "
					+ times + ">>" + outputpath;
			System.out.println("monkey comands:" + cs);
			try {
				Runtime.getRuntime().exec(cs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 等待时间
			Thread.sleep(waittime);
			// monkey进程还在，直接清除，否则开始记录，无权限kill		
//			String pid = getpid.getpid("monkey");
			Runtime.getRuntime().exec(cs);
			if (pid.equals("000000")) {
				// 记录结果进TXT
				String s = "cmd /c E:&&cd E:\\tools\\adb\\adb&&" + commandsa + commandsb + ".txt";
				System.out.println(s);
				Runtime.getRuntime().exec(s);
				// 回写ＥＸＣＥＬ
				Process p = Runtime.getRuntime().exec("cmd /c E:&&cd E:\\tools\\adb\\adb&&" + commandsa);
				br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					if (line.contains("TOTAL:")) {
						// 添加在列表后放在afermethod处理并反存数据源
						al.add(line.substring(24, line.length() - 28).trim());
					}
				}
				if (al.isEmpty()) {
					System.err.println("total值为0,原因可能是取值失败或者本进程不存在");
					total = 0;
				} else {
					total = Integer.parseInt(al.get(0));
					System.err.println("TOTAL:" + total);
				}
				try {
					r++;
					// 再转一次tostring,本来应该直接int的
					W.WriteExcel(path, total, "meminfotest", r, 2);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.err.println("case"+testdata.get("no")+" test end........");
		System.out.println("执行耗时 : " + (System.currentTimeMillis() - t) / 1000f + " 秒 ");
	}

	@BeforeClass
	public void BeforeClass() {
		// 还要加上一个加载配置文件
		try {
			path = new File("./").getCanonicalPath() + "/src/main/resources/data/excel/"
					+ this.getClass().getName().replaceAll("\\.", "/") + ".xls";
			System.err.println("当前数据驱动路径为： " + path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DataProvider(name = "meminfotest", parallel = false)
	public Iterator<Object[]> data4testngDatadriven4() throws IOException {
		return new ExcelProvider4Test(this, "meminfotest");
	}

}
