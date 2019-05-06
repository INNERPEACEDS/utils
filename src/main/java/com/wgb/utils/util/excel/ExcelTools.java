package com.wgb.utils.util.excel;


import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;




import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.PageOrientation;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
/**
 * @author : innerpeace
 * @date : 2018/11/28 14:42
 */
public class ExcelTools {

	public static void MakeExcelFile(HttpServletResponse response,
									 String fileName, String titleName, String query, String title[],
									 String field[], List list) throws Exception {
		OutputStream os = response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="
				.concat(URLEncoder.encode(fileName, "utf-8")));
		BufferedOutputStream bos = new BufferedOutputStream(os);

		// 创建Excel工作薄
		WritableWorkbook wwb = Workbook.createWorkbook(bos);
		// 添加第一个工作表并设置第一个Sheet的名字
		WritableSheet sheet = wwb.createSheet(titleName, 0);
		Label label;

		// 数字货币 格式 Excel 数据格式 保留2位小数
		NumberFormat fivedps = new NumberFormat("0.00");
		WritableCellFormat fivedpsFormat = new WritableCellFormat(fivedps);
		// 数字货币 格式-- 设置边框线
		fivedpsFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		sheet.getSettings().setOrientation(PageOrientation.LANDSCAPE); // 横向打印
		// 文本格式
		jxl.write.WritableFont wf = new jxl.write.WritableFont(
				WritableFont.ARIAL);
		jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wf);
		// wcfF.setWrap(true);// 自动换行
		wcfF.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
		// wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//
		// 把垂直对齐方式指定为居中
		// 设置边框线
		wcfF.setBorder(Border.ALL, BorderLineStyle.THIN);

		// 标题
		sheet.addCell(new Label(0, 0, titleName));
		sheet.addCell(new Label(1, 0, query));
		// 准备设置excel工作表的标题
		for (int i = 0; i < title.length; i++) {
			// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是z
			// 在Label对象的子对象中指明单元格的位置和内容
			label = new Label(i, 1, title[i], wcfF);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
		}
		// 循环获取数据
		// 序号
		int number = 1;
		// 行号
		int line = 2;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int row = 0;
				if ("序号".equals(title[0])) {
					sheet.addCell(new Label(0, line, String.valueOf(number),
							wcfF));
					row = 1;
				}
				for (int k = 0; k < field.length; k++) {

					Method method = list.get(i).getClass()
							.getMethod(field[k], null);
					Class cl = method.getReturnType();
					Object ob = method.invoke(list.get(i), null);
					if (cl.equals(Long.class)) {
						sheet.addCell(new jxl.write.Number(row, line, Double
								.parseDouble(Tools.formatAmt((Long) ob)),
								fivedpsFormat));
					} else {
						sheet.addCell(new Label(row, line, Tools
								.nullObjectFormat(ob, ""), wcfF));
					}
					row++;
				}
				// 写输出文件
				number++;
				line++;
			}
		}
		// 写入数据
		wwb.write();
		// 关闭文件
		wwb.close();
		bos.flush();
		bos.close();
	}



	public static void createExcelForMap(HttpServletResponse response, String fileName, String titleName, String query, String[] title,
										 String[] field, List<Map<String,Object>> list) throws Exception {
		OutputStream os = response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="
				.concat(URLEncoder.encode(fileName, "utf-8")));
		BufferedOutputStream bos = new BufferedOutputStream(os);
		// 创建Excel工作薄
		WritableWorkbook wwb = Workbook.createWorkbook(bos);
		// 添加第一个工作表并设置第一个Sheet的名字
		WritableSheet sheet = wwb.createSheet(titleName, 0);
		Label label;
		// 数字货币 格式 Excel 数据格式 保留2位小数
		NumberFormat fivedps = new NumberFormat("0.00");
		WritableCellFormat fivedpsFormat = new WritableCellFormat(fivedps);
		// 数字货币 格式-- 设置边框线
		fivedpsFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		sheet.getSettings().setOrientation(PageOrientation.LANDSCAPE); // 横向打印
		// 文本格式
		jxl.write.WritableFont wf = new jxl.write.WritableFont(
				WritableFont.ARIAL);
		jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wf);
		// wcfF.setWrap(true);// 自动换行
		wcfF.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居中
		// wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//
		// 把垂直对齐方式指定为居中
		// 设置边框线
		wcfF.setBorder(Border.ALL, BorderLineStyle.THIN);
		// 标题
		sheet.addCell(new Label(0, 0, titleName));
		sheet.addCell(new Label(1, 0, query));
		// 准备设置excel工作表的标题
		for (int i = 0; i < title.length; i++) {
			// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是z
			// 在Label对象的子对象中指明单元格的位置和内容
			label = new Label(i, 1, title[i], wcfF);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
		}
		int number = 1;// 序号
		int line = 2;// 行号
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int row = 0;//
				if ("序号".equals(title[0])) {
					sheet.addCell(new Label(0, line, String.valueOf(number),
							wcfF));
					row = 1;
				}
				for (int k = 0; k < field.length; k++) {
					String str = field[k];
					Object ob = list.get(i).get(str);
					sheet.addCell(new Label(row, line,
							Tools.nullObjectFormat(ob, ""), wcfF));
					row++;
				}
				// 写输出文件
				number++;
				line++;
			}
		}
		// 写入数据
		wwb.write();
		// 关闭文件
		wwb.close();
		bos.flush();
		bos.close();
	}

	/**
	 * 删除字符串内的回车 空格和两端空白
	 */
	public static String tse(String str){
		return str == null? "":str.replace(" ", "").replace("/r", "").replace("/n", "").trim();
	}

}
