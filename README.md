wechatboot
========
#未完成功能
* 自动刷新token
* api返回-1时自动重试
#开始
##配置
Classpath下加入wechatboot.properties
```properties
#appid
wechatboot.appid=your appid
#secret
wechatboot.secret=your secret
#token
wechatboot.token=your token
```
需要启用消息加密（兼容模式或安全模式）额外配置公众平台基础配置中的encodingAESKey，微信会尝试使用上一次的key进行加解密，此项支持多个key以逗号分割
* 说明：异常java.security.InvalidKeyException:illegal Key Size的解决方案
* 在官方网站下载JCE无限制权限策略文件，或者在[这里](https://github.com/uncoseason/wechatboot/tree/master/src/test/resources/jce_policy)获取
* 下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt
* 如果安装了JRE，将两个jar文件放到%JRE_HOME%\lib\security目录下覆盖原来的文件
* 如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件
```properties
#encoding aes key
wechatboot.encodingAESKey=tHABwlsxDHpGfHyiFDqbEZleaP66KE95XlkTZjzqNO8,U5kKZxVS9uVE1TtrFmVGrs92a3TBMhyxfEiq8hj4yF3
```
需要使用支付API Wechatboot.payApi() 需要额外配置微信商户号和微信商户网站设置的商户密钥
```properties
#wechat mch id
wechat.mch.id=
#wechat mch api secret key, must be 32 length
wechat.mch.key=
```
##代码配置
可以在任何时候通过API访问配置对象来加载新的配置
```java
Wechatboot.config().setAppid("your appid");
Wechatboot.config().setSecret("your appid");
Wechatboot.config().setToken("your token");
```
##通过Spring来配置
``` xml
<bean class="com.uncos.wechatboot.common.Config" factory-method="instance">
	<property name="appid" value="your appid"/>
	<property name="secret" value="your appid"/>
	<property name="token" value="your token"/>
</bean>
```
#消息后台
##自定义Servlet
可以通过继承WechatbootServlet来定义自己的微信后台Servlet并Override自己感兴趣的消息或事件处理函数
```java
public class MyWechatbootServlet extends WechatbootServlet {

    /**
     * 文本消息
     *
     * @param textMessage
     * @return
     */
    @Override
    protected Response onTextMessage(TextMessage textMessage) {
        switch (textMessage.getContent()) {
            case "1":
                return createTextResponse("回复一个文本消息");
            case "2":
                return createImageResponse("eqUhUq0pxtaWTj9CGr4ajBnn5Gsu3LpjhnRrGK6qZnAz4xRoCu80jjHFoWYuKINv");
            case "3":
                return createVoiceResponse("eqUhUq0pxtaWTj9CGr4ajBnn5Gsu3LpjhnRrGK6qZnAz4xRoCu80jjHFoWYuKINv");
            case "4":
                return createVideoResponse("FEhrWtq3bESfA9XP2hjoVeUDX8_r3GwkzE1pwbvPn-VzZqNfICbUkCqJuSCaCfkz", "视频标题", "视频介绍");
            case "5":
                return createMusicResponse("音乐标题", "音乐介绍", "http://mp.weixin.qq.com/wiki/home/index.html", "http://mp.weixin.qq.com/wiki/home/index.html", "eqUhUq0pxtaWTj9CGr4ajBnn5Gsu3LpjhnRrGK6qZnAz4xRoCu80jjHFoWYuKINv");
            case "6":
                return createNewsResponse("图文标题","图文介绍","http://mp.weixin.qq.com/debug/zh_CN/htmledition/images/bg/bg_logo1f2fc8.png","http://mp.weixin.qq.com/wiki/home/index.html");
            case "7":
                List<Article> articles = new ArrayList<>();
                Article article1 = new Article();
                article1.setTitle("图文标题1");
                article1.setDescription("图文介绍1");
                article1.setPicUrl("http://mp.weixin.qq.com/debug/zh_CN/htmledition/images/bg/bg_logo1f2fc8.png");
                article1.setUrl("http://mp.weixin.qq.com/wiki/home/index.html");
                articles.add(article1);
                Article article2 = new Article();
                article2.setTitle("图文标题2");
                article2.setDescription("图文介绍2");
                article2.setPicUrl("http://mp.weixin.qq.com/debug/zh_CN/htmledition/images/bg/bg_logo1f2fc8.png");
                article2.setUrl("http://mp.weixin.qq.com/wiki/home/index.html");
                articles.add(article2);
                return createNewsResponse(articles);
            default:
                return null;
        }
    }
}
```
##Servlet注册
``` xml
<servlet>
	<servlet-name>wechatboot-servlet</servlet-name>
	<servlet-class>MyWechatbootServlet</servlet-class>
</servlet>
<servlet-mapping>
	<servlet-name>wechatboot-servlet</servlet-name>
	<url-pattern>/wechatboot</url-pattern>
 </servlet-mapping>
```
##Spring MVC示例
```java
@RequestMapping("/wechatboot")
public String wechatbootServlet(HttpServletRequest httpServletRequest) {
	return new MyWechatbootServlet().feedback(httpServletRequest);
}
```
#API
可以通过Wechatboot类开始访问各种API
```java
Wechatboot.messageApi().sendTextMessage("ouP0Yv3rPy5xPnzLD45g3psCJqZQ", "主动发送一个文本消息");
```

#其他
* 欢迎fork
* 欢迎Star
* 任何问题都可以创建issue或者与我邮件联系（uncoseason@163.com）
