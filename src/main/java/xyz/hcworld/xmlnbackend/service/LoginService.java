package xyz.hcworld.xmlnbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.hcworld.xmlnbackend.model.user.Account;

/**
 * @author: 张红尘
 * @date: 2018/4/17 11:16
 * @Description: 用户登陆验证
 */
public class LoginService {

    @Autowired
    private JdbcTemplate jdbcTemplate;//JDBC数据库接口对象（从Spring底层自动加载）

    public boolean isValidPassword(Account credentials) {
        String userName = credentials.getUsername();
        String password = credentials.getPassword();


        return "admin".equals(userName)
                && "admin".equals(password);
    }




}
