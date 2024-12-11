package com.doublevpartners.ticketmanagement.service;

import com.doublevpartners.ticketmanagement.model.Role;
import com.doublevpartners.ticketmanagement.model.enums.RoleEnum;

public interface RoleService {
    Role readByName(RoleEnum name);
}
