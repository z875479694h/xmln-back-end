package xyz.hcworld.xmlnbackend.model.user;

/**
 * 账号类
 *
 * @author: 张红尘
 * @date: 2018/4/23
 **/
public class Account {
    /**账号*/
    private String username;
    /**密码*/
    private String password;

    /**获取账号*/
    public String getUsername() {
        return username;
    }

    /**设置账号*/
    public void setUsername(String username) {
        this.username = username;
    }

    /**获取密码*/
    public String getPassword() {
        return password;
    }

    /**设置密码*/
    public void setPassword(String password) {
        this.password = password;
    }
}
