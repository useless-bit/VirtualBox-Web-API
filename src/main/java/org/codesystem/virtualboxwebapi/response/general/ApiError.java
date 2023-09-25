package org.codesystem.virtualboxwebapi.response.general;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ApiError implements ApiResponse {

    private Instant timestamp;
    private String message;
    private String detailedInfo;

    private ApiError() {
        timestamp = Instant.now();
    }

    public ApiError(String message, String detailedInfo) {
        this();
        this.message = message;
        this.detailedInfo = detailedInfo;
    }
}