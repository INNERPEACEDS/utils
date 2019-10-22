package com.wgb.utils.util.annotation;

/**
 * @author INNERPEACE
 * @date 2019/4/1 13:28
 **/
public class PasswordUtils {
	@UseCase.UseCasesInterface(id="47",description="Passwords must contain at least one numeric")
	public boolean validatePassword(String password) {
		return (password.matches("\\w*\\d\\w*"));
	}

	@UseCase.UseCasesInterface(id ="48")
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}
}
