package com.example.demo.controller;

import com.example.demo.dto.RefreshTokenRequest;
import com.example.demo.dto.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        try {
            TokenResponse tokenResponse = AuthService.refreshAccessToken(request.getRefreshToken());
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token invalid or expired");
        }
    }
}
