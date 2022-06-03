package com.helpserviceprovider.sampleexceptionhandler.service;

import com.helpserviceprovider.sampleexceptionhandler.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Service
public class ShowExceptionService {

    public Mono<ServerResponse> customHome() {
        return Mono.error(() -> new RuntimeException("Error"))
                .onErrorResume(e -> Mono.error(new CustomException(HttpStatus.BAD_REQUEST, e.getMessage(), e)))
                .flatMap(o -> ServerResponse.ok().build());
    }
}
