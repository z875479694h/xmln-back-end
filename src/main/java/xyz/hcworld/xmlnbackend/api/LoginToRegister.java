package xyz.hcworld.xmlnbackend.api;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import xyz.hcworld.xmlnbackend.filter.JwtAuthenticationFilter;
import xyz.hcworld.xmlnbackend.model.token.Token;
import xyz.hcworld.xmlnbackend.model.token.TokenResult;
import xyz.hcworld.xmlnbackend.model.user.Account;
import xyz.hcworld.xmlnbackend.service.LoginService;
import xyz.hcworld.xmlnbackend.util.JwtUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static xyz.hcworld.xmlnbackend.util.JwtUtil.USER_NAME;

/**
 * 登陆接口
 * @author: 张红尘
 * @date: 2018/4/19 0:18
 */
@RestController
public class LoginToRegister {
    LoginService login = new LoginService();

    /**
     * 测试
     *
     * @author: 张红尘
     * @date: 2018/4/19
     **/
    @GetMapping("/api/protected")
    public @ResponseBody
    Object hellWorld(@RequestHeader(value = USER_NAME) String userId) {
        return "Your user id is'" + userId + "'..";
    }

    /**
     *登陆
     * @author: 张红尘
     * @date: 2018/4/19
    **/
    @PostMapping("/user/login")
    public Map<String, Object> login(@RequestBody Account account) throws SQLException {
        Map log = login.isValidPassword(account);
        String userName = "userName";
        String password = "password";
        //账号密码都正确的时候
        if ((boolean) log.get(userName) && (boolean) log.get(password)) {
            String jwt = JwtUtil.generateToken(account.getUsername());
            TokenResult tokenResult = new TokenResult(new Token(jwt, "86400", "Bearer"));
            return new HashMap<String, Object>(3) {{
                put("statusCode", "000000");
                put("desc", "登录成功");
                put("result", tokenResult);
            }};
        } else {
            //账号密码都错误的时候
            return new HashMap<String, Object>(2) {{
                put("statusCode", "111001");
                put("desc", "用户密码错误/用户不存在");
                put("result", "");
            }};
        }
    }

    /**
     * jwt过滤器
     *
     */
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        registrationBean.setFilter(filter);
        return registrationBean;
    }



}
