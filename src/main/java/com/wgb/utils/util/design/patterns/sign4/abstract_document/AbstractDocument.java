package com.wgb.utils.util.design.patterns.sign4.abstract_document;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author INNERPEACE
 * @date 2019/8/27 15:19
 */
public abstract class AbstractDocument implements Document {
	private final Map<String, Object> properties;

	AbstractDocument(Map<String, Object> properties) {
		Objects.requireNonNull(properties, "properties map is required");
		this.properties = properties;
	}

	@Override
	public Void put(String key, Object value) {
		properties.put(key, value);
		return null;
	}

	@Override
	public Object get(String key) {
		return properties.get(key);
	}

	@Override
	public <T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor) {
		Optional<List<Map<String, Object>>> any = Stream.of(get(key)).filter(Objects::nonNull).map(e -> (List<Map<String, Object>>) e).findAny();
		return any.isPresent() ? any.get().stream().map(constructor) : Stream.empty();
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getName()).append("[");
		properties.forEach((key, value) -> builder.append("[").append(key).append(" : ").append(value).append("]"));
		builder.append("]");
		return builder.toString();
	}
}
