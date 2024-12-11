package com.doublevpartners.ticketmanagement.service;

import com.doublevpartners.ticketmanagement.dto.request.TicketInDto;
import com.doublevpartners.ticketmanagement.dto.response.TicketOutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface TicketService {
    TicketOutDto getById(Long ticketId);
    TicketOutDto createTicket(TicketInDto ticket);
    TicketOutDto updateTicket(Long ticketId, TicketInDto ticket);
    void deleteTicket(Long ticketId);
    Page<TicketOutDto> getAll(Pageable pageable);
    Page<TicketOutDto> getByNameOrDescriptionContaining(Pageable pageable, String search);
    Page<TicketOutDto> getByTicketStatus(Pageable pageable, String ticketStatus);
    Page<TicketOutDto> getByTicketType(Pageable pageable, String ticketType);
    Page<TicketOutDto> getByUsername(Pageable pageable, String username);
    Page<TicketOutDto> getByCreatedAtBetween(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);
}
