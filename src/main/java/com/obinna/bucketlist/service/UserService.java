package com.obinna.bucketlist.service;

import com.obinna.bucketlist.dto.LoginRequestDto;
import com.obinna.bucketlist.model.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    String signIn(LoginRequestDto requestDto);

    String signUp(User user);

    User currentUser(HttpServletRequest req);
}
