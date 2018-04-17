package xyz.hcworld.xmlnbackend.model.token;

/**
 * @author: 张红尘
 * @date: 2018/4/17 11:03
 * @Description: "result":{
 * "token":{
 * "accessToken":"tokenmessage",
 * "validTime":86400,
 * "tokenType":"Bearer"
 * }
 * }
 */
public class TokenResult {
    Object token;

    /**
     * 默认构造方法
     */
    public TokenResult(Object token) {
        this.token = token;
    }

    /**
     * 获取token结果
     */
    public Object getToken() {
        return token;
    }

    /**
     * 设置token结果
     */
    public void setToken(Object token) {
        this.token = token;
    }


}
