package com.wgb.utils.util.xml.entity;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author INNERPEACE
 * @date 2019/12/18 15:54
 */
public class Human {

	private String action;

	private Thinking thinking;

	@XmlElement
	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	@XmlElement
	public Thinking getThinking() {
		return thinking;
	}

	public void setThinking(Thinking thinking) {
		this.thinking = thinking;
	}
}
