package com.ai.k8splus.snaker.config;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(FeignContext.class)
public class FeignConfig {

    @Bean
    public Decoder feignDecoder() {
        return new FeignResultDecoder();
    }

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
