package com.wgb.utils.util.parameter;

import com.wgb.utils.entity.user.UserSession;
import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 * @date 2019/8/22
 */
@Slf4j
public class ParameterCheckTest {
	public static void main(String[] args) {
		String parameter = "22";
		UserSession userSession = new UserSession();
		ParameterCheckUtil.nullReset(parameter, userSession, "userId");
		log.info("用户的userId为：{}", userSession.getUserId());

	}
}
