package com.wgb.utils.util.database;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * jdbc连接数据库加载配置文件
 */
@Data
@Slf4j
public class JdbcConfig {

    /**
     * 工具类实例
     */
    private static JdbcConfig jdbcConfig = new JdbcConfig();

    /**
     * 连接方式：0 = 常规方式连接;1 = 连接池连接
     */
     private String type;

    /**
     * 驱动
     */
    private String driver;

    /**
     * url地址
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;

    JdbcConfig() {
        PropertiesConfiguration pc;
        try {
            pc = new PropertiesConfiguration("properties/database/jdbc/oracle.properties");
            this.type = pc.getString("jdbc.type");
            this.driver = pc.getString("jdbc.driver");
            this.url = pc.getString("jdbc.url");
            this.user = pc.getString("jdbc.user");
            this.password = pc.getString("jdbc.password");
        } catch (ConfigurationException e) {
            log.error("jdbc连接数据库中加载配置文件出错", e);
        }
    }

    public static JdbcConfig getInstance() {
        return jdbcConfig;
    }

}
