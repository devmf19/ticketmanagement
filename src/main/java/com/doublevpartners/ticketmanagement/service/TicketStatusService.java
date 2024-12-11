package com.doublevpartners.ticketmanagement.service;

import com.doublevpartners.ticketmanagement.model.TicketStatus;
import com.doublevpartners.ticketmanagement.model.enums.TicketStatusEnum;

public interface TicketStatusService {
    TicketStatus readByName(TicketStatusEnum name);
}
