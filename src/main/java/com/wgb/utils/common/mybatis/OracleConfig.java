package com.wgb.utils.common.mybatis;

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
 * @author : innerpeace
 * @date : 2018/11/30 16:53
 */
@Configuration
@MapperScan(basePackages = "com.wgb.utils.dao.oracle", sqlSessionTemplateRef = "oracleSqlSessionTemplate")
public class OracleConfig {
	/**
	 * 创建数据源 oracle
	 */
	@Bean(name = "oracleDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.oracle")
	public DataSource oracleDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * 创建sql工场
	 *
	 * @param dataSource 数据源
	 * @return 工场
	 * @throws Exception 创建异常
	 */
	@Bean(name = "oracleSqlSessionFactory")
	@Primary
	public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource dataSource)
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

	@Bean(name = "oracleTransactionManager")
	@Primary
	public DataSourceTransactionManager oracleTransactionManager(@Qualifier("oracleDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "oracleSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate oracleSqlSessionTemplate(
			@Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
