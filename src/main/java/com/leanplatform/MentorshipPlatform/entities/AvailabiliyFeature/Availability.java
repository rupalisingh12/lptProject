package com.leanplatform.MentorshipPlatform.entities.AvailabiliyFeature;

import com.leanplatform.MentorshipPlatform.enums.DaysOfTheWeek;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Availability {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID availabilityId;
    private UUID mentorId;
    private DaysOfTheWeek dayOfTheWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private Boolean isBooked;

}






