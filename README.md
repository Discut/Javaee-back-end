# Javaee-back-end

## 环境

openjdk 17

maven 3

mariadb 10.11.x

redis 7.0.x

## Swagger3 各类配置

<a href="https://blog.csdn.net/weixin_42759726/article/details/111850907">Link</a>

## 日志打印示例

在该系统中，我们对日志打印再一次进行了封装。
包含注解

+ [@PrintLog](https://github.com/Discut/Javaee-back-end/blob/master/src/main/java/com/ybuse/schoolbackend/core/logger/annotation/PrintLog.java)
  用于标注类或方法是否需要日志打印

+ [@ArgsFilter](https://github.com/Discut/Javaee-back-end/blob/master/src/main/java/com/ybuse/schoolbackend/core/logger/annotation/ArgsFilter.java)
  用于标注别切的点，即方法在进行日志打印时是否需要过滤打印的参数。有些时候我们并不想将所有参数的打印出

+ [@TypeFilter](https://github.com/Discut/Javaee-back-end/blob/master/src/main/java/com/ybuse/schoolbackend/core/logger/annotation/TypeFilter.java)
  以下三种都是不同类型的参数过滤器
+ [@IndexFilter](https://github.com/Discut/Javaee-back-end/blob/master/src/main/java/com/ybuse/schoolbackend/core/logger/annotation/IndexFilter.java)
+ [@NameFilter](https://github.com/Discut/Javaee-back-end/blob/master/src/main/java/com/ybuse/schoolbackend/core/logger/annotation/NameFilter.java)

示例

```java

@PrintLog("CustomAccessDeniedHandler")
@ArgsFilter(
        @TypeFilter({HttpServletRequest.class, HttpServletResponse.class})
)
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        CommonResult<Object> forbidden = CommonResult.forbidden(accessDeniedException.getMessage());
        ResponseUtil.returnJson(response, forbidden);
    }
}
```

此处打印日志将会忽略 response与request参数