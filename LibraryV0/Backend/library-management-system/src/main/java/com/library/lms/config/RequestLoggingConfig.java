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
import java.util.Enumeration;

@Component
public class RequestLoggingConfig extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingConfig.class);
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

            // Build request URI
            String queryString = wrappedRequest.getQueryString();
            String requestURI = wrappedRequest.getRequestURI() + (queryString != null ? "?" + queryString : "");

            // Build headers string
            StringBuilder headers = new StringBuilder();
            Enumeration<String> headerNames = wrappedRequest.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    headers.append(name).append("=").append(wrappedRequest.getHeader(name)).append("; ");
                }
            }

            // Get request payload
            String payload = getPayload(wrappedRequest.getContentAsByteArray());

            // Log unified request + response info
            logger.info("REQUEST DATA : method={}, uri={}, status={}, duration={}ms, headers=[{}], payload=[{}]",
                    wrappedRequest.getMethod(),
                    requestURI,
                    wrappedResponse.getStatus(),
                    duration,
                    headers.toString(),
                    payload
            );

            // Copy response body back to client
            wrappedResponse.copyBodyToResponse();
        }
    }

    private String getPayload(byte[] buf) {
        if (buf == null || buf.length == 0) return "";
        int length = Math.min(buf.length, MAX_PAYLOAD_LENGTH);
        return new String(buf, 0, length, StandardCharsets.UTF_8);
    }
}
