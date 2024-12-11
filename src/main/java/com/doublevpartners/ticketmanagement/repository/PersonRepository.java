package com.doublevpartners.ticketmanagement.repository;

import com.doublevpartners.ticketmanagement.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
