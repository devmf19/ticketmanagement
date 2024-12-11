package com.doublevpartners.ticketmanagement.security.service;

import com.doublevpartners.ticketmanagement.security.dto.request.LogInDto;

public interface AuthService {
    String authenticate(LogInDto logIntDto);
}
