package io.spring2go.authcodeserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//资源服务配置
@Configuration
@EnableResourceServer   //告诉springboot启动资源服务器
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

    /**
     * 告诉资源服务器访问哪些资源的时候需要进行oauth2认证
     * 如果是访问了指定的资源，那么就必须要带着token过来，如果不带token或者错误的token是访问不了的。
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .requestMatchers()
                .antMatchers("/api/**");
    }

}
