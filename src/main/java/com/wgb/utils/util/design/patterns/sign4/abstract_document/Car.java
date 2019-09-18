package com.wgb.utils.util.design.patterns.sign4.abstract_document;

import java.util.Map;

public class Car extends AbstractDocument implements HasModel, HasPrice, HasParts {

	public Car(Map<String, Object> properties) {
		super(properties);
	}

}