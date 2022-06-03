package com.helpserviceprovider.sampleexceptionhandler.route;

import com.helpserviceprovider.sampleexceptionhandler.handler.ShowExceptionRouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ShowExceptionRoute {

    @Bean
    RouterFunction<ServerResponse> customRoutes(ShowExceptionRouteHandler handler) {
        return route(GET("/custom"), handler::customHome);
    }
}
