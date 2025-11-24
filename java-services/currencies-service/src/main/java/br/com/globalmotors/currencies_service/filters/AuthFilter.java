/*
package br.com.globalmotors.currencies_service.filters;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import br.com.globalmotors.currencies_service.components.JwtUtil;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {
		
	
	private static final List<String> PROTECTED_ROUTES = List.of("/ws/");
	
	@Override
	public int getOrder() {
		return -1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		var request = exchange.getRequest();
		String path = request.getURI().getPath();
		
		
		//Validar existencia e validar o token
		String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);
			var payload = JwtUtil.validateToken(jwt);
			if (payload != null) {
				var modifiedRequest = request.mutate()
						//TODO pablo vou deixar esse contigo ja que aqui tem informações do usuario e vc ta com a parte de usuario. Ta na aula 17 a partir do tempo 19:06
						.header("X-User-Id", String.valueOf(payload.get("id", Long.class)))
				
						.build();
				return chain.filter(exchange);
			}
		}
		
		if(!PROTECTED_ROUTES.stream().anyMatch(path::startsWith))
			return chain.filter(exchange);
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		return exchange.getResponse().setComplete();
	}

}
*/