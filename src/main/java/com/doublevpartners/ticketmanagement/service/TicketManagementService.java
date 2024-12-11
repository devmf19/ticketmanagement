package com.doublevpartners.ticketmanagement.service;

import com.doublevpartners.ticketmanagement.dto.request.TicketManagementInDto;
import com.doublevpartners.ticketmanagement.dto.response.TicketManagementOutDto;

import java.util.List;

public interface TicketManagementService {

    TicketManagementOutDto create(TicketManagementInDto ticketManagement);
    List<TicketManagementOutDto> readByTicketId (Long ticketId);
    void deleteTicket(Long ticketId);

}
