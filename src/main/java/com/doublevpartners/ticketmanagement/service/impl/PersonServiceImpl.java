package com.doublevpartners.ticketmanagement.service.impl;

import com.doublevpartners.ticketmanagement.dto.request.PersonInDto;
import com.doublevpartners.ticketmanagement.dto.response.PersonOutDto;
import com.doublevpartners.ticketmanagement.dto.response.UserOutDto;
import com.doublevpartners.ticketmanagement.mapper.PersonMapper;
import com.doublevpartners.ticketmanagement.mapper.UserMapper;
import com.doublevpartners.ticketmanagement.model.Person;
import com.doublevpartners.ticketmanagement.model.User;
import com.doublevpartners.ticketmanagement.model.enums.RoleEnum;
import com.doublevpartners.ticketmanagement.repository.PersonRepository;
import com.doublevpartners.ticketmanagement.repository.UserRepository;
import com.doublevpartners.ticketmanagement.service.PersonService;
import com.doublevpartners.ticketmanagement.service.RoleService;
import com.doublevpartners.ticketmanagement.util.Constants;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PersonMapper personMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(UserRepository userRepository,
                             PersonRepository personRepository,
                             RoleService roleService,
                             UserMapper userMapper,
                             PersonMapper personMapper,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.personMapper = personMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<PersonOutDto> getAllUsers() {
        return personMapper.toDtoList(
                personRepository.findAll()
        )
                .stream()
                .peek(personOutDto -> {
                    User user = userRepository.findByPersonId(personOutDto.getId());
                    personOutDto.setRole(user.getRole().getName().name());
                    personOutDto.setUsername(user.getUsername());
                })
                .collect(Collectors.toList());
    }

    @Override
    public PersonOutDto getByPersonId(Long personId) {
        PersonOutDto personOutDto = personMapper.toDto(
                personRepository.findById(personId).orElseThrow(
                        () -> new EntityNotFoundException(Constants.NOT_FOUND_PERSON.concat(personId.toString()))
                )
        );

        User user = userRepository.findByPersonId(personOutDto.getId());
        personOutDto.setRole(user.getRole().getName().name());
        personOutDto.setUsername(user.getUsername());

        return personOutDto;
    }

    @Override
    @Transactional
    public PersonOutDto createUser(PersonInDto person) {
        Person savedPerson = personRepository.save(
                personMapper.toEntity(person)
        );

        User user = new User();
        user.setUsername(savedPerson.getFirstname().substring(0, 1)
                        .concat(savedPerson.getFirstLastname().substring(0, 1))
                        .concat(savedPerson.getDocumentNumber())
        );
        user.setPassword(passwordEncoder.encode(person.getDocumentNumber()));
        user.setPerson(savedPerson);
        user.setRole(roleService.readByName(RoleEnum.USER));

        User savedUser = userRepository.save(user);

        PersonOutDto responsePerson = personMapper.toDto(savedPerson);

        responsePerson.setUsername(savedUser.getUsername());
        responsePerson.setRole(savedUser.getRole().getName().name());

        return responsePerson;
    }

    @Override
    public PersonOutDto updateUser(Long personId, PersonInDto person) {
        Person oldPerson = personRepository.findById(personId).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_PERSON.concat(personId.toString()))
        );

        Person updatedPerson = personMapper.toEntity(person);
        updatedPerson.setId(oldPerson.getId());

        PersonOutDto personOutDto = personMapper.toDto(
                personRepository.save(updatedPerson)
        );

        User user = userRepository.findByPersonId(personOutDto.getId());
        user.setRole(roleService.readByName(RoleEnum.fromString(person.getRole())));

        User savedUser = userRepository.save(user);

        personOutDto.setRole(savedUser.getRole().getName().name());
        personOutDto.setUsername(savedUser.getUsername());

        return personOutDto;
    }

    @Override
    public UserOutDto disableUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_USER.concat(username))
        );

        user.setRole(roleService.readByName(RoleEnum.DISABLED));

        return userMapper.toDto(
                userRepository.save(user)
        );
    }
}
