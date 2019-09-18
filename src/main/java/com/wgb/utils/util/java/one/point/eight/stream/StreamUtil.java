package com.wgb.utils.util.java.one.point.eight.stream;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author INNERPEACE
 * @date 2019/8/28
 */
@Slf4j
public class StreamUtil {
	public static Stream<String> conversion(String str) {
		List<String> result = new ArrayList<>();
		log.info("str:{}", str);
		for (char c : str.toCharArray()) {
			result.add(String.valueOf(c));
		}
		return result.stream();
	}

	public static void main(String[] args) {
		String[] yourName = {"your", "name", "name"};
		Stream<Stream<String>> streamStream = Arrays.stream(yourName).distinct().map(StreamUtil::conversion);
		streamStream.forEach(stream -> stream.forEach(data1 -> log.info("data1:{}", data1)));
//		streamStream.forEach(data1 -> log.info("data1:{}", data1));
//		log.info("map:{}", streamStream);

		Stream<String> streamStream1 = Arrays.stream(yourName).flatMap(StreamUtil::conversion);
		streamStream1.forEach(data2 -> log.info("data2:{}", data2));
//		log.info("map:{}", streamStream1);

	}
}
