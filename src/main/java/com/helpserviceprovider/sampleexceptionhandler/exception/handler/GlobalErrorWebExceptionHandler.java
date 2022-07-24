package com.helpserviceprovider.sampleexceptionhandler.exception.handler;

import com.helpserviceprovider.sampleexceptionhandler.GlobalErrorAttributes;
import com.helpserviceprovider.sampleexceptionhandler.data.ErrorResponse;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.STACK_TRACE;
import static org.springframework.boot.web.error.ErrorAttributeOptions.defaults;
import static org.springframework.boot.web.error.ErrorAttributeOptions.of;

@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalErrorWebExceptionHandler(GlobalErrorAttributes g, ApplicationContext applicationContext,
                                          ServerCodecConfigurer serverCodecConfigurer) {
        super(g, new WebProperties.Resources(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {
        String query = request.uri().getQuery();
        ErrorAttributeOptions errorAttributeOptions = isTraceEnabled(query) ? of(STACK_TRACE) : defaults();

        final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, errorAttributeOptions);
        int status = (int) errorPropertiesMap.getOrDefault("status", HttpStatus.INTERNAL_SERVER_ERROR);
        String errorMessage = (String) errorPropertiesMap.getOrDefault("message", "Internal Error");
        String requestId = (String) errorPropertiesMap.getOrDefault("requestId", "");
        String trace = (String) Optional.ofNullable(errorPropertiesMap.get("trace")).orElse("");

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(errorMessage)
                .status(HttpStatus.resolve(status))
                .path(request.path())
                .requestId(requestId)
                .timestamp(LocalDateTime.now())
                .stackTrace(trace)
                .build();

        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorResponse));
    }

    private boolean isTraceEnabled(String query) {
        return StringUtils.hasLength(query) && query.contains("trace=true");
    }

}
