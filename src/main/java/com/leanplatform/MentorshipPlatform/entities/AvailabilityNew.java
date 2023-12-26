package com.leanplatform.MentorshipPlatform.entities;

import com.leanplatform.MentorshipPlatform.enums.DaysOfTheWeek;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AvailabilityNew {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID availabilityId;
    private UUID scheduleId;
    private Long day;
    private List<Long>slotIds;




}
