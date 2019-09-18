package com.wgb.utils.util.design.patterns.sign4.abstract_document;

import java.util.Optional;

/**
 * @author INNERPEACE
 * @date 2019/8/27
 */
public interface HasPrice extends Document {
	default Optional<Number> getPrice() {
		return Optional.ofNullable((Number) get(Property.PRICE.toString()));
	}
}
