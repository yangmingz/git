package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

public class WriteExcelAndReadExcel {
	// private static String path = "E:/dingdanzhifu_newless.xls";
	public void WriteExcelFail(String path, String Wcontents, String sheetname, int rownum, int cellnum)
			throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
		// HSSFWorkbook wb = new HSSFWorkbook();
		// 得到Excel工作簿对象
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		// 得到Excel工作表对象
		// HSSFSheet sheet = wb.getSheetAt(sheetnum);
		HSSFSheet sheet = wb.getSheet(sheetname);
		// sheetnums=sheet.getLastRowNum();
		// 新建SHEET
		// HSSFSheet sheet2 = wb.createSheet("append sheet2");
		// 得到Excel工作表的行
		HSSFRow row = sheet.getRow((short) rownum);
		// 新建Excel工作表的行
		// HSSFRow row = sheet.createRow((short)1);
		// 得到Excel工作表指定行的单元格
		HSSFCell cell = row.createCell(cellnum);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillPattern(HSSFCellStyle.FINE_DOTS);
		style.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
		style.setFillBackgroundColor(new HSSFColor.RED().getIndex());
		// 向单元格中放入值
		cell.setCellValue(Wcontents);
		cell.setCellStyle(style);
		int n = wb.getNumberOfSheets();
		// System.out.println("NumberOfSheets: " + String.valueOf(n));
		FileOutputStream fileOut = new FileOutputStream(path);
		wb.write(fileOut);
		// return sheetnums;
	}

	public void WriteExcelPass(String path, String Wcontents, String sheetname, int rownum, int cellnum)
			throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
		// HSSFWorkbook wb = new HSSFWorkbook();
		// 得到Excel工作簿对象
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		// 得到Excel工作表对象
		// HSSFSheet sheet = wb.getSheetAt(sheetnum);
		HSSFSheet sheet = wb.getSheet(sheetname);
		// sheetnums=sheet.getLastRowNum();
		// 新建SHEET
		// HSSFSheet sheet2 = wb.createSheet("append sheet2");
		// 得到Excel工作表的行
		HSSFRow row = sheet.getRow((short) rownum);
		// 新建Excel工作表的行
		// HSSFRow row = sheet.createRow((short)1);
		// 得到Excel工作表指定行的单元格
		HSSFCell cell = row.createCell(cellnum);
		// HSSFCellStyle style = wb.createCellStyle();
		// //成功的填充绿色
		// style.setFillForegroundColor((short) 15);
		// cell.setCellStyle(style);
		// cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// 向单元格中放入值
		cell.setCellValue(Wcontents);
		int n = wb.getNumberOfSheets();
		// System.out.println("NumberOfSheets: " + String.valueOf(n));
		FileOutputStream fileOut = new FileOutputStream(path);
		wb.write(fileOut);
		// return sheetnums;
	}

	public String ReadExcelcells(String path, String sheetname, int rownum, int cellnum)
			throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
		// 得到Excel工作簿对象
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		// 得到Excel工作表对象
		HSSFSheet sheet = wb.getSheet(sheetname);
		/*
		 * sheetnums=sheet.getLastRowNum(); HSSFSheet sheet2 = wb.createSheet(
		 * "append sheet2"); 得到Excel工作表的行 HSSFRow row =
		 * sheet.createRow((short)1); 得到Excel工作表指定行的单元格
		 */
		HSSFRow row = sheet.getRow((short) rownum);
		HSSFCell cell = row.getCell(cellnum);
		return cell.getStringCellValue();
	}

	public void WriteExcel(String path, int number, String sheetname, int rownum, int cellnum)
			throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));

		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(sheetname);

		HSSFRow row = sheet.getRow((short) rownum);

		HSSFCell cell = row.createCell(cellnum);
		// //成功的填充绿色
		// style.setFillForegroundColor((short) 15);
		// cell.setCellStyle(style);
		// cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// 向单元格中放入值
		cell.setCellValue(number);
		int n = wb.getNumberOfSheets();
		FileOutputStream fileOut = new FileOutputStream(path);
		wb.write(fileOut);
	}
	public void WriteExcels(String path, String s, String sheetname, int rownum, int cellnum)
			throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));

		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(sheetname);

		HSSFRow row = sheet.getRow((short) rownum);

		HSSFCell cell = row.createCell(cellnum);
		// //成功的填充绿色
		// style.setFillForegroundColor((short) 15);
		// cell.setCellStyle(style);
		// cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// 向单元格中放入值
		cell.setCellValue(s);
		int n = wb.getNumberOfSheets();
		FileOutputStream fileOut = new FileOutputStream(path);
		wb.write(fileOut);
	}
}