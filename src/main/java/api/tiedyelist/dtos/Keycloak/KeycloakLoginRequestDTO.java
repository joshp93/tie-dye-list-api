package api.tiedyelist.dtos.Keycloak;

import api.tiedyelist.dtos.LoginRequestDTO;

public class KeycloakLoginRequestDTO {
    private String client_id;
    private String grant_type;
    private String username;
    private String password;
    private String client_secret;

    public KeycloakLoginRequestDTO(LoginRequestDTO loginRequest, String resource, String clientSecret, String grantType) {
        this.client_id = resource;
        this.username = loginRequest.getUsername();
        this.password = loginRequest.getPassword();
        this.grant_type = grantType;
        this.client_secret = clientSecret;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }
}
