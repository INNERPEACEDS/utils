package com.wgb.utils.service.system;

import com.wgb.utils.entity.system.UserOnline;

import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/8/30
 */
public interface SessionService {
	List<UserOnline> list();

	void forceLogout(String id);
}
