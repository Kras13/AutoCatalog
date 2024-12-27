package org.nkp.autocatalog.models.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("auth_token")
    private String token;

    public AuthenticationResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}
