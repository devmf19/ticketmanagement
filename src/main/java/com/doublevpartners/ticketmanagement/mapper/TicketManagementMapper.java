package com.doublevpartners.ticketmanagement.mapper;

import com.doublevpartners.ticketmanagement.dto.request.TicketManagementInDto;
import com.doublevpartners.ticketmanagement.dto.response.TicketManagementOutDto;
import com.doublevpartners.ticketmanagement.model.TicketManagement;
import com.doublevpartners.ticketmanagement.model.TicketStatus;
import com.doublevpartners.ticketmanagement.model.enums.TicketStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketManagementMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "ticket", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "ticketStatus", expression = "java(mapTicketStatus(ticketManagement.getTicketStatus()))")
    TicketManagement toEntity(TicketManagementInDto ticketManagement);

    @Mapping(target = "ticketId", source = "ticket.id")
    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "ticketStatus", expression = "java(ticketManagement.getTicketStatus().getName().name())")
    TicketManagementOutDto toDto(TicketManagement ticketManagement);

    List<TicketManagementOutDto> toDtoList(List<TicketManagement> ticketManagementList);


    default TicketStatus mapTicketStatus(String ticketStatusName) {
        if (ticketStatusName == null || ticketStatusName.isBlank()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío");
        }
        TicketStatus ticketStatus = new TicketStatus();
        ticketStatus.setName(TicketStatusEnum.valueOf(ticketStatusName.toUpperCase()));
        return ticketStatus;
    }
}
