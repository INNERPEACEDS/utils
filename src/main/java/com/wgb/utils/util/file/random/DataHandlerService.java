package com.wgb.utils.util.file.random;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/12/2
 */
@Slf4j
public abstract class DataHandlerService {

	/**
	 * 处理文件前几行数据
	 * @param point 前几行起始地址
	 * @return 结束地址
	 */
	long handlerHeadLineOfFile(long point){
		return point;
	};

	/**
	 * 处理文件结尾几行数据
	 * @param point 最后几行数据的起始地址
	 * @return 结束地址
	 */
	long handlerFootLineOfFile(long point) {
		return point;
	};

	/**
	 * 处理行数据
	 * @param data 待处理（读取一行所得）数据
	 * @param list
	 * @param bufferedWriter
	 * @return
	 */
	void lineDataHandler(String data, List<String> list, BufferedWriter bufferedWriter){
		try {
			bufferedWriter.write(data + "\n");
		} catch (IOException e) {
			log.error("向分块文件中写数据异常");
		}
		list.add(data);
	}

	/**
	 * 处理线程分割块数据
	 * @param list 待处理数据
	 * @param bufferedWriter
	 */
	void dataHandler(List<String> list, BufferedWriter bufferedWriter) {
		list.clear();
	}
}
