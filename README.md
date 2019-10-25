# utils
开发工具封装:
算法、常用工具（线程池分装、时间格式和金额转换、数据的正则处理等）、框架DEMO

# 2019-04-1
#1.自定义注解

包路径：com.wgb.utils.util.annotation

# 2019-08-20
#1.线程池工具封装

包路径：com.wgb.utils.util.concurrency

1.提供要使用线程的类、方法和参数

2.使用线程池工具开启线程执行多线程

#2.金典排序算法

包路径：com.wgb.utils.util.algorithm.sort

1.冒泡排序,平均时间复杂度是O(n^2)

2.选择排序-堆排序,平均时间复杂度是O(n log n)

3.插入排序，平均时间复杂度O(n^2)

4.归并排序，平均时间复杂度O(n log n)

5.快速排序，平均时间复杂度O(n log n)

6.基数排序，平均时间复杂度O(log r B)

7.选择排序,平均时间复杂度O(n^2)

8.希尔排序，平均时间复杂度O(n^(1.3—2))

#3.汉诺塔算法

包路径：com.wgb.utils.util.algorithm.hanoi

# 2019-09-18  
#1.新增 springboot+shiro+Thymeleaf

1.定义一个ShiroConfig，然后配置SecurityManager Bean，SecurityManager为Shiro的安全管理器，管理着所有Subject；
 
2.在ShiroConfig中配置ShiroFilterFactoryBean，其为Shiro过滤器工厂类，依赖于SecurityManager；
 
3.自定义Realm实现，Realm包含doGetAuthorizationInfo()和doGetAuthenticationInfo()方法。

注意：使用shiro时，请关闭热部署。

#2.新增Spring的AOP示例

包路径：com.wgb.utils.util.aop

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

# 2019-10-21
#1.文件中写对象系列号测试

包路径：com.wgb.utils.util.serializable

1.对对象数据序列号写入文件

2.读取文件中的对象进行反序列化

3.再次操作对象数据

#2.连接zookeeper

包路径：com.wgb.utils.util.distributed

1.连接zookeeper并且注册一个默认的监听器

2.获取目录节点数据

3.获取更新的目录节点数据

#3.远程过程调用RPC（Remote Procedure Call）简单实现

包路径：com.wgb.utils.util.rpc

1.provider服务提供者暴露

2.consumer服务消费者请求执行服务提供者提供方法

3.服务端执行服务方法并返回消费者结果

# 2019-10-22
#1.JAVA SPI用例

包路径：com.wgb.utils.util.spi

SPI 全称为 Service Provider Interface，是一种服务发现机制。SPI 的本质是将接口实现类的全限定名配置在文件中，并由服务加载器读取配置文件，加载实现类。这样可以在运行时，动态为接口替换实现类。正因此特性，我们可以很容易的通过 SPI 机制为我们的程序提供拓展功能。

#2.dubbo SPI用例

包路径：com.wgb.utils.util.spi

1.DUBBO SPI 启动自加载实现类

2.DUBBO SPI 自适应扩展加载实现类（生成代理）