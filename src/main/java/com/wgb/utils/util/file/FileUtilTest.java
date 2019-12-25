package com.wgb.utils.util.file;

import com.wgb.utils.util.file.random.BlockFile;
import com.wgb.utils.util.file.random.DataHandlerService;

/**
 * @author INNERPEACE
 * @date 2019/11/13
 */
public class FileUtilTest {
	public static void main(String[] args) {
		blockFileTest();
	}

	public static void blockFileTest() {
		String filePath = "E:\\null\\a.txt";
		// new BlockFile(filePath).readFile();
		new BlockFile(filePath, new DataHandlerService() {}).readFileBySplit();
	}
}
