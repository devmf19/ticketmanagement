package com.doublevpartners.ticketmanagement.controller;

import com.doublevpartners.ticketmanagement.dto.request.PersonInDto;
import com.doublevpartners.ticketmanagement.dto.response.PersonOutDto;
import com.doublevpartners.ticketmanagement.dto.response.ResponseDto;
import com.doublevpartners.ticketmanagement.service.PersonService;
import com.doublevpartners.ticketmanagement.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Registro y gestion de usuarios", description = "Gestion de usuarios (SOLO ADMIN)")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    @Operation(
            summary = "Todos los usuarios registrados"
    )
    public ResponseEntity<ResponseDto<List<PersonOutDto>>> getAllUsers(){
        return new ResponseEntity<>(
                ResponseDto.<List<PersonOutDto>>builder()
                        .data(personService.getAllUsers())
                        .message(Constants.SUCCESS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{personId}")
    @Operation(
            summary = "Buscar usuario por id de persona"
    )
    public ResponseEntity<ResponseDto<PersonOutDto>> getByPersonId(@PathVariable Long personId){
        return new ResponseEntity<>(
                ResponseDto.<PersonOutDto>builder()
                        .data(personService.getByPersonId(personId))
                        .message(Constants.SUCCESS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @Operation(
            summary = "Crear usuario con rol USER"
    )
    public ResponseEntity<ResponseDto<PersonOutDto>> create(@Validated @RequestBody PersonInDto person) {
        return new ResponseEntity<>(
                ResponseDto.<PersonOutDto>builder()
                        .data(personService.createUser(person))
                        .message(Constants.UPDATED_USER)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{personId}")
    @Operation(
            summary = "Actualizar usuario"
    )
    public ResponseEntity<ResponseDto<PersonOutDto>> updateUser(@PathVariable Long personId, @Validated @RequestBody PersonInDto person) {
        return new ResponseEntity<>(
                ResponseDto.<PersonOutDto>builder()
                        .data(personService.updateUser(personId, person))
                        .message(Constants.UPDATED_USER)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }
}
