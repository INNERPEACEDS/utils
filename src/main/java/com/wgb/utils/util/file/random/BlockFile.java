package com.wgb.utils.util.file.random;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author INNERPEACE
 * @date 2019/11/13
 */
@Slf4j
public class BlockFile {
	private String filePath;

	private int numberOfThread = 5;
	/**
	 * 空闲线程存活时间
	 */
	private long keepAliveTime = 5;

	private ThreadPoolExecutor executor;

	private DataHandlerService dataHandlerService;

	private boolean isDeleteFirstLine = false;

	public BlockFile(String filePath, DataHandlerService dataHandlerService) {
		this.filePath = filePath;
		this.dataHandlerService = dataHandlerService;
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
				.setNameFormat("demo-pool-%d").build();
		this.executor = new ThreadPoolExecutor(numberOfThread, numberOfThread, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), namedThreadFactory);
	}

	public BlockFile(String filePath, int numberOfThread, int keepAliveTime, DataHandlerService dataHandlerService, boolean isDeleteFirstLine) {
		this.filePath = filePath;
		this.numberOfThread = numberOfThread;
		this.keepAliveTime = keepAliveTime;
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
				.setNameFormat("demo-pool-%d").build();
		this.executor = new ThreadPoolExecutor(numberOfThread, numberOfThread, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), namedThreadFactory);
		this.dataHandlerService = dataHandlerService;
		this.isDeleteFirstLine = isDeleteFirstLine;
	}


	public boolean readFileBySplit() {
		final long startTime = System.currentTimeMillis();
		long start, end;
		try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")){
			long fileLength = raf.length();
			long oneLineLength = 0;
			if (isDeleteFirstLine) {
				//  RandomAccessFile中readLine只读\r\n之前数据,获取\r\n之前的数据和长度
				// 例如：abc\r\n; readLine得到的数据为：abc，readLine.length=3;
				// 此时getFilePointer得到的是下一行第一个数据的地址
				// 该表达式oneLineLength：window系统地址指向第一行最后一字符（\n）的地址；linux系统地址指向第二行第一个字符地址
				oneLineLength = raf.readLine().length() + 1;
			}
			// 文件前几行数据处理
			oneLineLength = dataHandlerService.handlerHeadLineOfFile(oneLineLength);
			// 文件最后几行数据处理

			// 每个线程处理的块大小
			long block = (fileLength - oneLineLength) / numberOfThread;
			log.info("文件大小：{},每个线程处理文件大小：{}", fileLength, block);
			// 以完整的换行分割文件 所带来的偏移量。
			start = oneLineLength;
			List<Map<String, Long>> storeList = new ArrayList<>();
			for (int i = 0; i < numberOfThread; i++) {
				/*if () {
				}*/
				if (i != (numberOfThread - 1)) {
					int num = 0;
					end = start + block - 1;
					if (start > end) {
						break;
					}
					if (end > fileLength) {
						end = fileLength - 1;
					}
					raf.seek(end);
					try {
						while (true) {
							char charge = (char) raf.readByte();
							if (charge == '\r' || charge == '\n') {
								break;
							}
							num++;
							end++;
						}
						log.info("num={}", num);
					} catch (EOFException e) {
						log.error("End", e);
					}
				} else {
					end = fileLength;
				}
				HashMap<String, Long> storeMap = new HashMap<>(2);
				storeMap.put("start", start);
				storeMap.put("end", end);
				storeList.add(storeMap);
				storeMap = null;
				log.info("start={},end={}", start, end);
				start = end + 1;
			}
			CountDownLatch latch = new CountDownLatch(storeList.size());
			if (storeList.size() > 0) {
				for (Map<String, Long> tempMap : storeList) {
					// 放tempMap到线程类中
					executor.execute(new FileHandlerThread(tempMap, filePath, latch, dataHandlerService));
				}
			}
			latch.await();
			log.info("运行时间为：{}ms", (System.currentTimeMillis() - startTime));
			return true;
		} catch (Exception e) {
			log.error("文件分快异常：{}", e);
		} finally {
			executor.shutdown();
		}
		return false;
	}

	public boolean readFile() {
		final long startTime = System.currentTimeMillis();
		int start, end;

		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(filePath, "r");
			int fileLength = (int) raf.length();
			System.out.println(fileLength);
			raf.seek(1);
			for (int i = 0; i < fileLength; i++) {
				char charge = (char) raf.readByte();
				System.out.println("第" + (i+1 ) + "个数据：" + charge);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
