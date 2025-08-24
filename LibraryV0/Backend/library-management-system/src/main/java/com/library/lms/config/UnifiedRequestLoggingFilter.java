package com.library.lms.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;

@Component
public class UnifiedRequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(UnifiedRequestLoggingFilter.class);
    private static final int MAX_PAYLOAD_LENGTH = 1000;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Wrap request and response for caching
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            // Build request info
            String queryString = wrappedRequest.getQueryString();
            String requestURI = wrappedRequest.getRequestURI() + (queryString != null ? "?" + queryString : "");

            StringBuilder headers = new StringBuilder();
            Enumeration<String> headerNames = wrappedRequest.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headers.append(headerName).append("=").append(wrappedRequest.getHeader(headerName)).append("; ");
            }

            String payload = getPayload(wrappedRequest.getContentAsByteArray());

            // Log everything
            logger.info("REQUEST DATA : method={}, uri={}, status={}, duration={}ms, headers=[{}], payload=[{}]",
                    wrappedRequest.getMethod(),
                    requestURI,
                    wrappedResponse.getStatus(),
                    duration,
                    headers,
                    payload
            );

            // Copy response back to client
            wrappedResponse.copyBodyToResponse();
        }
    }

    private String getPayload(byte[] buf) {
        if (buf == null || buf.length == 0) return "";
        int length = Math.min(buf.length, MAX_PAYLOAD_LENGTH);
        return new String(buf, 0, length, StandardCharsets.UTF_8);
    }
}