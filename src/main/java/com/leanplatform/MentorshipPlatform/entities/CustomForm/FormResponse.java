package com.leanplatform.MentorshipPlatform.entities.CustomForm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int response_id;

    private String title;
    private String name;
    private String email;
    private String phoneNumber;
    private String others;
    private LocalDateTime submittedAt;
    private LocalDateTime openedAt;
    private UUID visitorId;
}
