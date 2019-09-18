package com.wgb.utils.common.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author INNERPEACE
 * @date 2019/3/29 17:00
 **/
@Slf4j
@Configuration
//@EnableAutoConfiguration
@ComponentScan({"com.wgb.utils"})
@AutoConfigureAfter({MasterConfig.class, Slave1Config.class, Slave2Config.class, OracleConfig.class})
@MapperScan(basePackages = {"com.wgb.utils.dao.oracle","com.wgb.utils.dao.system"}, sqlSessionTemplateRef = "dynamicSqlSessionTemplate")
public class DynamicDataSourceConfiguration {
	/**
	 * 主数据源标记
	 */
	public static final String MASTER = "master";
	/**
	 * 从数据源1标记
	 */
	public static final String SLAVE1 = "slave1";

	/**
	 * 从数据源2标记
	 */
	public static final String SLAVE2 = "slave2";

	/*
	@Qualifier("masterDataSource")
	@Autowired
	@Qualifier("masterDataSource")*/
	@Resource(name = "masterDataSource")
	private DataSource masterDataSource;

	/*@Qualifier("slave1DataSource")
	@Autowired
	@Qualifier("slave1DataSource")*/
	@Resource(name = "slave1DataSource")
	private DataSource slave1DataSource;

	/*
	@Qualifier("slave2DataSource")
	@Autowired
	@Qualifier("slave2DataSource")*/
	@Resource(name = "slave2DataSource")
	private DataSource slave2DataSource;

	@Bean(name="dynamicDataSource")
	@DependsOn(value={"masterDataSource", "slave1DataSource", "slave2DataSource"})
	public DynamicDataSource getDynamicDataSource(){
		DynamicDataSource dds = new DynamicDataSource();
		dds.setDefaultTargetDataSource(masterDataSource);
		Map<Object, Object> targetDataSources = new HashMap<>(16);
		log.info("master:{}", masterDataSource);
		log.info("slave1:{}", slave1DataSource);
		log.info("slave2:{}", slave2DataSource);
		targetDataSources.put(MASTER, masterDataSource);
		targetDataSources.put(SLAVE1, slave1DataSource);
		targetDataSources.put(SLAVE2, slave2DataSource);
		dds.setTargetDataSources(targetDataSources);
		return dds;
	}

	/**
	 * 创建sql工场
	 *
	 * @param dataSource 数据源
	 * @return 工场
	 * @throws Exception 创建异常
	*/
	@Bean(name = "dynamicSqlSessionFactory")
	@Primary
	public SqlSessionFactory dinamicSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource)
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

	@Bean(name = "dynamicTransactionManager")
	@Primary
	public DataSourceTransactionManager dynamicTransactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "dynamicSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate dynamicSqlSessionTemplate(
			@Qualifier("dynamicSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}

