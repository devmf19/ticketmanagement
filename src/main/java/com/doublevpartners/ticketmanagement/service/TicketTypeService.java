package com.doublevpartners.ticketmanagement.service;

import com.doublevpartners.ticketmanagement.model.TicketType;
import com.doublevpartners.ticketmanagement.model.enums.TicketTypeEnum;

public interface TicketTypeService {
    TicketType readByName(TicketTypeEnum name);
}
