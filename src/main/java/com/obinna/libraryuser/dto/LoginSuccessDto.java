package com.obinna.libraryuser.dto;

import com.obinna.libraryuser.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginSuccessDto {

    @NotBlank
    private String token;

    @NotNull
    private User user;

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
}
