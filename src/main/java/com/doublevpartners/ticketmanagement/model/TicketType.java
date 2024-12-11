package com.doublevpartners.ticketmanagement.model;

import com.doublevpartners.ticketmanagement.model.enums.TicketTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket_type")
@Getter
@Setter
@NoArgsConstructor
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketTypeEnum name;
}
