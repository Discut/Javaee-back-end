package com.ybuse.schoolbackend.utils;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;

import java.util.Date;
import java.util.Map;

public class TokenUtil {
    public final static String TOKEN_HEADER = "Bearer ";

    /**
     * 生成token的密钥
     */
    private final static String SECRET = "xTMFbRLzDFPNyC7gW6Yo";

    /**
     * token的过期间隔时间
     */
    private final static long INTERVAL = 1000 * 60 * 60 * 24;

    /**
     * 算法签名器<br>
     * 包含加密算法 {@link JWTSignerUtil#hs256(byte[])}<br>
     * 文档 <a href="https://hutool.cn/docs/#/jwt/JWT%E7%AD%BE%E5%90%8D%E5%B7%A5%E5%85%B7-JWTSignerUtil">web</a>
     */
    private final static JWTSigner SIGNER = JWTSignerUtil.hs256(SECRET.getBytes());

    /**
     * 创建JWT
     *
     * @param claims 创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
     * @return
     */
    public static String getAccessToken(@NonNull Map<String, Object> claims) {
        // 默认使用HS265(HmacSHA256)算法加密
        JWT jwt = JWT.create();
        // 设置当前时间为为签发时间
        jwt.setIssuedAt(new Date());
        // 设置token的签发者
        jwt.setIssuer("systemAdmin");
        // 设置token的过期时间
        jwt.setExpiresAt(new Date(System.currentTimeMillis() + INTERVAL));
        // 设置payload
        claims.forEach(jwt::setPayload);
        // 加密
        jwt.setSigner(SIGNER);
        // 生成token
        return jwt.sign();
    }

    /**
     * 创建JWT
     *
     * @param tokenGetter 实现该接口的主体可自动获取token
     * @return
     */
    public static String getAccessToken(ITokenGetter tokenGetter) {
        return getAccessToken(tokenGetter.getTokenContains());
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Map<String, Object> parseToken(String token) {
        token = spliceToken(token);
        JWT jwt = JWTUtil.parseToken(token);
        return jwt.getPayload().getClaimsJson().getRaw();
    }

    /**
     * 根据传入的token过期时间判断token是否已过期
     *
     * @param expiresIn
     * @return true-已过期，false-没有过期
     */
    public static boolean isExpiresIn(long expiresIn) {
        long now = System.currentTimeMillis();
        return now > expiresIn;
    }

    /**
     * 验证token是否正确
     *
     * @param token
     */
    public static void validateToken(String token) throws ValidateException {
        token = spliceToken(token);
        if (!JWTUtil.verify(token, SIGNER)) {
            throw new ValidateException("token不正确");
        }
        JWTValidator.of(token).validateAlgorithm(SIGNER);
    }

    /**
     * 验证token是否正确
     *
     * @param request 请求
     */
    public static void validateToken(HttpServletRequest request) throws ValidateException {
        String token = getTokenFor(request);
        validateToken(token);
    }

    /**
     * 验证token是否过期
     *
     * @param token JWT生成的token
     */
    public static void validateTokenDate(String token) throws ValidateException {
        token = spliceToken(token);
        JWTValidator.of(token).validateDate(new Date());
    }

    /**
     * 验证token是否过期
     *
     * @param request 请求
     * @return
     */
    public static void validateTokenDate(HttpServletRequest request) throws ValidateException {
        String token = getTokenFor(request);
        validateTokenDate(token);
    }

    /**
     * 获取token
     *
     * @param request 请求
     * @return token
     */
    public static String getTokenFor(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        return spliceToken(token);
    }


    /**
     * 合并请求头的token， 加入 Bearer
     *
     * @param token JWT生成的token
     * @return 合并后的token
     * @Deprecated
     */
    @Deprecated
    public static String mergeToken(String token) {
        return TOKEN_HEADER + token;
    }

    /**
     * 分割请求头的token，将Bearer 去除
     *
     * @param token 请求头token
     * @return 分割后的token
     */
    public static String spliceToken(String token) {
        if (token.contains(TOKEN_HEADER))
            return token.substring(TOKEN_HEADER.length());
        return token;
    }

    /**
     * 快速获取用于生成token的内容
     *
     * @param tokenGetter 实现该接口的主体可自动获取token
     * @return token内容
     */
    public static Map<String, Object> getTokenContains(ITokenGetter tokenGetter) {
        return tokenGetter.getTokenContains();
    }

    /**
     * 获取该token的过期间隔时间
     *
     * @param token token
     * @return 过期间隔时间 毫秒为单位
     */
    public static long getExpirationInterval(String token) {
        token = spliceToken(token);
        // 获取的时间戳以秒为单位
        return Long.parseLong(parseToken(token).get("exp").toString()) * 1000 - System.currentTimeMillis();
    }

}
