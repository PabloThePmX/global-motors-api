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
	                        .uri("http://localhost:8081/cars"))
	                .route("user-service", r -> r
	                        .path("/users/**")
	                        //TODO pablo colocar a URL do users.
	                        .uri("http://localhost:8082")) // URL do microserviço de usuários
	                .build();
	    }
}
