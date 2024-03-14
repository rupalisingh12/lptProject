package com.leanplatform.MentorshipPlatform.entities.MentorEntity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Mentor {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID mentor_id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String resume;
    private String email;
    private String linkedIn_link;
    private Integer yearsOfExperience;
    private String mentorRoleApplied;
    private String lastPlaceOfEmployment;
    private String currentPlaceOfEmployment;
    private String priorToLastPlaceOfEmployment;
    private String prioritySkill;
    private String educationQualification;
    private Double overAllRating;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}

