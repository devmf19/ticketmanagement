package com.doublevpartners.ticketmanagement.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketManagementInDto {
    private String comment;
    private String username;
    private Long ticketId;
    private String ticketStatus;
    private Long personId;
}
