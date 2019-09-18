package com.wgb.utils.service.system.impl;

import com.wgb.utils.entity.system.UserOnline;
import com.wgb.utils.service.system.SessionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/8/30 17:40
 */
@Service
public class SessionServiceImpl implements SessionService {
	@Override
	public List<UserOnline> list() {
		return null;
	}

	@Override
	public void forceLogout(String id) {

	}
}
