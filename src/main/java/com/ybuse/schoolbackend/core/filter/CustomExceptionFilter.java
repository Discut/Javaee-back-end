package com.ybuse.schoolbackend.core.filter;

import com.ybuse.schoolbackend.core.domain.vo.CommonResult;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class CustomExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.info("[ExceptionFilter]-全局异常捕获获取到异常：{}", e.getMessage());
            if (e.getMessage().contains("com.ybuse")) {
                log.error("[ExceptionFilter]-自定义异常抛出：{}", e.getMessage());
                CommonResult.forbidden(e.getMessage()).putIn(response).send();
            } else
                throw e;
        }
    }
}
