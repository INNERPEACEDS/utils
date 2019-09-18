package com.wgb.utils.util.design.patterns.sign4.abstract_document;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author INNERPEACE
 * @date 2019/8/27 15:08
 */
public interface HasType extends Document {

	default Optional<String> getType() {
		return Optional.ofNullable((String) get(Property.TYPE.toString()));
	}
}
