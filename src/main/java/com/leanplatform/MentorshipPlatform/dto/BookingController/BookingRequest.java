package com.leanplatform.MentorshipPlatform.dto.BookingController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingRequest {
   private UUID eventTypeId;   //(eventId that is in the eventType table)
    private LocalDateTime start;
    private String description ;
    private ResponseDTO response;


}
