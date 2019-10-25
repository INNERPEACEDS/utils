package com.wgb.utils.util.spi.entity;

/**
 * @author INNERPEACE
 * @date 2019/10/24 17:03
 */
public class Wheel {
	public String color;


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Wheel{" +
				"color='" + color + '\'' +
				'}';
	}
}
