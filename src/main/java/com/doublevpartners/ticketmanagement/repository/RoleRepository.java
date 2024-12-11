package com.doublevpartners.ticketmanagement.repository;

import com.doublevpartners.ticketmanagement.model.Role;
import com.doublevpartners.ticketmanagement.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleEnum role);
}
