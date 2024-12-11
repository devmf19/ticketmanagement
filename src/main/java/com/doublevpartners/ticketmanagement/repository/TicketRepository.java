package com.doublevpartners.ticketmanagement.repository;

import com.doublevpartners.ticketmanagement.model.Ticket;
import com.doublevpartners.ticketmanagement.model.TicketStatus;
import com.doublevpartners.ticketmanagement.model.TicketType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Page<Ticket> findByNameContainingIgnoreCaseOrDetailsContainingIgnoreCase(Pageable pageable, String name, String details);
    Page<Ticket> findByTicketStatus(Pageable pageable, TicketStatus ticketStatus);
    Page<Ticket> findByTicketType(Pageable pageable, TicketType ticketType);
    Page<Ticket> findByUsername(Pageable pageable, String username);
    Page<Ticket> findByCreatedAtBetween(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);
}
