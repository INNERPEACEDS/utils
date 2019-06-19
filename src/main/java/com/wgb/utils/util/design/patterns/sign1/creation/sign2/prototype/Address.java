package com.wgb.utils.util.design.patterns.sign1.creation.sign2.prototype;

import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 * @date 2019/6/19 13:31
 **/
@Slf4j
class Address implements Cloneable {
	private String country;
	private String province;
	private String city;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [country=" + country + ", province=" + province
				+ ", city=" + city + "]";
	}

	public Address(String country, String province, String city) {
		super();
		this.country = country;
		this.province = province;
		this.city = city;
	}

	@Override
	public Address clone() {
		Address address = null;
		try {
			address = (Address) super.clone();
		} catch (CloneNotSupportedException e) {
			log.info("克隆异常", e);
		}
		return address;
	}
}
