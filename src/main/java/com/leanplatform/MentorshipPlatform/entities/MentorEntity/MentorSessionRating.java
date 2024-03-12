package com.leanplatform.MentorshipPlatform.entities.MentorEntity;

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
public class MentorSessionRating {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID sessionRatingId;

    private UUID mentorId;
    private UUID menteeId;
    private UUID serviceId;
    private UUID sessionId;
    private String feedback;
    private Double sessionRating;
}
