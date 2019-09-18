package com.wgb.utils.util.design.patterns.sign4.abstract_document;

import java.util.stream.Stream;

/**
 * @author INNERPEACE
 * @date 2019/8/27 14:56
 */
public interface HasParts extends Document{
	default Stream<Part> getParts() {
		return children(Property.PARTS.toString(), Part::new);
	}
}
