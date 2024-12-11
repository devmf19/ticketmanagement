package com.doublevpartners.ticketmanagement.repository;

import com.doublevpartners.ticketmanagement.model.Ticket;
import com.doublevpartners.ticketmanagement.model.TicketManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketManagementRepository extends JpaRepository<TicketManagement, Long> {
    List<TicketManagement> findByTicket(Ticket ticket);
    @Modifying
    @Query("DELETE FROM TicketManagement tm WHERE tm.ticket.id = :ticketId")
    void deleteByTicketId(@Param("ticketId") Long ticketId);
}
