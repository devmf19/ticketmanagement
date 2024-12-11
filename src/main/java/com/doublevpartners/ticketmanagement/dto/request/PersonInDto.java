package com.doublevpartners.ticketmanagement.dto.request;


import com.doublevpartners.ticketmanagement.util.Constants;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PersonInDto {
    @NotBlank(message = Constants.REQUIRED_NAME)
    private String documentNumber;

    @NotBlank(message = Constants.REQUIRED_NAME)
    private String firstname;

    private String secondName;

    @NotBlank(message = Constants.REQUIRED_NAME)
    private String firstLastname;

    private String secondLastname;

    @NotBlank(message = Constants.REQUIRED_NAME)
    private LocalDate birthdate;

    @NotBlank(message = Constants.REQUIRED_NAME)
    private String phone;

    private String email;

    private String address;

    String role;
}
