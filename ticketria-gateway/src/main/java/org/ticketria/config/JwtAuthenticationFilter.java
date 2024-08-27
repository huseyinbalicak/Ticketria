package org.ticketria.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final TokenService tokenService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        FeignAuthUserResponse authUserResponse = tokenService.verifyToken(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));


        log.info("JWT: " + authUserResponse);
        String path = exchange.getRequest().getPath().toString();
        log.info("Path: " + path);

        if (authUserResponse != null) {


            CurrentUser currentUser = new CurrentUser(authUserResponse);

            log.info(currentUser.getAuthorities().toString());


            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    authUserResponse.getUsername(), null, currentUser.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));

        }

        return chain.filter(exchange);
    }

}
