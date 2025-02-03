package com.doublevpartners.ticketmanagement.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.doublevpartners.ticketmanagement.dto.request.PersonInDto;
import com.doublevpartners.ticketmanagement.dto.response.PersonOutDto;
import com.doublevpartners.ticketmanagement.service.PersonService;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(personController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Registra el módulo para Java 8 date/time
    }

    @Test
    public void testGetAllUsers() throws Exception {
        // Arrange
        PersonOutDto person1 = new PersonOutDto();
        person1.setId(1L);
        person1.setFirstname("John");

        PersonOutDto person2 = new PersonOutDto();
        person2.setId(2L);
        person2.setFirstname("Jane");

        List<PersonOutDto> personList = Arrays.asList(person1, person2);

        when(personService.getAllUsers()).thenReturn(personList);

        // Act & Assert
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].firstname").value("John"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].firstname").value("Jane"))
                .andExpect(jsonPath("$.message").value("Operación exitosa"))
                .andExpect(jsonPath("$.status").value("OK"));
    }

    @Test
    public void testGetByPersonId() throws Exception {
        // Arrange
        PersonOutDto person = new PersonOutDto();
        person.setId(1L);
        person.setFirstname("John");

        when(personService.getByPersonId(1L)).thenReturn(person);

        // Act & Assert
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.firstname").value("John"))
                .andExpect(jsonPath("$.message").value("Operación exitosa"))
                .andExpect(jsonPath("$.status").value("OK"));
    }

    @Test
    public void testCreateUser() throws Exception {
        // Arrange
        PersonInDto personInDto = new PersonInDto();
        personInDto.setFirstname("John");
        personInDto.setFirstLastname("Doe");
        personInDto.setBirthdate(LocalDate.of(1990, 1, 1));

        PersonOutDto personOutDto = new PersonOutDto();
        personOutDto.setId(1L);
        personOutDto.setFirstname("John");

        when(personService.createUser(any(PersonInDto.class))).thenReturn(personOutDto);

        // Act & Assert
        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personInDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.firstname").value("John"))
                .andExpect(jsonPath("$.message").value("Usuario modificado "))
                .andExpect(jsonPath("$.status").value("OK"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Arrange
        PersonInDto personInDto = new PersonInDto();
        personInDto.setFirstname("John");
        personInDto.setFirstLastname("Doe");
        personInDto.setBirthdate(LocalDate.of(1990, 1, 1));

        PersonOutDto personOutDto = new PersonOutDto();
        personOutDto.setId(1L);
        personOutDto.setFirstname("John");

        when(personService.updateUser(eq(1L), any(PersonInDto.class))).thenReturn(personOutDto);

        // Act & Assert
        mockMvc.perform(put("/users/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personInDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.firstname").value("John"))
                .andExpect(jsonPath("$.message").value("Usuario modificado "))
                .andExpect(jsonPath("$.status").value("OK"));
    }
}