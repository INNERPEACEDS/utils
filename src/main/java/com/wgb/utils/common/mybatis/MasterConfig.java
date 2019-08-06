package com.wgb.utils.common.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
 * @date 2019/3/29 16:40
 **/
@Slf4j
@Configuration
// @EnableAutoConfiguration
// @MapperScan(basePackages = "com.wgb.utils.dao.oracle", sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class MasterConfig {
	/**
	 * 创建数据源 masterDataSource
	 */
	@Bean(name = "masterDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource masterDataSource() {
		DataSource masterDataSource = DataSourceBuilder.create().build();
		log.info("创建masterDataSource数据源,数据源为：{}", masterDataSource);
		return masterDataSource;
	}
/*
	*//**
	 * 创建sql工场
	 *
	 * @param dataSource 数据源
	 * @return 工场
	 * @throws Exception 创建异常
	 *//*
	@Bean(name = "masterSqlSessionFactory")
	@Primary
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource)
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

	@Bean(name = "masterTransactionManager")
	@Primary
	public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "masterSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate masterSqlSessionTemplate(
			@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}*/
}

