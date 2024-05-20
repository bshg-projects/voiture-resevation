package com.example.voitureresevation.zsecurity.ws.restapi;

import com.example.voitureresevation.zsecurity.entity.AppUser;
import com.example.voitureresevation.zsecurity.entity.Authority;
import com.example.voitureresevation.zsecurity.jwt.JwtUtils;
import com.example.voitureresevation.zsecurity.ws.dto.request.LoginRequest;
import com.example.voitureresevation.zsecurity.ws.dto.response.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        AppUser userDetails = (AppUser) authentication.getPrincipal();
        List<String> authorities = userDetails
                .getAuthorities()
                .stream().map(Authority::getAuthority)
                .toList();

        JwtResponse response = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), authorities);
        return ResponseEntity.ok(response);
    }
}
