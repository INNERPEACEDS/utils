package com.wgb.utils.util.spi.entity;

/**
 * @author INNERPEACE
 * @date 2019/10/24 17:09
 */

public class Car {
	public String velocity;

	public String length;

	public Wheel wheel;


	public String getVelocity() {
		return velocity;
	}

	public void setVelocity(String velocity) {
		this.velocity = velocity;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Wheel getWheel() {
		return wheel;
	}

	public void setWheel(Wheel wheel) {
		this.wheel = wheel;
	}


	@Override
	public String toString() {
		return "Car{" +
				"velocity='" + velocity + '\'' +
				", length='" + length + '\'' +
				", wheel=" + wheel +
				'}';
	}
}
