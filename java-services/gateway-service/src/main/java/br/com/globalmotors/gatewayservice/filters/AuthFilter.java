package br.com.globalmotors.gatewayservice.filters;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {
	
	private static final List<String> PROTECTED_ROUTES = List.of("/ws/");
	private final WebClient authClient;
	
    private static final ParameterizedTypeReference<Map<String, String>> PAYLOAD_TYPE =
            new ParameterizedTypeReference<>() {};
	
    public AuthFilter(WebClient.Builder builder) {
        this.authClient = builder.baseUrl("lb://auth-service").build();
    }
	
	@Override
	public int getOrder() {
		return -1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		var request = exchange.getRequest();
		String path = request.getURI().getPath();
		
		//so entra se nao tiver ws
		if(!PROTECTED_ROUTES.stream().anyMatch(path::startsWith))
			return chain.filter(exchange);
		
		String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            return unauthorized(exchange);

        //usa webclient para ser reativo e poder criar a chamada, visto que com o feign estava tendo dependencia circular pois o
        //spring n sabia qual bean iniciar primeiro, visto que o client precisava do filter e vice versa (feign n eh recomendado em gateway reativo)
		return authClient.post()
                .uri("/auth/validate-jwt")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .bodyToMono(PAYLOAD_TYPE)
                .switchIfEmpty(Mono.error(new IllegalStateException("Auth retornou vazio.")))
                .flatMap(payload -> {
                    var modifiedRequest = request.mutate()
                            .header("X-User-Id", payload.get("id"))
                            .header("X-User-Type", payload.get("type"))
                            .header("X-User-Email", payload.get("email"))
                            .build();
                    return chain.filter(exchange.mutate().request(modifiedRequest).build());
                })
                // só 401/403 do auth viram 401; outros erros (404, 415, 5xx) aparecem como erro de verdade
                .onErrorResume(WebClientResponseException.class, e ->
                e.getStatusCode().value() == 401 || e.getStatusCode().value() == 403
                        ? unauthorized(exchange)
                        : Mono.error(e));

	}
	
    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
	
}
