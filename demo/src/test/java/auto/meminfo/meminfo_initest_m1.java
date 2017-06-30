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
import tools.ExcelProvider4Test;
//测试初始化POS内存性能
public class meminfo_initest_m1 {
	int r=0;
	WriteExcelAndReadExcel W = new WriteExcelAndReadExcel();
	private String path = "";
//	invocationCount指定方法执行2次
	@Test(dataProvider = "meminfotest", description = "meminfotest for P1", enabled = true,invocationCount=1)
	public void meminfotest(Map<String, String> testdata) {
		ArrayList<String> al = new ArrayList();
		// 先清除上一次循环数据
		al.clear();
		String commandsa = null;
		String commandsb = null;
		int total=0;
		BufferedReader br = null;
		System.err.println("test begin........"); 
		long t = System.currentTimeMillis();
		try {
			commandsa = testdata.get("commands").trim();
			commandsb = testdata.get("txt").trim();

			/*
			 * Process p = Runtime.getRuntime().exec(
			 * "cmd /c E:&&cd E:\\tools\\adb\\adb&&adb shell dumpsys meminfo woyou.aidlservice.jiuiv5>>E:\\testmeminfo.txt"
			 * );
			 */
//			记录结果进TXT
			Runtime.getRuntime().exec("cmd /c E:&&cd E:\\tools\\adb\\adb&&" + commandsa + commandsb);
//			回写ＥＸＣＥＬ
			Process p = Runtime.getRuntime().exec("cmd /c E:&&cd E:\\tools\\adb\\adb&&" + commandsa);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				if (line.contains("TOTAL")) {
					// 添加在列表后放在afermethod处理并反存数据源
					al.add(line.substring(16,line.length()-52).trim());
				}
			}
			if (al.isEmpty()) {
				System.err.println("total值为0,原因可能是取值失败或者本进程不存在");
				total=0;
			} else {
				total= Integer.parseInt(al.get(0));
				System.err.println("TOTAL:"+total);
			}
			try {
				r++;
//				再转一次tostring,本来应该直接int的
				W.WriteExcel(path,total, "meminfotest", r, 2);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		System.err.println("test end........");
		System.out.println("执行耗时 : " + (System.currentTimeMillis() - t) / 1000f + " 秒 ");
	}

	@BeforeClass(alwaysRun = true, description = "初始化保存结果文件", timeOut = 5000)
	public void beforeclass() {
		File file = new File("E:\\testini_m1.txt");
		file.delete();
	}
	@BeforeClass
	public void beforeClass() {
		//还要加上一个加载配置文件
		try {
			path = new File("./").getCanonicalPath()
					+ "/src/main/resources/data/excel/"
					+ this.getClass().getName().replaceAll("\\.", "/") + ".xls";
			 System.err.println("当前数据驱动路径为： "+path);
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
