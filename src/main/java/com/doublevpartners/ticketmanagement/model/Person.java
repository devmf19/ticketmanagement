package com.doublevpartners.ticketmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "document_number", unique = true, nullable = false)
    private String documentNumber;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "secondname")
    private String secondName;

    @Column(name = "first_lastname", nullable = false)
    private String firstLastname;

    @Column(name = "second_lastname")
    private String secondLastname;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;
}
