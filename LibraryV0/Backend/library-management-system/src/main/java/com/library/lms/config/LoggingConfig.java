package com.library.lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);   // log client IP
        filter.setIncludeQueryString(true);  // log query params
        filter.setIncludePayload(true);      // log request body
        filter.setMaxPayloadLength(10000);   // limit request body size
        filter.setIncludeHeaders(false);     // set to true if you want headers too
        return filter;
    }
}
