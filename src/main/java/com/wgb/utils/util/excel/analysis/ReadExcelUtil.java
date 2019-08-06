package com.wgb.utils.util.excel.analysis;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/7/26 16:34
 **/
public class ReadExcelUtil {
	// 为什么没有工作表（Sheet）这个属性呢？--默认读取第一个Sheet,为了避免三重循环，三重循环大大增加了时间复杂度
	/**
	 * sheet
	 */
	private int excelSheet = 0;

	/**
	 * 行
	 */
	private int excelRow = 0;

	/**
	 * 列
	 */
	private int excelCell = 0;

	/**
	 * 错误信息
	 */
	private String errorInfo = "";

	public int getExcelRow() {
		return excelRow;
	}

	public int getExcelCell() {
		return excelCell;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * 一般判断文件类型、是否为空、是否存在，都在上传文件的时候判断了，不过避免你们麻烦，我在这里判断，你们只要传入文件完整路径就可以了
	 * @param filePath 文件完整路径
	 * @return
	 */
	public boolean isFile(String filePath) {
	boolean bl = true;

	/**
	 * 判断文件路径是否为空、是否是 .xls .xlsx 格式
     */
	if (filePath == null || !(ExcelUtil.isExcel2003(filePath) || ExcelUtil.isExcel2007(filePath))) {
		this.errorInfo = "文件不是excel格式";
		bl = false;
	} else {
		// 判断文件是否存在
		File file = new File(filePath);
		if (file == null || !file.exists()) {
			this.errorInfo = "文件不存在";
			bl = false;
		}
	}
	return bl;
}

	/**
	 * 判断用哪个Workbook
	 * @param filePath 文件完整路径
	 * @return
	 */
	public Workbook isWorkbook(String filePath){
		Workbook wb = null;
		InputStream is = null;
		// 调用isFile方法，判断文件是否有问题，为true进入下一步
		if (isFile(filePath)) {
			try {
				// excel文件输入流
				is = new FileInputStream(filePath);
				// 是否是2003版本
				if (ExcelUtil.isExcel2003(filePath)) {
					wb = new HSSFWorkbook(is);
				} else {
					wb = new XSSFWorkbook(is);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			// 打印错误信息
			System.out.println(this.errorInfo);
		}
		return wb;
	}

	/**
	 * 解析excel
	 * @param filePath 文件完整路径
	 * @return
	 */
	public List<List<List<String>>> readExcel(String filePath) {
		List<List<List<String>>> listLists = new ArrayList<>();

		Workbook wb = isWorkbook(filePath);
		if (wb == null) {
			return null;
		}
		int sheetCount = wb.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			List<List<String>> lists = new ArrayList<>();
			getDataBySheet(wb.getSheetAt(i), lists);
			listLists.add(lists);
		}
		return listLists;
	}

	public static void getDataBySheet(Sheet sheet, List<List<String>> lists) {
		// 判断工作表不为空
		if (sheet != null) {
			// 得到Excel的行数
			int excelRow = sheet.getPhysicalNumberOfRows();
			// 判断行数不能小于1并且第一行不能为空
			if (excelRow >= 1 && sheet.getRow(0) != null) {
				// 得到Excel的列数
				int excelCell = sheet.getRow(0).getPhysicalNumberOfCells();
				// 循环行
				for (int i = 0; i < excelRow; i++) {
					Row row = sheet.getRow(i);
					// 判断行是否为空--为空结束本次循环
					if (row == null) {
						continue;
					}
					List<String> list = new ArrayList<>();
					// 循环列
					for (int j = 0; j < excelCell; j++) {
						Cell cell = row.getCell(j);
						// 判断列是否为空
						getData(cell, list);
					}
					lists.add(list);
				}
			}
		}
	}

	public static void getData(Cell cell, List<String> list) {
		String cellValue = "";
		if (cell != null) {
			// 判断数据类型
			switch (cell.getCellType()) {
				// 数字
				case Cell.CELL_TYPE_NUMERIC:
					// 格式化返回值，不会出现 .0 和科学记数法
					DecimalFormat df = new DecimalFormat("0");
					// + "" 是为了把数据变成字符串返回
					cellValue = df.format(cell.getNumericCellValue()) + "";
					break;
				// 字符串
				case Cell.CELL_TYPE_STRING:
					cellValue = cell.getStringCellValue();
					break;
				// boolean
				case Cell.CELL_TYPE_BOOLEAN:
					cellValue = cell.getBooleanCellValue() + "";
					break;
				// 公式
				case Cell.CELL_TYPE_FORMULA:
					cellValue = cell.getCellFormula() + "";
					break;
				// 空值
				case Cell.CELL_TYPE_BLANK:
					cellValue = "";
					break;
				// 错误
				case Cell.CELL_TYPE_ERROR:
					cellValue = "非法字符";
					break;
				default:
					cellValue = "未知类型";
					break;
			}
		}
		list.add(cellValue);
	}

	public static void main(String[] args) {
		ReadExcelUtil excelUtil = new ReadExcelUtil();
		// C:/Users/云澈/Desktop/New Folder/信道.xls
		String filePath = "E:\\project\\重新生成子商户号20190726.xlsx";
		String filePath1= "E:\\project\\测试\\测试.xlsx";
		List<List<List<String>>> listLists = excelUtil.readExcel(filePath1);
		// 双重循环打印
		for (int i = 0; i < listLists.size(); i++) {
			// 获取第i个Sheet
			System.out.println("Excel表格--第"+(i+1)+"个Sheet");
			List<List<String>> lists = listLists.get(i);
			for (int j = 0; j < lists.size(); j++) {
				System.out.print("Excel表格--第"+(j+1)+"行");
				List<String> list = lists.get(j);
				for (int k = 0; k < list.size(); k++) {
					// \t    -- 在同一个缓冲区内横向跳8个空格，也称"\t"为制表符。
					System.out.print("\t"+list.get(k));
				}
				System.out.println(); // 换行
			}

		}
	}
}

/**
 * excel工具
 * @author INNERPEACE
 */
class ExcelUtil {


	/**
	 * 判断Excel文件的版本是否是2003    --    参数：文件完整路径
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	/**
	 * 判断Excel文件的版本是否是2007    --    参数：文件完整路径
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}
