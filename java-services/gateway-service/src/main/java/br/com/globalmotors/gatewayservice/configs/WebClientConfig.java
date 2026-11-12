package br.com.globalmotors.gatewayservice.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced // faz "lb://auth-service" resolver via discovery, como o @FeignClient(name=...)
    WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
}