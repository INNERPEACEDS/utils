package com.wgb.utils.util.design.patterns.sign4.abstract_document;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Document Interface
 * @author INNERPEACE
 * @date 2019/8/27 14:37
 */
public interface Document {

	/**
	 * Puts the value related to key
	 * @param key   element key
	 * @param value element value
	 * @return
	 */
	Void put(String key, Object value);

	/**
	 * Gets the value for the key
	 * @param key element key
	 * @return    value or null
	 */
	Object get(String key);

	/**
	 * Gets the stream of the children documents
	 * @param key         element key
	 * @param constructor constructor of children class
	 * @param <T>
	 * @return            children documents
	 */
	<T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor);
}
