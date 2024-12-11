package com.doublevpartners.ticketmanagement.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TicketManagementOutDto {
    private Long id;
    private LocalDateTime createdAt;
    private String comment;
    private String username;
    private Long ticketId;
    private String personId;
    private String ticketStatus;
}
