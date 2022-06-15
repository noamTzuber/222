package com.example.loginactivity.API;

public class TokenToId {

        String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public TokenToId(String id, String token) {
            this.id = id;
            this.token = token;
        }

        String token;
    }


