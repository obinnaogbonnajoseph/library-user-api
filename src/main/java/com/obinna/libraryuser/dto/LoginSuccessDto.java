package com.obinna.libraryuser.dto;

import com.obinna.libraryuser.model.User;

public class LoginSuccessDto {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String token;
    private User user;
}
