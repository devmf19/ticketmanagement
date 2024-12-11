package com.doublevpartners.ticketmanagement.security.service.impl;

import com.doublevpartners.ticketmanagement.repository.UserRepository;
import com.doublevpartners.ticketmanagement.security.model.PrincipalUser;
import com.doublevpartners.ticketmanagement.security.util.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(PrincipalUser::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException(SecurityConstants.NOT_FOUND_USERNAME)
                );
    }
}