package com.lungesoft.token.example.model;

public class User {

    private Long id;
    private String login;
    private String sha256Password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSha256Password() {
        return sha256Password;
    }

    public void setSha256Password(String sha256Password) {
        this.sha256Password = sha256Password;
    }
}
