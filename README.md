# utils
开发工具封装:
算法、常用工具（线程池分装、时间格式和金额转换、数据的正则处理等）、框架DEMO

# 2019-09-18  
#1.新增 springboot+shiro+Thymeleaf

1、定义一个ShiroConfig，然后配置SecurityManager Bean，SecurityManager为Shiro的安全管理器，管理着所有Subject；
 
2、在ShiroConfig中配置ShiroFilterFactoryBean，其为Shiro过滤器工厂类，依赖于SecurityManager；
 
3、自定义Realm实现，Realm包含doGetAuthorizationInfo()和doGetAuthenticationInfo()方法。

注意：使用shiro时，请关闭热部署。

#2.新增Spring的AOP示例
使用Spring的AOP切面示例，研究Spring架构详细流程，请看源码，地址：https://github.com/spring-projects/spring-framework

# 2019-09-19  
#1.添加自动选择开发、测试、生产环境下配置文件

1.添加配置文件名称:

开发环境配置文件名称：application-dev.properties

测试环境配置文件名称：application-test.properties

生产环境配置文件名称：application-pro.properties

2.在application.properties中配置spring.profiles.active属性选择开发、测试、生产环境。

#2.添加日志输出文件
日志输出文件按天打印，配置日志文件为resources/logback.xml
