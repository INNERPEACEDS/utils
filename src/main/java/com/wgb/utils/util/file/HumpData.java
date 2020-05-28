package com.wgb.utils.util.file;

import com.google.common.base.CaseFormat;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author INNERPEACE
 * @date 2020/4/8 19:19
 */
public class HumpData {
	public static void main(String[] args) throws Exception {
		String filePath = "E:\\deploy\\HumpData.txt";
		String filePath1 = "E:\\deploy\\HumpData1.txt";
		System.out.println(readFile(filePath, filePath1));
	}


	public static String readFile(String path, String path1) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bw = null;
		BufferedReader bw1 = null;
		try {
			bw = new BufferedReader(new FileReader(path));
			bw1 = new BufferedReader(new FileReader(path1));
			String line = null;
			String line1 = null;
			while ((line = bw.readLine()) != null && (line1 = bw1.readLine()) != null) {
				sb.append(line).append("  ").append(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, line)).append("  ").append(line1).append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
			bw1.close();
		}
		return sb.toString();
	}
}
