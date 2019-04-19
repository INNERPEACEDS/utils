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
 * @date 2019/4/18 11:19
 **/
@Slf4j
@Configuration
// @MapperScan(basePackages = "com.wgb.utils.dao.oracle", sqlSessionTemplateRef = "slave2SqlSessionTemplate")
public class Slave2Config {
	/**
	 * 创建数据源 slave2DataSource
	 */
	@Bean(name = "slave2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.slave2")
	public DataSource slave2DataSource() {
		log.info("创建slave2DataSource数据源");
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
	@Bean(name = "slave2SqlSessionFactory")
	@Primary
	public SqlSessionFactory slave2SqlSessionFactory(@Qualifier("slave2DataSource") DataSource dataSource)
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

	@Bean(name = "slave2TransactionManager")
	@Primary
	public DataSourceTransactionManager slave2TransactionManager(@Qualifier("slave2DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "slave2SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate slave2SqlSessionTemplate(
			@Qualifier("slave2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}*/
}