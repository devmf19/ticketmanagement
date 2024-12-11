package com.doublevpartners.ticketmanagement.model.enums;

import lombok.Getter;

@Getter
public enum TicketTypeEnum {
    BUG("BUG"),
    REQUEST("REQUEST")
    ;

    private final String type;

    TicketTypeEnum(String type) {
        this.type = type;
    }

    public static TicketTypeEnum fromString(String type) {
        for (TicketTypeEnum ticketType : TicketTypeEnum.values()) {
            if (ticketType.type.equalsIgnoreCase(type)) {
                return ticketType;
            }
        }
        throw new IllegalArgumentException("Invalid TicketType: " + type);
    }
    
}
