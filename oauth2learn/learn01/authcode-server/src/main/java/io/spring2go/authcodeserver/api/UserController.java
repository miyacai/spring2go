package io.spring2go.authcodeserver.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 获取用户相关信息
 *
 * 这个实现最关键的地方就是授权服务器（OAuth2AuthorizationServer）和资源服务器（OAuth2ResourceServer）的配置
 */
@Controller
public class UserController {

	// 资源API
    @RequestMapping("/api/userinfo")
    public ResponseEntity<UserInfo> getUserInfo() {
        //这个用户是从 SecurityContextHolder 里面获取的
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        //登录之后就可以返回user
        String email = user.getUsername() + "@spring2go.com";

        UserInfo userInfo = new UserInfo();
        userInfo.setName(user.getUsername());
        userInfo.setEmail(email);

        //ResponseEntity返回到浏览器上。作用：替代了@ResponseBody @ResponseStatus
        return ResponseEntity.ok(userInfo);
    }

}