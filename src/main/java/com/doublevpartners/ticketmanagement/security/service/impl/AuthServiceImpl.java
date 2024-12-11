package com.doublevpartners.ticketmanagement.security.service.impl;

import com.doublevpartners.ticketmanagement.dto.request.PersonInDto;
import com.doublevpartners.ticketmanagement.dto.response.UserOutDto;
import com.doublevpartners.ticketmanagement.mapper.PersonMapper;
import com.doublevpartners.ticketmanagement.mapper.UserMapper;
import com.doublevpartners.ticketmanagement.model.Person;
import com.doublevpartners.ticketmanagement.model.User;
import com.doublevpartners.ticketmanagement.model.enums.RoleEnum;
import com.doublevpartners.ticketmanagement.repository.PersonRepository;
import com.doublevpartners.ticketmanagement.repository.UserRepository;
import com.doublevpartners.ticketmanagement.security.dto.request.LogInDto;
import com.doublevpartners.ticketmanagement.security.jwt.JwtGenerator;
import com.doublevpartners.ticketmanagement.security.service.AuthService;
import com.doublevpartners.ticketmanagement.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    public String authenticate(LogInDto logInDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                logInDto.getUsername(),
                logInDto.getPassword()
        );

        Authentication authenticationResult = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
        return jwtGenerator.generateToken(authenticationResult);
    }
}
