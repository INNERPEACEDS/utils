package com.wgb.utils.test.thead;

import com.wgb.utils.util.excel.Tools;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author INNERPEACE
 * @date 2019/12/26 16:44
 */
@Slf4j
public class ObtainData implements Callable<List<String>> {
	public int index;
	public ObtainData(int i) {
		index = i;
	}
	@Override
	public List<String> call() throws Exception {
		log.info("call index 读 {}", index + 1);
		Thread.sleep(2000);
		return getStrByIndex(index);
	}

	public List<String> getStrByIndex(int i) {
		List<String> list = new ArrayList<>();
		for (int j = 0; j < i + 1; j++) {
			list.add((i + 1) + Tools.getRandomNumber(2));
		}
		return list;
	}
}
