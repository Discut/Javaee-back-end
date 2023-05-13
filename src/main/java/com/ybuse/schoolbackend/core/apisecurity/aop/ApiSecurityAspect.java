package com.ybuse.schoolbackend.core.apisecurity.aop;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.ybuse.schoolbackend.core.apisecurity.ISubjectProvider;
import com.ybuse.schoolbackend.core.apisecurity.exception.ApiKeyException;
import com.ybuse.schoolbackend.core.apisecurity.properties.ApiKeyProperties;
import com.ybuse.schoolbackend.core.apisecurity.util.ApiKeyUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 消费api key
 */
@Aspect
@Component
public class ApiSecurityAspect {

    /**
     * apikey签发对象主体提供者
     */
    private ISubjectProvider subjectProvider;

    /**
     * 系统配置类
     */
    private ApiKeyProperties apiKeyProperties;

    @Pointcut("@annotation(com.ybuse.schoolbackend.core.apisecurity.annotation.EnableApiSecurity)")
    public void apiSecurity() {
    }

    @Around("apiSecurity()")
    public Object apiSecurity(ProceedingJoinPoint pjp) throws Throwable {
        Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
        logger.info("Around-开始api key验证");
        // 获取用户名
        String subject = subjectProvider.getSubject();
        if (subject == null)
            subject = ApiKeyUtil.Subject.ANONYMITY.name();
        // 转换对象为ServletRequestAttributes
        if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes servletRequestAttributes) {
            // 获取请求URL
            String url = servletRequestAttributes.getRequest().getRequestURI();
            // 获取api key
            String header = servletRequestAttributes.getRequest().getHeader(apiKeyProperties.getHeaderName());
            // 请求未携带api key
            if (header == null || header.isEmpty())
                throw new ApiKeyException("api key is empty");
            logger.debug("Around-获取的信息为：subject={},api key={}", subject, header);
            // 校验api key，不合法抛出异常
            ApiKeyUtil.validateApiKey(header, subject);
            // 解析api key
            JWT jwt = JWTUtil.parseToken(header);
            logger.debug("Around-解析后apikey信息：header{},payloads{}", jwt.getHeaders(), jwt.getPayloads());
            String path = jwt.getPayload("path").toString();
            // api key 信息与请求路径不符
            if (!url.contains(path))
                return new ApiKeyException("Incorrect api key!");
            // 分布式系统用分布式锁
            // 消费api key
            synchronized (this) {
                if (!ApiKeyUtil.isValid(header))
                    throw new ApiKeyException("api key is consumed");
                logger.debug("Around-消费apikey");
                ApiKeyUtil.consumeApikey(header);
            }
            logger.info("Around-apikey消费成功");
            return pjp.proceed();
        }
        logger.warn("Around-意外的失败");
        return pjp.proceed();
    }

    @Autowired
    public void setSubjectProvider(ISubjectProvider subjectProvider) {
        this.subjectProvider = subjectProvider;
    }

    @Autowired
    public void setApiKeyProperties(ApiKeyProperties apiKeyProperties) {
        this.apiKeyProperties = apiKeyProperties;
    }
}
