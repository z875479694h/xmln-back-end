package xyz.hcworld.xmlnbackend.api;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hcworld.xmlnbackend.filter.JwtAuthenticationFilter;
import xyz.hcworld.xmlnbackend.model.token.Token;
import xyz.hcworld.xmlnbackend.model.token.TokenResult;
import xyz.hcworld.xmlnbackend.util.JwtUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static xyz.hcworld.xmlnbackend.util.JwtUtil.USER_NAME;

/**
 * @author: 张红尘
 * @date: 2018/4/17 10:29
 * @Description: 登陆注册接口类
 */
@RestController
public class LoginToRegister {

    @GetMapping("/api/protected")
    public @ResponseBody
    Object hellWorld(@RequestHeader(value = USER_NAME) String userId) {
        return "Your user id is阿斯顿阿三 '" + userId + "'";
    }

    @PostMapping("/user/login")
    public Object login(HttpServletResponse response, @RequestBody final Account account) {
        if (isValidPassword(account)) {
            String jwt = JwtUtil.generateToken(account.username);
            TokenResult tokenResult = new TokenResult(new Token(jwt, "86400", "Bearer"));
            return new HashMap<String, Object>() {{
                put("statusCode", "000000");
                put("desc", "登录成功");
                put("result", tokenResult);
            }};
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }


    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        registrationBean.setFilter(filter);
        return registrationBean;
    }

    private boolean isValidPassword(Account credentials) {
        return "admin".equals(credentials.username)
                && "admin".equals(credentials.password);
    }


    public static class Account {
        public String username;
        public String password;
    }

}
