基于授权码模式+Spring Security OAuth2的最简授权服务器
======

# 操作方式

### 1. 获取授权码

浏览器请求：
表示通过浏览器向授权服务器做授权（这里的访问地址，其实就是授权服务器的4个端点中的之一，此时是授权端点）

http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9001/callback&response_type=code&scope=read_userinfo

**注意：state参数暂忽略**
输入配置文件中配置的用户密码

响应案例：

http://localhost:9001/callback?code=8uYpdo

通过code=8uYpdo 这个授权码来交换获取token
### 2. 获取访问令牌（此时是token端点）  通过postman进行此次的post请求，采用Basic Auth方式。
-H表示头信息
-D表示参数信息

curl -X POST --user clientapp:112233 http://localhost:8080/oauth/token -H
"content-type: application/x-www-form-urlencoded" -d
"code=8uYpdo&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalh
ost%3A9001%2Fcallback&scope=read_userinfo"

案例响应：

```json
{
    "access_token": "36cded80-b6f5-43b7-bdfc-594788a24530",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "read_userinfo"
}
```

用了access_token 就可以访问用户的数据了
### 3. 调用API（postman此时的模式是Bearer Token）

curl -X GET http://localhost:8080/api/userinfo -H "authorization: Bearer 36cded80-b6f5-43b7-bdfc-594788a24530"

案例响应：

```json
{
    "name": "bobo",
    "email": "bobo@spring2go.com"
}
```