package com.wgb.utils.util.design.patterns.sign4.abstract_document;

import java.util.Optional;

public interface HasModel extends Document{
	default Optional<String> getModel() {
		return Optional.ofNullable((String) get(Property.MODEL.toString()));
	}
}
