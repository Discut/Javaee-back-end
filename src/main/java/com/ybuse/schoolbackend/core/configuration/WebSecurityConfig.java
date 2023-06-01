package com.ybuse.schoolbackend.core.configuration;

import com.ybuse.schoolbackend.core.filter.JwtAuthenticationTokenFilter;
import com.ybuse.schoolbackend.core.handler.CustomAccessDeniedHandler;
import com.ybuse.schoolbackend.core.handler.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,securedEnabled=true,jsr250Enabled=true)
public class WebSecurityConfig {

    private UserDetailsService userDetailsService;

    private OncePerRequestFilter customExceptionFilter;


    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 添加Security的用户
     *
     * @return SecurityFilterChain
     */
    // @Bean
    public UserDetailsService myUserDetailsService() {
        if (!Objects.isNull(userDetailsService)) {
            return userDetailsService;
        }
        //内存管理对象，把用户存储到内存中
        // InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        /*JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(source);
        //创建用户user01,密码user01,角色user
        if (!jdbcUserDetailsManager.userExists("a")) {
            jdbcUserDetailsManager.createUser(User.withUsername("a").password(passwordEncoder().encode("a")).roles("user").build());
        }
        return jdbcUserDetailsManager;*/
        return null;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //.authorizeRequests()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/user/login").permitAll()
                .requestMatchers("/v1/api-docs/**", "/swagger-ui/**").permitAll()// 过滤swagger访问路径
                //.antMatchers("/api/v1/user/login").anonymous()
                .anyRequest().authenticated()
                .and().cors().configurationSource(corsConfiguration());
        // 添加JWT过滤器
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setUserDetailsService(myUserDetailsService());
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 添加自定义异常处理
        http.addFilterAfter(customExceptionFilter, ExceptionTranslationFilter.class);

        // 添加自定义handler
        http.exceptionHandling()
                // 认证handler
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                // 授权handler
                .accessDeniedHandler(new CustomAccessDeniedHandler());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /**
     * Cors 的配置信息 配置+路径<br>
     * 跨域配置
     *
     * @author Discut
     */
    private CorsConfigurationSource corsConfiguration() {
        // Cors配置类
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(false); // 是否返回时生成凭证
        corsConfiguration.setAllowedHeaders(List.of("*")); // 允许请求携带哪些请求头信息
        corsConfiguration.setAllowedMethods(List.of("*")); // 允许哪些类型的请求方法
        corsConfiguration.setAllowedOrigins(List.of("*")); // 允许哪些域可以进行方法
        corsConfiguration.setMaxAge(3600L); // 设置预检的最大的时长
        corsConfiguration.setExposedHeaders(Collections.emptyList()); // 设置返回暴露的响应头信息

        // 设置注册URL 配置类
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }


    @Autowired
    public void setCustomExceptionFilter(OncePerRequestFilter customExceptionFilter) {
        this.customExceptionFilter = customExceptionFilter;
    }
}
