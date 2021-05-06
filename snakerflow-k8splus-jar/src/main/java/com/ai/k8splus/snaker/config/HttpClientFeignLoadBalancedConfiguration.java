package com.ai.k8splus.snaker.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ApacheHttpClient.class)
@ConditionalOnProperty(value = "feign.httpclient.enabled", matchIfMissing = true)
class HttpClientFeignLoadBalancedConfiguration {

    @Autowired(required = false)
    private HttpClient httpClient;

    @Bean
    @ConditionalOnMissingBean(Client.class)
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
                              SpringClientFactory clientFactory) {
        ApacheHttpClient delegate;
        if (this.httpClient != null) {
            delegate = new ApacheHttpClient(this.httpClient);
        } else {
            delegate = new ApacheHttpClient();
        }
        return new LoadBalancerFeignClient(delegate, cachingFactory, clientFactory);
    }

    @Bean(destroyMethod = "close")
    public CloseableHttpClient httpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(400);
        connectionManager.setDefaultMaxPerRoute(100);

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(2000)//从连接池获取连接等待超时时间
                .setConnectTimeout(2000)//请求超时时间
                .setSocketTimeout(15000)//等待服务响应超时时间
                .build();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                //自定义重试策略，针对502和503重试一次
//                .setServiceUnavailableRetryStrategy(new CustomizedServiceUnavailableRetryStrategy())
                .evictExpiredConnections();
        return httpClientBuilder.build();
    }

}
