package com.doublevpartners.ticketmanagement.service.impl;

import com.doublevpartners.ticketmanagement.model.TicketStatus;
import com.doublevpartners.ticketmanagement.model.enums.TicketStatusEnum;
import com.doublevpartners.ticketmanagement.repository.TicketStatusRepository;
import com.doublevpartners.ticketmanagement.service.TicketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketStatusServiceImpl implements TicketStatusService {
    private final TicketStatusRepository ticketStatusRepository;

    @Autowired
    public TicketStatusServiceImpl(TicketStatusRepository ticketStatusRepository) {
        this.ticketStatusRepository = ticketStatusRepository;
    }

    @Override
    public TicketStatus readByName(TicketStatusEnum name) {
        return ticketStatusRepository.findByName(name);
    }
}
