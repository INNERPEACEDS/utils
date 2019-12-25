package com.wgb.utils.common.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * 1、定义一个ShiroConfig，然后配置SecurityManager Bean，SecurityManager为Shiro的安全管理器，管理着所有Subject；
 * <p>
 * 2、在ShiroConfig中配置ShiroFilterFactoryBean，其为Shiro过滤器工厂类，依赖于SecurityManager；
 * <p>
 * 3、自定义Realm实现，Realm包含doGetAuthorizationInfo()和doGetAuthenticationInfo()方法
 *
 * @author INNERPEACE
 * @date 2019/3/20
 **/
/**
 * @author INNERPEACE
 * @date 2019/8/30
 */
@Slf4j
//暂时不使用shiro
//@Component
//@Configuration
public class ShiroConfig {

//	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置securityManager（必须设置）
		shiroFilterFactoryBean.setSecurityManager(securityManager);


		// 登录的url
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后跳转的url
		shiroFilterFactoryBean.setSuccessUrl("/loginIndex");
		// 未授权url
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

		// 定义filterChain，静态资源不拦截
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		// druid数据源监控页面不拦截
		filterChainDefinitionMap.put("/druid/**", "anon");
		// 配置退出过滤器，其中具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/", "anon");

		// 非shiro项目功能页面（由于调试时开启热部署，会导致项目报错，所以在使用shiro项目时关闭热部署，此处添加页面为非shiro项目功能）
		filterChainDefinitionMap.put("/rabbitMq/page", "anon");
		filterChainDefinitionMap.put("/rabbitMq/queueOne", "anon");
		filterChainDefinitionMap.put("/rabbitMq/queueTwo", "anon");

		filterChainDefinitionMap.put("/i", "anon");
		filterChainDefinitionMap.put("/index", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/page", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/download", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/upload", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/downloadBookRecord", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/downloadBookRecord1", "anon");
		filterChainDefinitionMap.put("/upload", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/getImage", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/getImage1", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/imageIndex", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/queryImages", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/alterImageInfo", "anon");
		filterChainDefinitionMap.put("/json/page", "anon");
		filterChainDefinitionMap.put("/json/addJson", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/queryBookRecord", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/batchAddCreateDate", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/addBookRecord", "anon");
		filterChainDefinitionMap.put("/downloadAndUpload/deleteBookRecord", "anon");
		filterChainDefinitionMap.put("/loginInfo", "anon");
		filterChainDefinitionMap.put("/notFound.html", "anon");

		filterChainDefinitionMap.put("/font/**", "anon");
		filterChainDefinitionMap.put("/lib/**", "anon");
		// 除上以外所有url都必须认证通过才可以访问，未通过认证自动访问LoginUrl
		// 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边:这是一个坑呢，一不小心代码就不好使了;
		// authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		filterChainDefinitionMap.put("/**", "user");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * shiro缓存管理器;
	 * 需要注入对应的其它的实体类中：
	 * 1、安全管理器：securityManager
	 * 可见securityManager是整个shiro的核心；
	 * @return
	 */
//	@Bean
	public EhCacheManager ehCacheManager(){
		System.out.println("ShiroConfiguration.getEhCacheManager()");
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:properties/shiro/ehcache-shiro.xml");
		return cacheManager;
	}

	/**
	 * 配置 SecurityManager 加载自定义 Realm
	 *
	 * @return
	 */
//	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 加载 shiroRealm
		securityManager.setRealm(shiroRealm());
		// 加载 cookie
		// securityManager.setRememberMeManager(rememberMeManager());

		// 采用以下任意一种缓存方式
		// 1.加载 redisManager
		// securityManager.setCacheManager(cacheManager());
		// 2.注入缓存管理器
		// securityManager.setCacheManager(ehCacheManager());

		// 加载 SessionManager
		// securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * 注册 shiroRealm
	 * @return
	 */
	@Bean
	public ShiroRealm shiroRealm() {
		// 配置 Realm
		return new ShiroRealm();
	}

	/**
	 * cookie对象
	 * @return
	 */
	public SimpleCookie rememberMeCookie() {
		log.info("设置rememberMeCookie");
		// 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		// 设置cookie的过期时间，单位为秒，这里为一天
		cookie.setMaxAge(86400);
		return cookie;
	}

	/**
	 * cookie管理对象
	 * @return
	 */
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		// rememberMe cookie加密的密钥
		cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		return cookieRememberMeManager;
	}

	/**
	 * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
	 * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
	 * 主要是 AuthorizingRealm 类的子类，以及EhCacheManager类。
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		// Shiro生命周期处理器
		log.info("ShiroConfiguration.getLifecycleBeanPostProcessor()");
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
	 * @return
	 */
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	/**
	 * AuthorizationAttributeSourceAdvisor，shiro里实现的 Advisor 类，
	 * 内部使用 AopAllianceAnnotationsAuthorizingMethodInterceptor 来拦截用以下注解的方法。
	 * 老实说，这里注入securityManager，我不知道有啥用，从source上看不出它在什么地方会被调用。
	 *
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}


	/**
	 * Redis 配置
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		return redisManager;
	}

	/**
	 * RedisManager 配置
	 * @return
	 */
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * 配置 thymeleaf 标签
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	@Bean
	public RedisSessionDAO sessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		Collection<SessionListener> listeners = new ArrayList<SessionListener>();

		listeners.add(new ShiroSessionListener());
		sessionManager.setSessionListeners(listeners);
		sessionManager.setSessionDAO(sessionDAO());
		return sessionManager;
	}

}

