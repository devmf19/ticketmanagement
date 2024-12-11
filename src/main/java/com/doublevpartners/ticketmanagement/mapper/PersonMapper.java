package com.doublevpartners.ticketmanagement.mapper;

import com.doublevpartners.ticketmanagement.dto.request.PersonInDto;
import com.doublevpartners.ticketmanagement.dto.response.PersonOutDto;
import com.doublevpartners.ticketmanagement.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonInDto person);

    PersonOutDto toDto(Person person);

    List<PersonOutDto> toDtoList(List<Person> personList);
}
