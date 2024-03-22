package com.leanplatform.MentorshipPlatform.entities.BookingFeature;

import com.leanplatform.MentorshipPlatform.enums.BookingEnums;
import com.leanplatform.MentorshipPlatform.enums.MentorEnums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.boot.Metadata;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
   // private List<Attendee> attendees;
  // private UUID metaDataId;
   private  BookingEnums status;
   private LocalDate date;
   private UUID locationId;
   private Set<Long> slotIds;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime createdBy;
    @CreationTimestamp
    private LocalDateTime modifiedBy;
    @CreationTimestamp
    private LocalDateTime modifiedAt;



    //  private BookingEnums status;



}
