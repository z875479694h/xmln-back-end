package xyz.hcworld.xmlnbackend.db;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.sql.SQLException;


/**
 * 描述:Druid连接池配置
 *
 * @author 张红尘
 * @create 2018-04-19 22:12
 */
@ConfigurationProperties(prefix = "db")
public class Druid {
    public static DruidDataSource datasource = new DruidDataSource();
    @Value("${spring.datasource.url}")
    private static String dbUrl;
    @Value("${spring.datasource.username}")
    private static String username;
    @Value("${spring.datasource.password}")
    private static String password;
    @Value("${spring.datasource.driver-class-name}")
    private static String driverClassName;

    static {
       /*datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);*/
        datasource.setUrl("jdbc:mysql://118.89.25.158:3306/XMLN?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false");
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

    public static DruidPooledConnection getConnection() throws SQLException {
        return datasource.getConnection();
    }

}