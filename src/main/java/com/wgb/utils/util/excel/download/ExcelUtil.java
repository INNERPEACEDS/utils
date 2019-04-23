package com.wgb.utils.util.excel.download;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类(使用poi3.7版本)
 * @author 2018年08月14日09:25:57
 */
public class ExcelUtil {
    private ExcelUtil() {
    }

    /**
     * 默认日期格式
     */
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";
    /**
     * 默认行高
     */
    private static final Short DEFAULT_COLUMNS_HEIGHT = 400;
    /**
     * 默认行宽
     */
    private static final Short DEFAULT_COLUMNS_WEIGHT = 1700;
    /**
     * 默认excel版本
     */
    private static final Integer DEFAULT_EXCEL_VERSION = 2007;

    /**
     * 提供默认后缀
     */
    public static void workbook2InputStream(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
        workbook2InputStream(response, workbook, fileName, ".xls");
    }

    /**
     * COLNUMS
     * 将工作表输出到浏览器中
     *
     * @param response 响应流
     * @param workbook 创建完成的工作表
     * @param fileName 文件名
     * @param sufferNm 文件后缀名
     * @throws Exception 异常
     */
    public static void workbook2InputStream(HttpServletResponse response, Workbook workbook, String fileName,
                                            String sufferNm) throws Exception {
        try (ServletOutputStream out = response.getOutputStream()) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + new String((fileName).getBytes("UTF-8"), "UTF-8") + sufferNm);
            // 设置下载头信息
            response.setContentType("application nd.ms-excel; charset=utf-8");
            workbook.write(out);
            out.flush();
        }
    }

    /**
     * 提供默认版本excel
     *
     */
    public static Workbook createWorkbook(List<BaseInf> baseInfList, List<?> list)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        String sheetNm = dateToString(new Date());
        return createWorkbook(DEFAULT_EXCEL_VERSION,sheetNm,baseInfList,list);
    }

    private static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 提供默认版本excel
     *
     */
    public static Workbook createWorkbook(String sheetNm, List<BaseInf> baseInfList, List<?> list)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        return createWorkbook(DEFAULT_EXCEL_VERSION,sheetNm,baseInfList,list);
    }

    /**
     * 实体类单层嵌套
     */
    public static Workbook createWorkbook(int version, String sheetNm, List<BaseInf> baseInfList, List<?> list)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        return createWorkbook(version, sheetNm, baseInfList, list, null);
    }

    /**
     * 创建一个数据表 实体类嵌套实体类
     *
     * @param version     excel版本 2007 或者其他
     * @param sheetNm     sheet 名称
     * @param baseInfList 数据基础信息
     * @param list        数据
     * @param innerMethod 实体类多层嵌套
     * @return 构建完成的数据表对象
     * @throws SecurityException         安全异常
     * @throws NoSuchMethodException     方法没有异常
     * @throws InvocationTargetException 调用异常
     * @throws IllegalArgumentException  非法参数异常
     * @throws IllegalAccessException    非法访问异常
     * @see BaseInf
     */
    public static Workbook createWorkbook(int version, String sheetNm, List<BaseInf> baseInfList, List<?> list,
                                          String innerMethod) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        Workbook workbook;
        if (DEFAULT_EXCEL_VERSION.equals(version)) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }
        Sheet sheet = workbook.createSheet(isEmpty(sheetNm) ? "sheet1" : sheetNm);
        // 写入标题
        CellStyle titleStyle = titleStyle(workbook);
        // 创建标题行(第一行)
        Row titleRow = sheet.createRow(0);
        // 设置第一行的行高
        titleRow.setHeight(DEFAULT_COLUMNS_HEIGHT);

        // 设置序号
        sheet.setColumnWidth(0, DEFAULT_COLUMNS_WEIGHT);
        Cell cell = titleRow.createCell(0);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue("序号");
        cell.setCellStyle(titleStyle);
        // 其他标题
        for (int i = 0; i < baseInfList.size(); i++) {
            String titleName = baseInfList.get(i).getTitleName();
            // 设置单元格的宽
            sheet.setColumnWidth(i + 1, titleName.length() * DEFAULT_COLUMNS_WEIGHT);
            cell = titleRow.createCell(i + 1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(titleName);
            cell.setCellStyle(titleStyle);
        }

        /*
         * 写入数据
         *
         * 写入数据按照先行 后列的的方式进行
         *
         */
        CellStyle dataStyle = dataStyle(workbook);
        Row dataRow;
        for (int i = 0; i < list.size(); i++) {
            // 创建行
            dataRow = sheet.createRow(i + 1);
            // 创建列 此处为序号列
            cell = dataRow.createCell(0);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(i + 1d);
            cell.setCellStyle(titleStyle);
            // 序号列创建完毕 开始创建数据列
            for (int j = 0; j < baseInfList.size(); j++) {
                // 创建数据列
                cell = dataRow.createCell(j + 1);
                BaseInf baseInf = baseInfList.get(j);
                // 设值
                Method method;
                Object value;
                if (innerMethod != null) {
                    method = list.get(i).getClass().getMethod(innerMethod);
                    Object obj = method.invoke(list.get(i));
                    method = obj.getClass().getMethod(baseInf.getColumMethod());
                    value = method.invoke(obj);
                } else {
                    method = list.get(i).getClass().getMethod(baseInf.getColumMethod());
                    value = method.invoke(list.get(i));
                }
                String returnType = method.getReturnType().getName().toLowerCase();
                //数据处理类 处理数据库原始生成数据
                Class<?> valueClazz = baseInf.getClazz();
                Method valueMethod = baseInf.getMethod();
                if (valueClazz != null && valueMethod != null) {
                    value = valueMethod.invoke(valueClazz, value);
                }
                cell.setCellStyle(dataStyle);
                // 转义列表
                Map<String, String> transMap = baseInf.getMap();
                // 判断是否需要转义
                if (transMap == null) {
                    if (returnType.contains("string")) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(value == null ? "" : value.toString());
                    } else if (returnType.contains("integer") || returnType.contains("int")
                            || returnType.contains("bigdecimal") || returnType.contains("double")
                            || returnType.contains("long") || returnType.contains("float")) {
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        if (value != null) {
                            cell.setCellValue(new Double(value.toString()));
                        } else {
                            cell.setCellValue(0.0d);
                        }
                    } else if (returnType.contains("date")) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(value == null ? null : sdf.format((Date) value));
                    } else {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(value == null ? "" : value.toString());
                    }
                } else {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String cellValue = value == null ? "" : transMap.get(tse(value.toString()));
                    cell.setCellValue(cellValue == null ? tse(value.toString()) : cellValue);
                }
            }
        }
        // 创建统计行
        // 创建行
        dataRow = sheet.createRow(list.size() + 1);
        // 创建列 此处为序号列
        cell = dataRow.createCell(0);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue("统计");
        cell.setCellStyle(titleStyle);
        for (int i = 0; i < baseInfList.size(); i++) {
            BaseInf baseInf = baseInfList.get(i);
            cell = dataRow.createCell(i + 1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellStyle(dataStyle);
            if (baseInf.getCount() != null) {
                cell.setCellValue(baseInf.getCount());
            } else {
                cell.setCellValue("");
            }
        }
        return workbook;
    }

    private static String tse(String str) {
        return str == null ? "" : str.replace(" ", "").replace("/r", "").replace("/n", "").trim();
    }

    private static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 设置标题样式
     *
     * @param workbook 工作表
     * @return 标题样式
     */
    private static CellStyle titleStyle(Workbook workbook) {
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        // 居中
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);
        titleStyle.setBorderLeft((short) 1);
        titleStyle.setBorderRight((short) 1);
        titleStyle.setBorderBottom((short) 1);
        titleStyle.setBorderTop((short) 1);
        Font font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 12);
        // 选择需要用到的字体格式
        titleStyle.setFont(font);
        return titleStyle;
    }

    /**
     * 数据样式
     *
     * @param workbook 工作表
     * @return 数据样式
     */
    private static CellStyle dataStyle(Workbook workbook) {
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom((short) 1);
        dataStyle.setBorderLeft((short) 1);
        dataStyle.setBorderRight((short) 1);
        dataStyle.setBorderTop((short) 1);
        dataStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        return dataStyle;
    }

}
