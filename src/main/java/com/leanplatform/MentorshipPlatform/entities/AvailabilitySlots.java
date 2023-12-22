package com.leanplatform.MentorshipPlatform.entities;

import com.leanplatform.MentorshipPlatform.enums.DaysOfTheWeek;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AvailabilitySlots {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID availabilitySlotId;
    private UUID availabilityId;
    private UUID scheduleId;
    private UUID slotId;
    private DaysOfTheWeek day;

}
