package com.fleetmanagement.api_rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class ApiException {
    private final String error;
}
