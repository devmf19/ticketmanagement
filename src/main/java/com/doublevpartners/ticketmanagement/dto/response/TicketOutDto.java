package com.doublevpartners.ticketmanagement.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TicketOutDto {
    private Long id;

    private String name;

    private String details;

    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long assignedPersonId;

    private Long personId;

    private String ticketType;

    private String ticketStatus;

    private List<TicketManagementOutDto> managementList;
}
