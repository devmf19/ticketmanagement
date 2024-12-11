package com.doublevpartners.ticketmanagement.model;

import com.doublevpartners.ticketmanagement.model.enums.TicketStatusEnum;
import com.doublevpartners.ticketmanagement.model.enums.TicketTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket_status")
@Getter
@Setter
@NoArgsConstructor
public class TicketStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatusEnum name;
}
