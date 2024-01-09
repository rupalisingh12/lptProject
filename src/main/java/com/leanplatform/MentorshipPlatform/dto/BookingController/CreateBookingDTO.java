package com.leanplatform.MentorshipPlatform.dto.BookingController;

import com.leanplatform.MentorshipPlatform.entities.Attendee;
import com.leanplatform.MentorshipPlatform.entities.UserEntity;
import com.leanplatform.MentorshipPlatform.enums.BookingEnums;
import com.twilio.rest.chat.v1.service.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBookingDTO {
    private UUID id;
    private UUID userId;
    private String description;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingEnums Status;
    private UUID eventTypeId; //(This is coming from EventType table);
    private UserDTO user;
  private List<Attendee> attendees;
   // private ArrayList<UserEntity>user;



}
