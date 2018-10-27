package xyz.hcworld.xmlnbackend.service;

import com.alibaba.druid.pool.DruidPooledConnection;
import xyz.hcworld.xmlnbackend.db.Druid;
import xyz.hcworld.xmlnbackend.model.user.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登陆验证
 * @author: 张红尘
 * @date: 2018/4/17 11:16
 */
public class LoginService {
    DruidPooledConnection conn = null;
    /**Druid druidDataSource*/
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Map isValidPassword(Account credentials) throws SQLException {
        Map login = new HashMap<String, Boolean>(2);
        try {
            conn = Druid.getConnection();
            String sql = "SELECT UserPassword FROM login WHERE UserAccount=?";
            String userName = credentials.getUsername();
            String password = credentials.getPassword();
            pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
            rs = pst.executeQuery();

            if (rs.next()) {
                login.put("userName", true);
                login.put("password", password.equals(rs.getString("UserPassword")));
            } else {
                login.put("userName", false);
                login.put("password", false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return login;
    }




}
