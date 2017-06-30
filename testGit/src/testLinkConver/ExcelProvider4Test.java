package testLinkConver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import jxl.*;
import jxl.read.biff.BiffException;

public class ExcelProvider4Test implements Iterator<Object[]> {

	private Workbook book = null;
	private Sheet sheet = null;
	private int rowNum = 0;	//总行数
	private int curRowNo = 0; // 当前行的行号
	private int columnNum = 0;	// 总列数
	private String[] columnnName;	// 列名

	public ExcelProvider4Test(Object aa, String methodname)
		 {
		
		
		// 读取文件 C:\Users\Administrator\Desktop\质料\workspace\Mypro\src\driver\My\tools
		try {
			this.book = Workbook.getWorkbook(new File(new File("./").getCanonicalPath()  
					+ "/src/main/resources/data/excel/" + aa.getClass().getName().replaceAll("\\.", "/")
					+ ".xls"));
		System.out.println("读取文件路径为："+new File("./").getCanonicalPath()  
					+ "/src/main/resources/data/excel/" + aa.getClass().getName().replaceAll("\\.", "/")
					+ ".xls");	
//			System.out.println(new File("../").getCanonicalPath());
			this.sheet = book.getSheet(methodname);
			// 得到行数
			this.rowNum = sheet.getRows();
			// 得到第一行数据，将第一行的数据作为map的key
			Cell[] c = sheet.getRow(0);
			this.columnNum = c.length;
			columnnName = new String[c.length];
			for (int i = 0; i < c.length; i++) {
				columnnName[i] = c[i].getContents().toString();
			}		
			// 当前行的行号++
			this.curRowNo++;
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 是否有下一行
	 */
	public boolean hasNext() {
		if (this.rowNum == 0 || this.curRowNo >= this.rowNum) {
			book.close();
			return false;
		} else
			return true;
	}

	/**
	 * 得到下一行
	 */
	public Object[] next() {
		Cell[] c = sheet.getRow(this.curRowNo);
		Map<String, String> s = new HashMap<String, String>();
		for (int i = 0; i < this.columnNum; i++) {
			s.put(this.columnnName[i], c[i].getContents().toString());
		}
		this.curRowNo++;
//		System.out.println(s);
		return new Object[]{s};
	}

	/**
	 * 不使用remove，未实现
	 */
	public void remove() {
		throw new UnsupportedOperationException("remove unsupported.");
	}

}