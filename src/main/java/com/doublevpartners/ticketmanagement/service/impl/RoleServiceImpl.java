package com.doublevpartners.ticketmanagement.service.impl;

import com.doublevpartners.ticketmanagement.model.Role;
import com.doublevpartners.ticketmanagement.model.enums.RoleEnum;
import com.doublevpartners.ticketmanagement.repository.RoleRepository;
import com.doublevpartners.ticketmanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role readByName(RoleEnum name) {
        return roleRepository.findByName(name);
    }
}
