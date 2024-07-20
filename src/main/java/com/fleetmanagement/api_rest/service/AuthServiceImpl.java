package com.fleetmanagement.api_rest.service;


import com.fleetmanagement.api_rest.config.JwtService;
import com.fleetmanagement.api_rest.exception.BadRequestException;
import com.fleetmanagement.api_rest.model.*;
import com.fleetmanagement.api_rest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse authenticate(AuthRequest request) {
        if (request.getPassword() == null || request.getEmail() == null ){
            throw new BadRequestException("Email and password are required");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
