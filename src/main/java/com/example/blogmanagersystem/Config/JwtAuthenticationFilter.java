package com.example.blogmanagersystem.Config;

import com.example.blogmanagersystem.Utils.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 从请求中提取JWT令牌
            String token = extractToken(request);

            if (token != null && jwtTokenUtil.validateToken(token)) {
                // 解析令牌中的用户名
                String username = jwtTokenUtil.getUserNameFromToken(token);

                // 根据用户名创建认证对象
                if (username != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, null);
                    // 将认证对象设置到安全上下文
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (JwtException e) {
            // JWT验证失败的处理
            logger.error("JWT validation error: {}", e.getMessage());
            handleAuthenticationException(response, e);
            return;
        }

        // 继续调用过滤器链
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        // 从请求头中提取Authorization字段的值
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken)) {
            return bearerToken;
        }
        return null;
    }

    private void handleAuthenticationException(HttpServletResponse response, Exception e) throws IOException {
        // 清除安全上下文
        SecurityContextHolder.clearContext();
        logger.error("Authentication error: {}", e.getMessage());
        // 返回401 Unauthorized错误
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + e.getMessage());
    }
}



