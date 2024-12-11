package com.doublevpartners.ticketmanagement.repository;

import com.doublevpartners.ticketmanagement.model.Person;
import com.doublevpartners.ticketmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByPerson(Person person);

    @Query("FROM User u WHERE u.person.id = :personId")
    User findByPersonId(Long personId);
}
