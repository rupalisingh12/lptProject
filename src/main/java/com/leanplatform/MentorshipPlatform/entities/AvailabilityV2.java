package com.leanplatform.MentorshipPlatform.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AvailabilityV2 {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID availabilityId;
    private UUID scheduleId;
    private Long day;
    private Set<Long> slotIds;
    @CreationTimestamp
    private LocalDateTime createdAt;




}
