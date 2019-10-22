package com.obinna.libraryuser.serviceImpl;

import com.obinna.libraryuser.dto.LoginRequestDto;
import com.obinna.libraryuser.dto.LoginSuccessDto;
import com.obinna.libraryuser.model.User;
import com.obinna.libraryuser.repository.UserRepository;
import com.obinna.libraryuser.security.JwtTokenProvider;
import com.obinna.libraryuser.service.UserService;
import com.obinna.libraryuser.utils.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private HttpServletRequest req;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public LoginSuccessDto signIn(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(username, userRepository.findByUsername(username)
                    .orElseThrow(() -> new CustomException("User with username: '" + username + "' does not exist", HttpStatus.NOT_FOUND))
                    .getRoles());
            logger.info("******************* Token created::: {}*************", token);
            User user = currentUser(token);
            LoginSuccessDto successDto = new LoginSuccessDto();
            successDto.setToken(token);
            successDto.setUser(user);
            return successDto;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void signUp(User user) {
        if (!userRepository.findByUsername(user.getUsername()).isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public User currentUser(String bearerToken) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(bearerToken))
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public User currentUser() {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)))
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
    }

}
