package com.doublevpartners.ticketmanagement.mapper;


import com.doublevpartners.ticketmanagement.dto.request.TicketInDto;
import com.doublevpartners.ticketmanagement.dto.response.TicketOutDto;
import com.doublevpartners.ticketmanagement.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ticketStatus", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "ticketType", ignore = true)
    Ticket toEntity(TicketInDto ticket);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "ticketStatus", expression = "java(ticket.getTicketStatus().getName().name())")
    @Mapping(target = "ticketType", expression = "java(ticket.getTicketType().getName().name())")
    @Mapping(target = "managementList", ignore = true)
    TicketOutDto toDto(Ticket ticket);

    List<TicketOutDto> toDtoList(List<Ticket> ticketList);

}
