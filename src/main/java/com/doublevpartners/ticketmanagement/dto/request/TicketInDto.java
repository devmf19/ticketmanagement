package com.doublevpartners.ticketmanagement.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketInDto {
    private String name;

    private String details;

    private String username;

    private Long personId;

    private Long assignedPersonId;

    private String ticketType;

    private String ticketStatus;
}
