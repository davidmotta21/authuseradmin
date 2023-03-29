package com.prueba.authuseradminds.payload.request;

import javax.validation.constraints.NotBlank;

public class AuthRequest {
    @NotBlank
    private String user;

    @NotBlank
    private String password;
    @NotBlank
    private String source;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}