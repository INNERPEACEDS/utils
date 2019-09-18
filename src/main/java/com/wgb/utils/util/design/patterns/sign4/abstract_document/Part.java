package com.wgb.utils.util.design.patterns.sign4.abstract_document;

import java.util.Map;

/**
 * @author INNERPEACE
 * @date 2019/8/27 15:10
 */
public class Part extends AbstractDocument implements HasModel, HasPrice, HasType{
	public Part(Map<String, Object> properties){
		super(properties);
	}
}
