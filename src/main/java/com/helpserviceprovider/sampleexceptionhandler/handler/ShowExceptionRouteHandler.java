package com.helpserviceprovider.sampleexceptionhandler.handler;

import com.helpserviceprovider.sampleexceptionhandler.service.ShowExceptionService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ShowExceptionRouteHandler {

    private final ShowExceptionService showExceptionService;

    public ShowExceptionRouteHandler(ShowExceptionService showExceptionService) {
        this.showExceptionService = showExceptionService;
    }

    public Mono<ServerResponse> customHome(ServerRequest request) {
        return showExceptionService.customHome();
    }
}
