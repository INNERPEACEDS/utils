package com.wgb.utils.util.spi.entity;

/**
 * @author INNERPEACE
 * @date 2019/10/24 17:15
 */
public class RaceCar extends Car {
	public String newColor;

	public RaceCar(Wheel wheel, String velocity, String length, String newColor) {
		this.wheel = wheel;
		this.velocity = velocity;
		this.length = length;
		this.newColor = newColor;
	}

	public void setNewColor(String color) {
		this.newColor = color;
	}

	public String getNewColor() {
		return newColor;
	}


	@Override
	public String toString() {
		return "RaceCar{" +
				"newColor='" + newColor + '\'' +
				", velocity='" + velocity + '\'' +
				", length='" + length + '\'' +
				", wheel=" + wheel +
				'}';
	}
}
