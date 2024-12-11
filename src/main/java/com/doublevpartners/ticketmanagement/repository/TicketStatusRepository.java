package com.doublevpartners.ticketmanagement.repository;

import com.doublevpartners.ticketmanagement.model.TicketStatus;
import com.doublevpartners.ticketmanagement.model.enums.TicketStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketStatusRepository extends JpaRepository<TicketStatus, Long> {
    TicketStatus findByName(TicketStatusEnum ticketStatus);
}
