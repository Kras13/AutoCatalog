package org.nkp.autocatalog.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("auth_token")
    private String token;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}
