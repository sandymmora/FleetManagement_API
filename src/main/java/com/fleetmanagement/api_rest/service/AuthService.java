package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.model.AuthResponse;
import com.fleetmanagement.api_rest.model.AuthRequest;

public interface AuthService {

    AuthResponse authenticate (AuthRequest request);
}
