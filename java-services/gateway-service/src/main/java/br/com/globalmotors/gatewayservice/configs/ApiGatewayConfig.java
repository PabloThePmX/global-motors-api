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
				//TODO fazer rotas
				.build();
	}
}
