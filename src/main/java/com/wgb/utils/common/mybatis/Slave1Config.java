package com.wgb.utils.common.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

/**
 * @author INNERPEACE
 * @date 2019/3/29 16:54
 **/
@Slf4j
@Configuration
// @MapperScan(basePackages = "com.wgb.utils.dao.oracle", sqlSessionTemplateRef = "slave1SqlSessionTemplate")
public class Slave1Config {
	/**
	 * 创建数据源 slave1DataSource
	 */
	@Bean(name = "slave1DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.slave1")
	public DataSource slave1DataSource() {
		log.info("创建slave1DataSource数据源");
		return DataSourceBuilder.create().build();
	}
/*
	*//**
	 * 创建sql工场
	 *
	 * @param dataSource 数据源
	 * @return 工场
	 * @throws Exception 创建异常
	 *//*
	@Bean(name = "slave1SqlSessionFactory")
	@Primary
	public SqlSessionFactory slave1SqlSessionFactory(@Qualifier("slave1DataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// xml 文件配置
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/oracle/*.xml"));
		// xml 文件配置中实体类默认包名
		bean.setTypeAliasesPackage("com.wgb.utils.entity.oracle");
		return bean.getObject();
	}

	@Bean(name = "slave1TransactionManager")
	@Primary
	public DataSourceTransactionManager slave1TransactionManager(@Qualifier("slave1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "slave1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate slave1SqlSessionTemplate(
			@Qualifier("slave1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}*/
}


