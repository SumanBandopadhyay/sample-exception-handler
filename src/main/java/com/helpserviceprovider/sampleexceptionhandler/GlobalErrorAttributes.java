package com.helpserviceprovider.sampleexceptionhandler;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;
import java.util.Optional;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);
        int status = (int) Optional.ofNullable(map.get("status")).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("status", status);
        map.put("message", "please provide a name");
        return map;
    }

}
