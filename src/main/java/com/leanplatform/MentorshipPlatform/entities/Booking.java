package com.leanplatform.MentorshipPlatform.entities;

import com.leanplatform.MentorshipPlatform.enums.BookingEnums;
import com.leanplatform.MentorshipPlatform.enums.MentorEnums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.Metadata;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Booking {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID bookingId;
    private UUID userId;
    private String description;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UUID eventTypeId; //(This is coming from EventType table);
   // private List<Attendee> attendees;( the coloumn of a table can not be a list)
  // private UUID metaDataId;
   private  BookingEnums status;
   private UUID locationId;

  //  private BookingEnums status;



}
