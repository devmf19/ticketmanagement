package com.doublevpartners.ticketmanagement.service.impl;

import com.doublevpartners.ticketmanagement.dto.request.TicketInDto;
import com.doublevpartners.ticketmanagement.dto.response.TicketOutDto;
import com.doublevpartners.ticketmanagement.mapper.TicketMapper;
import com.doublevpartners.ticketmanagement.model.*;
import com.doublevpartners.ticketmanagement.model.enums.RoleEnum;
import com.doublevpartners.ticketmanagement.model.enums.TicketStatusEnum;
import com.doublevpartners.ticketmanagement.model.enums.TicketTypeEnum;
import com.doublevpartners.ticketmanagement.repository.*;
import com.doublevpartners.ticketmanagement.service.TicketManagementService;
import com.doublevpartners.ticketmanagement.service.TicketService;
import com.doublevpartners.ticketmanagement.util.Constants;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final PersonRepository personRepository;
    private final TicketStatusRepository ticketStatusRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;
    private final TicketManagementService ticketManagementService;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,
                             TicketMapper ticketMapper,
                             TicketStatusRepository ticketStatusRepository,
                             TicketTypeRepository ticketTypeRepository,
                             UserRepository userRepository,
                             PersonRepository personRepository,
                             TicketManagementService ticketManagementService) {
        this.ticketRepository = ticketRepository;
        this.personRepository = personRepository;
        this.ticketStatusRepository = ticketStatusRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.userRepository = userRepository;
        this.ticketMapper = ticketMapper;
        this.ticketManagementService = ticketManagementService;
    }

    @Override
    public TicketOutDto getById(Long ticketId) {
        TicketOutDto ticketOutDto = ticketMapper.toDto(
                ticketRepository.findById(ticketId).orElseThrow(
                        () -> new EntityNotFoundException(Constants.NOT_FOUND_TICKET)
                )
        );

        ticketOutDto.setManagementList(
                ticketManagementService.readByTicketId(ticketOutDto.getId())
        );
        return ticketOutDto;
    }


    @Override
    public TicketOutDto createTicket(TicketInDto ticket) {
        Person person = personRepository.findById(ticket.getPersonId()).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_PERSON)
        );

        User user = userRepository.findByUsername(ticket.getUsername()).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_USER.concat(ticket.getUsername()))
        );

        if (!user.getRole().getName().name().equals(RoleEnum.DISABLED.name())) {
            Ticket ticketSaved = ticketMapper.toEntity(ticket);
            ticketSaved.setPerson(person);
            ticketSaved.setTicketType(ticketTypeRepository.findByName(TicketTypeEnum.fromString(ticket.getTicketType())));
            ticketSaved.setTicketStatus(ticketStatusRepository.findByName(TicketStatusEnum.ACTIVE));

            return ticketMapper.toDto(
                    ticketRepository.save(ticketSaved)
            );
        }

        throw new IllegalArgumentException(Constants.DISABLED_USER.concat(ticket.getUsername()));

    }

    @Override
    public TicketOutDto updateTicket(Long ticketId, TicketInDto ticket) {
        Ticket savedTicket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_TICKET)
        );

        Person assignedPerson = personRepository.findById(ticket.getAssignedPersonId()).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_PERSON.concat(ticket.getAssignedPersonId().toString()))
        );

        User assignedPersonUser = userRepository.findByPerson(assignedPerson).orElseThrow(
                () -> new EntityNotFoundException(Constants.NOT_FOUND_USER.concat(ticket.getAssignedPersonId().toString()))
        );

        if (!(assignedPersonUser.getRole().getName().name().equals(RoleEnum.DISABLED.name()) ||
                savedTicket.getTicketStatus().getName().name().equals(TicketStatusEnum.CLOSED.name()))) {

            savedTicket.setName(ticket.getName());
            savedTicket.setDetails(ticket.getDetails());
            savedTicket.setAssignedPersonId(ticket.getAssignedPersonId());
            savedTicket.setTicketStatus(ticketStatusRepository.findByName(
                    TicketStatusEnum.fromString(ticket.getTicketStatus())
            ));
            savedTicket.setTicketType(ticketTypeRepository.findByName(
                    TicketTypeEnum.fromString(ticket.getTicketType())
            ));
            TicketOutDto ticketOutDto = ticketMapper.toDto(ticketRepository.save(savedTicket));
            ticketOutDto.setManagementList(
                    ticketManagementService.readByTicketId(ticketOutDto.getId())
            );
            return ticketOutDto;
        }


        throw new EntityNotFoundException(Constants.DISABLED_USER.concat(ticket.getUsername()));
    }

    @Override
    public void deleteTicket(Long ticketId) {
        ticketManagementService.deleteTicket(ticketId);
    }

    @Override
    public Page<TicketOutDto> getAll(Pageable pageable) {
        return ticketRepository.findAll(pageable)
                .map(ticketEntity -> {
                    TicketOutDto ticketOutDto = ticketMapper.toDto(ticketEntity);
                    ticketOutDto.setManagementList(
                            ticketManagementService.readByTicketId(ticketOutDto.getId())
                    );
                    return ticketOutDto;
                });
    }

    @Override
    public Page<TicketOutDto> getByNameOrDescriptionContaining(Pageable pageable, String search) {
        return ticketRepository.findByNameContainingIgnoreCaseOrDetailsContainingIgnoreCase(pageable, search, search)
                .map(ticketEntity -> {
                    TicketOutDto ticketOutDto = ticketMapper.toDto(ticketEntity);
                    ticketOutDto.setManagementList(
                            ticketManagementService.readByTicketId(ticketOutDto.getId())
                    );
                    return ticketOutDto;
                });
    }

    @Override
    public Page<TicketOutDto> getByTicketStatus(Pageable pageable, String ticketStatus) {
        TicketStatus status = ticketStatusRepository.findByName(TicketStatusEnum.fromString(ticketStatus));
        return ticketRepository.findByTicketStatus(pageable, status)
                .map(ticketEntity -> {
                    TicketOutDto ticketOutDto = ticketMapper.toDto(ticketEntity);
                    ticketOutDto.setManagementList(
                            ticketManagementService.readByTicketId(ticketOutDto.getId())
                    );
                    return ticketOutDto;
                });
    }

    @Override
    public Page<TicketOutDto> getByTicketType(Pageable pageable, String ticketType) {
        TicketType type = ticketTypeRepository.findByName(TicketTypeEnum.fromString(ticketType));
        return ticketRepository.findByTicketType(pageable, type)
                .map(ticketEntity -> {
                    TicketOutDto ticketOutDto = ticketMapper.toDto(ticketEntity);
                    ticketOutDto.setManagementList(
                            ticketManagementService.readByTicketId(ticketOutDto.getId())
                    );
                    return ticketOutDto;
                });
    }

    @Override
    public Page<TicketOutDto> getByUsername(Pageable pageable, String username) {
        return ticketRepository.findByUsername(pageable, username)
                .map(ticketEntity -> {
                    TicketOutDto ticketOutDto = ticketMapper.toDto(ticketEntity);
                    ticketOutDto.setManagementList(
                            ticketManagementService.readByTicketId(ticketOutDto.getId())
                    );
                    return ticketOutDto;
                });
    }

    @Override
    public Page<TicketOutDto> getByCreatedAtBetween(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate) {
        return ticketRepository.findByCreatedAtBetween(pageable, startDate, endDate)
                .map(ticketEntity -> {
                    TicketOutDto ticketOutDto = ticketMapper.toDto(ticketEntity);
                    ticketOutDto.setManagementList(
                            ticketManagementService.readByTicketId(ticketOutDto.getId())
                    );
                    return ticketOutDto;
                });
    }
}
