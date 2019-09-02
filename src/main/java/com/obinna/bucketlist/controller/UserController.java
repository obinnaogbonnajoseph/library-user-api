package com.obinna.bucketlist.controller;

import com.obinna.bucketlist.dto.UserDto;
import com.obinna.bucketlist.model.User;
import com.obinna.bucketlist.service.UserService;
import io.swagger.annotations.*;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Api(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    @ApiOperation(value = "Authenticates user and returns jwt token")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity<String> login(//
                                        @ApiParam("Username") @RequestParam String username, //
                                        @ApiParam("Password") @RequestParam String password) {
        return ResponseEntity.ok(userService.signIn(username, password));
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
