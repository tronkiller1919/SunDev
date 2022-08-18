package com.returnorder.managment.gatewayservice.apigatewayservice.filter;

import com.returnorder.managment.gatewayservice.apigatewayservice.utility.JwtUtility;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@Component
public class GateFilter extends AbstractGatewayFilterFactory<GateFilter.Config> {

    @Autowired
    JwtUtility jwtUtility;

    public GateFilter() {
        super(Config.class);
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpResponse serverHttpResponse = exchange.getResponse();

            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                serverHttpResponse.setStatusCode(UNAUTHORIZED);
                return serverHttpResponse.setComplete();
            }

            String jwtToken = exchange.getRequest().getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);

            try {
                if (jwtUtility.validateToken(jwtToken)) {
                    return chain.filter(exchange);
                } else {
                    serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return serverHttpResponse.setComplete();
                }
            }catch(RuntimeException e){
                log.debug(e.getMessage());
                serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
                return serverHttpResponse.setComplete();
            }
        };

    }

    public static class Config {
    }


}
