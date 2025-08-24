package com.library.lms.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        // Proceed with the request
        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;

        // Log method, URL, query string, and response status
        String queryString = request.getQueryString();
        String requestURI = request.getRequestURI() + (queryString != null ? "?" + queryString : "");

        logger.info("REQUEST DATA : method={}, uri={}, status={}, duration={}ms",
                request.getMethod(),
                requestURI,
                response.getStatus(),
                duration);
    }
}