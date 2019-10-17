package com.obinna.libraryuser.controller;

import com.obinna.libraryuser.dao.AppRepository;
import com.obinna.libraryuser.dto.LoginRequestDto;
import com.obinna.libraryuser.dto.LoginSuccessDto;
import com.obinna.libraryuser.dto.UserDto;
import com.obinna.libraryuser.model.QUser;
import com.obinna.libraryuser.model.User;
import com.obinna.libraryuser.service.UserService;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
@Api(value = "users")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private AppRepository appRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    @ApiOperation(value = "Authenticates user and returns jwt token")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity<LoginSuccessDto> login(//
                                                 @ApiParam("Login User") @RequestBody LoginRequestDto requestDto) {

        return ResponseEntity.ok(userService.signIn(requestDto));
    }

    @GetMapping("/me")
    @ApiOperation(value = "Gets the current logged in user")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid user")})
    public ResponseEntity<User> getCurrentUser() {

        return ResponseEntity.ok(userService.currentUser());
    }


    @ApiOperation(value = "fetch users", response = QueryResults.class)
    @GetMapping()
    public ResponseEntity<?> getBucketLists(@ApiParam(value = "user name")
                                                     @RequestParam("username") Optional<String> username,
                                                     @ApiParam(value = "userId") @RequestParam("userId") Optional<String> userId,
                                                     @ApiParam(value = "page") @RequestParam("page") Optional<Integer> page,
                                                     @ApiParam(value = "limit") @RequestParam("limit") Optional<Integer> limit) {

        QUser user = QUser.user;
        JPAQuery<User> userJPAQuery = appRepository.startJPAQuery(user);
        username.ifPresent(searchUsername -> userJPAQuery.where(user.username.containsIgnoreCase(searchUsername)));
        userId.ifPresent(id -> userJPAQuery.where(user.id.eq(Long.valueOf(id))));
        userJPAQuery.limit(limit.orElse(10));
        userJPAQuery.offset(page.orElse(0));
        return ResponseEntity.ok(appRepository.fetchResults(userJPAQuery));
    }

    @PostMapping("/sign-up")
    @ApiOperation(value = "Creates user and returns jwt token")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<String> signUp(@ApiParam("Signup User") @RequestBody UserDto user) {
        return ResponseEntity.ok(userService.signUp(modelMapper.map(user, User.class)));
    }

    /*public static void main(String[] args) {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword("secret-key");
        if(passwordEncryptor.checkPassword("secret-key", encryptedPassword)) {
            System.out.println("Encrypted password:::: " + encryptedPassword);
        } else {
            System.out.println("Wrong password encryption!!!");
        }
    }*/
}
