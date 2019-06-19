package com.wgb.utils.listener;
import com.wgb.utils.util.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author INNERPEACE
 * @date 2019/5/16 18:22
 **/
@Slf4j
public class SessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		log.info("创建session" );
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.info("销毁session");
	}

	public static synchronized void sessionDeleted(HttpSession se) {
		if(se != null) {
			// 删除单一登录中记录的变量
			if(SessionUtil.getUserInfo() != null) {
				log.info("删除用户登录Session");
			}
		}
	}
}
