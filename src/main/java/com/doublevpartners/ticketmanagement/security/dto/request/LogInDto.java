package com.doublevpartners.ticketmanagement.security.dto.request;

import com.doublevpartners.ticketmanagement.security.util.SecurityConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogInDto {
    @NotBlank(message = SecurityConstants.REQUIRED_USERNAME)
    private String username;

    @NotBlank(message = SecurityConstants.REQUIRED_PASSWORD)
    private String password;
}
