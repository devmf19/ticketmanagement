package com.doublevpartners.ticketmanagement.service;

import com.doublevpartners.ticketmanagement.dto.request.PersonInDto;
import com.doublevpartners.ticketmanagement.dto.response.PersonOutDto;
import com.doublevpartners.ticketmanagement.dto.response.UserOutDto;

import java.util.List;

public interface PersonService {
    List<PersonOutDto> getAllUsers();
    PersonOutDto getByPersonId(Long personId);
    PersonOutDto createUser(PersonInDto person);
    PersonOutDto updateUser(Long personId, PersonInDto person);
    UserOutDto disableUser(String username);

}
