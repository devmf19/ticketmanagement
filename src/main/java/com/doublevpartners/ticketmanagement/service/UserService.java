package com.doublevpartners.ticketmanagement.service;

import com.doublevpartners.ticketmanagement.dto.response.UserOutDto;

import java.util.List;

public interface UserService {
    UserOutDto getUserById(Long userId);
    UserOutDto getUserByUsername(String username);
    List<UserOutDto> getAllUsers();
}
