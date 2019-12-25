package com.wgb.utils.util.xml.entity;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author INNERPEACE
 * @date 2019/12/18 16:08
 */
public class Thinking {
	private String selfless;

	private String selfish;

	@XmlElement
	public String getSelfless() {
		return selfless;
	}

	public void setSelfless(String selfless) {
		this.selfless = selfless;
	}

	@XmlElement
	public String getSelfish() {
		return selfish;
	}

	public void setSelfish(String selfish) {
		this.selfish = selfish;
	}
}
