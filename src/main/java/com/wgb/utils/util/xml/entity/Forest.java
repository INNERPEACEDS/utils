package com.wgb.utils.util.xml.entity;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author INNERPEACE
 * @date 2019/12/18 16:01
 */
public class Forest {
	private String flower;

	@XmlElement
	public String getFlower() {
		return flower;
	}

	public void setFlower(String flower) {
		this.flower = flower;
	}
}
