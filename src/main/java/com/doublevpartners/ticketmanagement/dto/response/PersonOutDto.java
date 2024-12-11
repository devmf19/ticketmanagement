package com.doublevpartners.ticketmanagement.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PersonOutDto {
    private Long id;

    private String documentNumber;

    private String firstname;

    private String secondName;

    private String firstLastname;

    private String secondLastname;

    private LocalDate birthdate;

    private String phone;

    private String email;

    private String address;

    private String username;

    private String role;
}
