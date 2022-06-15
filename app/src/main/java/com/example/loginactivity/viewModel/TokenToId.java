package com.example.loginactivity.viewModel;

public class TokenToId {
    String id;
    String Token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public TokenToId(String id, String token) {
        this.id = id;
        Token = token;
    }
}
