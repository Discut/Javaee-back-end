package com.ybuse.schoolbackend.core.filter;

import cn.hutool.core.exceptions.ValidateException;
import com.alibaba.druid.util.StringUtils;
import com.ybuse.schoolbackend.core.aop.annotation.LogMethod;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.utils.ResponseUtil;
import com.ybuse.schoolbackend.utils.TokenBlacklistUtil;
import com.ybuse.schoolbackend.utils.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;


public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @LogMethod
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        // 验证请求头是否带token
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            // 验证token正确性
            TokenUtil.validateToken(token);
            // 验证token是否过期
            TokenUtil.validateTokenDate(token);
        } catch (ValidateException e) {
            // 返回错误信息
            ResponseUtil.returnJson(response, CommonResult.unauthorized(e.getMessage()));
            return;
        }
        try {
            Map<String, Object> map = TokenUtil.parseToken(token);
            // 验证token
/*            if (map == null) {
                filterChain.doFilter(request, response);
                return;
            }*/
            // 检查token是否存在于黑名单
            if (TokenBlacklistUtil.isBlacklist(map.get("username").toString(), token)) {
                CommonResult.unauthorized("token已被注销")
                        .putIn(response)
                        .send();
                return;
            }
            String username = (String) map.get("username");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null && userDetails.isEnabled()) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //设置用户登录状态
                // log.info("authenticated user {}, setting security context",username);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Illegal token!");
        }
    }
}
