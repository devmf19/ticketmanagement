package com.doublevpartners.ticketmanagement.security.service;

import com.doublevpartners.ticketmanagement.model.User;

import java.util.Optional;

public interface CustomUserDetailsService {
    Optional<User> readByUsername(String username);
}
