package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.model.AuthResponse;
import com.fleetmanagement.api_rest.model.AuthRequest;
import com.fleetmanagement.api_rest.model.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse authenticate (AuthRequest request);
}
