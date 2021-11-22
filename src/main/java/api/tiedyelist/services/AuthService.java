package api.tiedyelist.services;

import api.tiedyelist.converters.ObjectToUrlEncodedConverter;
import api.tiedyelist.dtos.AuthResponseDTO;
import api.tiedyelist.dtos.Keycloak.KeycloakLoginRequestDTO;
import api.tiedyelist.dtos.Keycloak.KeycloakRefreshTokenRequestDTO;
import api.tiedyelist.dtos.LoginRequestDTO;
import api.tiedyelist.dtos.RefreshTokenRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String resource;

    @Value("${client-secret}")
    private String clientSecret;

    private static final String grantTypePassword = "password";
    private static final String grantTypeRefreshToken = "refresh_token";

    private final RestTemplate restTemplate;

    public AuthService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.restTemplate.getMessageConverters().add(new ObjectToUrlEncodedConverter());
    }

    public AuthResponseDTO login(LoginRequestDTO loginRequest) {
        final String tokenEndpoint = authServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        KeycloakLoginRequestDTO keycloakLoginRequest = new KeycloakLoginRequestDTO(loginRequest, resource, clientSecret, grantTypePassword);
        HttpEntity request = new HttpEntity(keycloakLoginRequest, headers);
        return restTemplate.postForObject(tokenEndpoint, request, AuthResponseDTO.class);
    }

    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequest) {
        final String tokenEndpoint = authServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        KeycloakRefreshTokenRequestDTO keycloakRefreshTokenRequest = new KeycloakRefreshTokenRequestDTO(refreshTokenRequest, resource, clientSecret, grantTypeRefreshToken);
        HttpEntity request = new HttpEntity(keycloakRefreshTokenRequest, headers);
        return restTemplate.postForObject(tokenEndpoint, request, AuthResponseDTO.class);
    }

}
