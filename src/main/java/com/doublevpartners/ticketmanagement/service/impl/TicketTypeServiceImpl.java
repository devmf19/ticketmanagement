package com.doublevpartners.ticketmanagement.service.impl;

import com.doublevpartners.ticketmanagement.model.TicketType;
import com.doublevpartners.ticketmanagement.model.enums.TicketTypeEnum;
import com.doublevpartners.ticketmanagement.repository.TicketTypeRepository;
import com.doublevpartners.ticketmanagement.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketTypeServiceImpl implements TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;

    @Autowired
    public TicketTypeServiceImpl(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Override
    public TicketType readByName(TicketTypeEnum name) {
        return ticketTypeRepository.findByName(name);
    }
}
