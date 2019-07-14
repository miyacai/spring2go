package io.spring2go.authcodeserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

//授权服务器配置
@Configuration              //标注为这个类是一个配置类
@EnableAuthorizationServer  //告诉springboot我要启用授权服务器
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    /**
     * 配置授权服务器
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        /*
            inMemory：内存模式，也就是信息，token等相关信息是存在内存里面的。这里只是为了试验，生产级肯定是不能用这种模式的
            withClient：支持的客户名称 以及 secret  这两个加起来就是客户凭证。这个数据一般来说是通过授权服务器注册的。这里直接填充到
            这里只是为了试验的目的。
            redirectUris：告诉授权服务器，拿到凭证后跳回到客户端的地址
            authorizedGrantTypes：明确告诉授权服务器只支持哪种模式
            scopes：细分权限

        */
        clients.inMemory()
                .withClient("clientapp")
                .secret("112233")
                .redirectUris("http://localhost:9001/callback")
                // 授权码模式
                .authorizedGrantTypes("authorization_code")
                .scopes("read_userinfo", "read_contacts");
    }

}
