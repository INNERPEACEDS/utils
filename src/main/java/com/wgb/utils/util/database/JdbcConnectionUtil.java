package com.wgb.utils.util.database;

import com.wgb.utils.util.constants.symbol.SymbolConstant;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author INNERPEACE
 * @date 2018/12/17 19:16
 **/
@Slf4j
public class JdbcConnectionUtil {
    /**
     * 连接配置信息
     */
    private static JdbcConfig jdbcConfig = JdbcConfig.getInstance();

    private static String COMMAND_TYPE = "0";

    private static String POND_TYPE = "1";

    /**
     * 与数据库建立连接
     * @return 连接
     */
    private static Connection getConnection() {
        log.info("---开始连接数据库---");
        // 连接数据校验
        if (jdbcConfig == null) {
            log.error("jdbc连接参数为空");
            throw new RuntimeException("jdbc连接参数为空");
        }
        Connection conn = null;
        String type = jdbcConfig.getType();
        if (COMMAND_TYPE.equals(type)) {
            conn = getConnectionByCommand();
        }
        if (POND_TYPE.equals(type)) {
            conn = getConnectionByPond();
        }
        return conn;
    }

    /**
     * 连接池方式连接数据库
     * @return
     */
    private static Connection getConnectionByPond() {
        Connection conn = null;
        return conn;
    }

    /**
     * 命令方式连接数据库
     * @return
     */
    private static Connection getConnectionByCommand() {
        Connection conn;
        String driver = jdbcConfig.getDriver();
        String url = jdbcConfig.getUrl();
        String user = jdbcConfig.getUser();
        String password = jdbcConfig.getPassword();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            log.error("常规方式连接数据异常", e);
            throw new RuntimeException("常规方式连接数据异常");
        }
        log.info("常规方式连接数据库成功");
        return conn;
    }

    public static void closeAll(Connection conn, Statement state) {
        closeAll(conn, state, null);
    }

    /**
     * 释放所有连接
     * @param conn  连接
     * @param state 语句
     * @param rs    结果集
     */
    public static void closeAll(Connection conn, Statement state, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("关闭数据库连接异常", e);
            }
        }
        try {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
            } catch (Exception e) {
                rs = null;
            }

            try {
                if (state != null) {
                    state.close();
                    state = null;
                }
            } catch (Exception e) {
                state = null;
            }

            try {
                if (conn != null) {
                    if (!conn.getAutoCommit()) {
                        conn.commit();
                    }
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException e2) {
                    }
                    try {
                        conn.close();
                    } catch (Exception e1) {
                    }
                    conn = null;
                }
            }
        } finally {
            rs = null;
            state = null;
            conn = null;
            log.info("---关闭数据库---");
        }
    }

    /**
     * 增删改操作
     * @param sql    执行sql语句（含占位符）
     * @param params 占位符对应参数值
     * @return       增刪改操作结果
     */
    public static boolean operateUpdate(String sql, List<Object> params) {
        // 影响的行数
        int res;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 创建数据库连接
            conn = getConnection();
            // 装载sql语句
            ps = statementLoad(conn, sql, params);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("数据库连接更新异常", e);
            throw new RuntimeException("数据库连接更新异常");
        } finally {
            closeAll(conn, ps);
        }
        return res > 0;

    }

    /**
     * 查询操作（使用泛型方法和反射机制进行封装）
     * @param sql    执行sql语句（含占位符）
     * @param params 占位符对应参数值
     * @param cls    查询获取的对象类型
     * @return       查询结果集List
     */
    public static <T> List<T> operateQuery(String sql, List<Object> params, Class<T> cls) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> data = new ArrayList<>();
        try {
            conn = getConnection();
            if (conn == null) {
                throw new RuntimeException("数据库连接异常");
            }
            ps = statementLoad(conn, sql, params);
            rs = ps.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            log.info("元数据对象：{}", rsd.toString());
            while (rs.next()) {
                T m = cls.newInstance();
                for (int i = 0; i < rsd.getColumnCount(); i++) {
                    // 得到所有数据库中的列名名称
                    String colName = rsd.getColumnName(i + 1);
                    // 转换成驼峰规则的命名
                    String camelColName = camelName(colName);
                    log.info("数据库列名：{}；实体类驼峰属性名：{}", colName, camelColName);
                    // 获得列所对应的值
                    Object value = rs.getObject(camelColName);
                    // 通过列名获取类的属性
                    Field field = cls.getDeclaredField(camelColName);
                    // 给私有属性设置可访问权
                    field.setAccessible(true);
                    // 给对象的私有属性赋值
                    field.set(m, value);
                }
                data.add(m);
            }
        } catch (Exception e) {
            log.info("执行数据库连接查询异常,sql:[{}],占位符参数：{}", sql, params, e);
            throw new RuntimeException("执行数据库连接查询异常");
        } finally {
            closeAll(conn, ps, rs);
        }
        return data;
    }

    /**
     * 装载sql语句
     * @param conn   数据库连接
     * @param sql    执行sql语句（含占位符）
     * @param params 占位符对应参数值
     * @return 返回装载sql的语句
     */
    private static PreparedStatement statementLoad(Connection conn, String sql, List<Object> params) {
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                int i = 0;
                for (Object obj : params) {
                    ps.setObject(++i, obj);
                }
            }
        } catch (SQLException e) {
            log.info("数据库连接装载sql异常，sql[{}],占位符参数[{}]", sql, params);
            throw new RuntimeException("数据库连接装载sql异常");
        }
        return ps;
    }

    /**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：helloWorld->HELLO_WORLD
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->helloWorld
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 为空不转化
        if (name == null || name.isEmpty()) {
            return "";
        }
        // 不含下划线，将所有字母小写
        if (!name.contains(SymbolConstant.DATABASE_JOINT_MARK)) {
            return name.toLowerCase();
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split(SymbolConstant.DATABASE_JOINT_MARK);
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
                continue;
            }
            // 其他的驼峰片段，首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String name = camelName("BOOK_NAME");
        log.info("camel:{}", name);
        String s = underscoreName(name);
        log.info("underscore:{}", s);
    }
}
