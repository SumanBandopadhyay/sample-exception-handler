package com.helpserviceprovider.sampleexceptionhandler.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private HttpStatus status;
    private String message;
    private String stackTrace;
    private LocalDateTime timestamp;
    private String path;
    private String requestId;
}
