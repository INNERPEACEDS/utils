package com.wgb.utils.util.cache;

/**
 * @author INNERPEACE
 * @date 2019/3/29 17:13
 **/

import java.util.List;

public class SmsCodeModel {
	private String phoneNo;

	private String email;

	private String code;

	private long sendTime;


	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static SmsCodeModel getSmsCode(List<SmsCodeModel> list,String email) {
		SmsCodeModel smsCode = null;
		for(SmsCodeModel code : list){
			if(email.equals(code.getEmail())){
				smsCode = code;
				break;
			}
		}
		return smsCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
