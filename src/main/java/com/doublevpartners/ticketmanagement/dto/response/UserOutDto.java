package com.doublevpartners.ticketmanagement.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserOutDto {
    private Long id;
    private String username;
    private String role;
    private Long personId;
}
