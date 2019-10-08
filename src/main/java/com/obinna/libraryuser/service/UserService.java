package com.obinna.libraryuser.service;

import com.obinna.libraryuser.dto.LoginRequestDto;
import com.obinna.libraryuser.dto.LoginSuccessDto;
import com.obinna.libraryuser.model.User;


public interface UserService {

    LoginSuccessDto signIn(LoginRequestDto requestDto);

    String signUp(User user);

    User currentUser();
}
