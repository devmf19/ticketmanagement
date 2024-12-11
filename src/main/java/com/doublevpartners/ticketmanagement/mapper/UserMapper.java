package com.doublevpartners.ticketmanagement.mapper;

import com.doublevpartners.ticketmanagement.dto.request.PersonInDto;
import com.doublevpartners.ticketmanagement.dto.response.UserOutDto;
import com.doublevpartners.ticketmanagement.model.Role;
import com.doublevpartners.ticketmanagement.model.User;
import com.doublevpartners.ticketmanagement.model.enums.RoleEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", expression = "java(user.getRole().getName().name())")
    @Mapping(target = "personId", source = "person.id")
    UserOutDto toDto(User user);

    List<UserOutDto> toDtoList(List<User> users);

//    default Role mapRole(String roleName) {
//        if (roleName == null || roleName.isBlank()) {
//            throw new IllegalArgumentException("El nombre del rol no puede estar vacío");
//        }
//        Role role = new Role();
//        role.setName(RoleEnum.valueOf(roleName.toUpperCase()));
//        return role;
//    }
}
