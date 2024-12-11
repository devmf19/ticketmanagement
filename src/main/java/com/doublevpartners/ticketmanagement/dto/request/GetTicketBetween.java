package com.doublevpartners.ticketmanagement.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GetTicketBetween {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
