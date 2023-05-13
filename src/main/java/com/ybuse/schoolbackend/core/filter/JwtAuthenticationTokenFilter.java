package com.ybuse.schoolbackend.core.filter;

import cn.hutool.core.exceptions.ValidateException;
import com.alibaba.druid.util.StringUtils;
import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import com.ybuse.schoolbackend.core.logger.annotation.PrintLog;
import com.ybuse.schoolbackend.utils.ResponseUtil;
import com.ybuse.schoolbackend.utils.TokenBlacklistUtil;
import com.ybuse.schoolbackend.utils.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;


@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PrintLog
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        // 验证请求头是否带token
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("[Token]-开始验证token");
        try {
            // 验证token正确性
            TokenUtil.validateToken(token);
            // 验证token是否过期
            TokenUtil.validateTokenDate(token);
        } catch (ValidateException e) {
            log.debug("[Token]-错误的token：{}", e.getMessage());
            // 返回错误信息
            ResponseUtil.returnJson(response, CommonResult.unauthorized(e.getMessage()));
            return;
        }

        Map<String, Object> map = TokenUtil.parseToken(token);
        log.debug("[Token]-token完整信息：{}", map);
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
            log.info("[Token]-用户：{}，已通过token验证并注入环境", userDetails.getUsername());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
