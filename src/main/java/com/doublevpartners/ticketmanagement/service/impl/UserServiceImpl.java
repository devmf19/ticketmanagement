package com.doublevpartners.ticketmanagement.service.impl;

import com.doublevpartners.ticketmanagement.dto.response.UserOutDto;
import com.doublevpartners.ticketmanagement.mapper.UserMapper;
import com.doublevpartners.ticketmanagement.repository.UserRepository;
import com.doublevpartners.ticketmanagement.service.UserService;
import com.doublevpartners.ticketmanagement.util.Constants;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserOutDto getUserById(Long userId) {
        return userMapper.toDto(
                userRepository.findById(userId).orElseThrow(
                        () -> new EntityNotFoundException(Constants.NOT_FOUND_USER.concat(userId.toString()))
                )
        );
    }

    @Override
    public UserOutDto getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<UserOutDto> getAllUsers() {
        return userMapper.toDtoList(
                userRepository.findAll()
        );
    }
}
