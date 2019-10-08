package com.obinna.libraryuser.controller;

import com.obinna.libraryuser.dto.LoginRequestDto;
import com.obinna.libraryuser.dto.LoginSuccessDto;
import com.obinna.libraryuser.model.User;
import com.obinna.libraryuser.repository.UserRepository;
import com.obinna.libraryuser.service.UserService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private UserRepository userRepository;

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


    @ApiOperation(value = "fetch users", response = List.class)
    @GetMapping()
    public ResponseEntity<List<User>> getBucketLists(@ApiParam(value = "user name")
                                                     @RequestParam("username") Optional<String> username,
                                                     @ApiParam(value = "userId") @RequestParam("userId") Optional<String> userId,
                                                     @ApiParam(value = "page") @RequestParam("page") Optional<Integer> page,
                                                     @ApiParam(value = "limit") @RequestParam("limit") Optional<Integer> limit) {

        List<User> users;
        int limitFetch = limit.map(integer -> (integer > 100 ? 100 : integer)).orElse(20); // place a minimum restriction of 20, max restriction of 100
        Pageable pagination = PageRequest.of(page.orElse(0), limitFetch,
                Sort.Direction.ASC, "username");

        String searchName = username.orElse("");
        String searchUserId = userId.orElse("");
        if(StringUtils.isNoneBlank(searchName, searchUserId)) {
            users = userRepository.findAllByUsernameContainsAndId(searchName, Long.valueOf(searchUserId), pagination);
        }
        else if(StringUtils.isBlank(searchName) && StringUtils.isNotBlank(searchUserId)) {
            User user = userRepository.findById(Long.valueOf(searchUserId)).orElse(null);
            users = new ArrayList<>(Arrays.asList(user));
        }
        else if(StringUtils.isBlank(searchUserId) && StringUtils.isNotBlank(searchName)) {
            users = userRepository.findAllByUsernameContains(searchName, pagination);
        }
        else users = Collections.EMPTY_LIST;
        return ResponseEntity.ok(users);
    }

    /*@PostMapping("/sign-up")
    @ApiOperation(value = "Creates user and returns jwt token")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<String> signUp(@ApiParam("Signup User") @RequestBody UserDto user) {
        return ResponseEntity.ok(userService.signUp(modelMapper.map(user, User.class)));
    }*/

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
