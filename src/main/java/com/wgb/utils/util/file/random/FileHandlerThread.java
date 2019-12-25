package com.wgb.utils.util.file.random;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author INNERPEACE
 * @date 2019/12/2
 */
@Slf4j
public class FileHandlerThread extends Thread {
	/**
	 * 数据结构：必须包含键start和键end
	 * {"start":0,"end":10}
	 */
	private Map<String, Long> map;

	private String filePath;

	private CountDownLatch countDownLatch;

	private DataHandlerService dataHandlerService;

	public FileHandlerThread(Map<String, Long> map, String filePath, CountDownLatch countDownLatch, DataHandlerService dataHandlerService) {
		this.map = map;
		this.filePath = filePath;
		this.countDownLatch = countDownLatch;
		this.dataHandlerService = dataHandlerService;

	}

	@Override
	public void run() {
		String blockFilePath = new File(filePath).getParent() + "/blockFile/ID" + Thread.currentThread().getId() + "NAME" + Thread.currentThread().getName() + ".txt";
		File file = new File(blockFilePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				log.error("创建文件{}时出现异常", file.getPath(), e);
			}
		}
		try (
				BufferedRandomAccessFile bufferedRandomAccessFile = new BufferedRandomAccessFile(filePath, "r");
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))
		) {

			long start = map.get("start");
			long end = map.get("end");
			bufferedRandomAccessFile.seek(start);
			List<String> list = new ArrayList<>();
			while (start < end) {
				String lineData = bufferedRandomAccessFile.readLine();
				start = bufferedRandomAccessFile.getFilePointer();
				lineData = new String(lineData.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
				// TODO: 处理数据lineData
				dataHandlerService.lineDataHandler(lineData, list, bw);
			}
			// TODO: 处理该线程块所得所有行数据
			dataHandlerService.dataHandler(list, bw);
		} catch (Exception e) {
			log.error(Thread.currentThread().getName() + ":分割文件处理行数据异常", e);
		} finally {
			countDownLatch.countDown();
		}
	}
}
