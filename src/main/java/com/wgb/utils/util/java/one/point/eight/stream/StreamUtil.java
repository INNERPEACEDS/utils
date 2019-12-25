package com.wgb.utils.util.java.one.point.eight.stream;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 1.8新特性：
 * 4.新工具：新的编译工具，如：Nashorn引擎 jjs、 类依赖分析器jdeps。
 * 5.Stream API：新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。
 * 6.Date Time API：加强对日期与时间的处理。
 * 7.Optional 类：Optional 类已经成为 Java 8 类库的一部分，用来解决空指针异常。
 * 8.Nashorn, JavaScript 引擎：Java 8提供了一个新的Nashorn javascript引擎，它允许我们在JVM上运行特定的javascript应用。
 * @author INNERPEACE
 * @date 2019/8/28
 */
@Slf4j
public class StreamUtil {
	public static Stream<String> conversion(String str) {
		log.info("str:{}", str);
		if ("your".equals(str)) {
			// 返回字符串流
			return Stream.of(str);
		}
		List<String> result = new ArrayList<>();
		for (char c : str.toCharArray()) {
			result.add(String.valueOf(c));
		}
		result.add(String.valueOf('#'));
		// 此处返回字符流
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
