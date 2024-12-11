package com.doublevpartners.ticketmanagement.model.enums;

import lombok.Getter;

@Getter
public enum TicketStatusEnum {
    ACTIVE("ACTIVE"),
    ATTENDING("ATTENDING"),
    RESOLVED ("RESOLVED"),
    CLOSED ("CLOSED")
    ;

    private final String status;

    TicketStatusEnum(String status) {
        this.status = status;
    }

    public static TicketStatusEnum fromString(String status) {
        for (TicketStatusEnum ticketStatus : TicketStatusEnum.values()) {
            if (ticketStatus.status.equalsIgnoreCase(status)) {
                return ticketStatus;
            }
        }
        throw new IllegalArgumentException("Invalid TicketStatus: " + status);
    }
}
