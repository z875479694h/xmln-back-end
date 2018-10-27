package xyz.hcworld.xmlnbackend.db;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.SQLException;


/**
 * 描述:Druid连接池配置
 *
 * @author 张红尘
 * @create 2018-04-19 22:12
 */

public class Druid {
    /**
     * 数据源
     * */
    public static DruidDataSource datasource = new DruidDataSource();

    static {
        datasource.setUrl("jdbc:mysql://localhost:3306/XMLN?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false");
        datasource.setUsername("xmln");
        datasource.setPassword("Xmln123.");
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setInitialSize(5);
        datasource.setMinIdle(5);
        datasource.setMaxActive(50);
        datasource.setMaxWait(60000);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        System.out.println(datasource);
    }

    /**
     * 获得数据库连接
     */
    public static DruidPooledConnection getConnection() throws SQLException {
        return datasource.getConnection();
    }

}
