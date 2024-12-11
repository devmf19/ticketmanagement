package com.doublevpartners.ticketmanagement.service.impl;

import com.doublevpartners.ticketmanagement.dto.request.TicketManagementInDto;
import com.doublevpartners.ticketmanagement.dto.response.TicketManagementOutDto;
import com.doublevpartners.ticketmanagement.mapper.TicketManagementMapper;
import com.doublevpartners.ticketmanagement.model.Person;
import com.doublevpartners.ticketmanagement.model.Ticket;
import com.doublevpartners.ticketmanagement.model.TicketManagement;
import com.doublevpartners.ticketmanagement.model.User;
import com.doublevpartners.ticketmanagement.model.enums.RoleEnum;
import com.doublevpartners.ticketmanagement.model.enums.TicketStatusEnum;
import com.doublevpartners.ticketmanagement.repository.*;
import com.doublevpartners.ticketmanagement.service.TicketManagementService;
import com.doublevpartners.ticketmanagement.service.TicketStatusService;
import com.doublevpartners.ticketmanagement.util.Constants;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketManagementServiceImpl implements TicketManagementService {
    private final TicketManagementRepository ticketManagementRepository;
    private final TicketManagementMapper ticketManagementMapper;
    private final TicketStatusService ticketStatusService;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketManagementServiceImpl(TicketManagementRepository ticketManagementRepository,
                                       TicketManagementMapper ticketManagementMapper,
                                       TicketStatusService ticketStatusService,
                                       PersonRepository personRepository,
                                       UserRepository userRepository,
                                       TicketRepository ticketRepository) {
        this.ticketManagementRepository = ticketManagementRepository;
        this.ticketManagementMapper = ticketManagementMapper;
        this.ticketStatusService = ticketStatusService;
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public TicketManagementOutDto create(TicketManagementInDto ticketManagement) {
        Person person = personRepository.findById(ticketManagement.getPersonId()).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_PERSON.concat(ticketManagement.getPersonId().toString()))
        );

        User user = userRepository.findByUsername(ticketManagement.getUsername()).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_USER.concat(ticketManagement.getUsername()))
        );

        Ticket ticket = ticketRepository.findById(ticketManagement.getTicketId()).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_TICKET.concat(ticketManagement.getTicketId().toString()))
        );

        if (!user.getRole().getName().name().equalsIgnoreCase(RoleEnum.DISABLED.name()) &&
                !ticket.getTicketStatus().getName().name().equalsIgnoreCase(TicketStatusEnum.CLOSED.name())) {
            TicketManagement savedTicketManagement = ticketManagementMapper.toEntity(ticketManagement);
            savedTicketManagement.setPerson(person);
            savedTicketManagement.setTicket(ticket);
            savedTicketManagement.setTicketStatus(ticketStatusService.readByName(savedTicketManagement.getTicketStatus().getName()));

            ticket.setTicketStatus(ticketStatusService.readByName(TicketStatusEnum.fromString(ticketManagement.getTicketStatus())));
            ticketRepository.save(ticket);

            return ticketManagementMapper.toDto(
                    ticketManagementRepository.save(savedTicketManagement)
            );
        }

        throw new EntityNotFoundException(Constants.CLOSED_TICKET.concat(ticketManagement.getPersonId().toString()));
    }

    @Override
    public List<TicketManagementOutDto> readByTicketId(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_TICKET.concat(ticketId.toString()))
        );
        return ticketManagementMapper.toDtoList(
                ticketManagementRepository.findByTicket(ticket)
        );
    }

    @Transactional
    @Override
    public void deleteTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_TICKET)
        );
        ticketManagementRepository.deleteByTicketId(ticketId);
        ticketRepository.delete(ticket);
    }
}
