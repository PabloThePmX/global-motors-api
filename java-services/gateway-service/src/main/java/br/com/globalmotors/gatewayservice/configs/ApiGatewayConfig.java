package br.com.globalmotors.gatewayservice.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
	
	  @Bean
	  RouteLocator getRoutes(RouteLocatorBuilder builder) {
	        return builder.routes()
	                .route("cars-service", r -> r
	                        .path("/cars/**")
	                        .uri("lb://cars-service"))
	                .route("cars-service", r -> r
	                        .path("/ws/cars/**")
	                        .uri("lb://cars-service"))
	                .route("currencies-service", r -> r
	                        .path("/currencies/**")
	                        .uri("lb://currencies-service"))
	                .route("addresses-service", r -> r
	                        .path("/ws/addresses/**")
	                        .uri("lb://addresses-service"))
	                .route("auth-service", r -> r
	                        .path("/auth/**")
	                        .uri("lb://auth-service"))
	                .route("orders-service", r -> r
	                        .path("/ws/orders/**")
	                        .uri("lb://orders-service"))
	                .route("users-service", r -> r
	                        .path("/ws/users/**")
	                        .uri("lb://users-service"))
	                .build();
	    }
}
