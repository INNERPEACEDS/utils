package com.wgb.utils.util.excel.alibaba;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * @Description:    excel操作工具类
 * @Author:         INNERPEACE
 * @CreateDate:     2018/9/29 18:05
 * @UpdateUser:     INNERPEACE
 * @UpdateDate:     2019/4/19 16:19
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public class ExcelUtils {
	/**
	 * 导出
	 * @param list
	 * @param response
	 * @param clazz
	 * @return
	 */
	public static  void export(List<? extends BaseRowModel> list, HttpServletResponse response, Class<? extends BaseRowModel> clazz) {
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
		try {
			String fileName = new String(
					(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(), "UTF-8");
			Sheet sheet2 = new Sheet(2, 3,clazz, "sheet", null);
			writer.write(list, sheet2);
			//response.setContentType("multipart/form-data");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
			//response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
			out.flush();
		} catch (Exception e) {
			//导出失败
		} finally {
			writer.finish();
			try {
				out.close();
			} catch (IOException e) {
				//流关闭失败
			}
		}
	}
}

