package com.example.demo.service;

import com.example.demo.dto.TokenResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthService {
    public static TokenResponse refreshAccessToken(String refreshToken) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", "my-spring-client");
        params.add("client_secret", "your-client-secret");
        params.add("refresh_token", refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "http://localhost:8088/realms/my-app-realm/protocol/openid-connect/token",
                request,
                Map.class);

        Map<String, Object> tokenResponse = response.getBody();
        String newAccessToken = (String) tokenResponse.get("access_token");
        String newRefreshToken = (String) tokenResponse.get("refresh_token");

        return new TokenResponse(newAccessToken, newRefreshToken);
    }
}
