package com.doublevpartners.ticketmanagement.repository;

import com.doublevpartners.ticketmanagement.model.TicketType;
import com.doublevpartners.ticketmanagement.model.enums.TicketTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    TicketType findByName(TicketTypeEnum ticketType);
}
