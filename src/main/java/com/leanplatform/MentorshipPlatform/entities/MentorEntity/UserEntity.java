package com.leanplatform.MentorshipPlatform.entities.MentorEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userId;
    private String userName;
    private String name;
    private String email;
    private String bio;
    private String avatar;
    private UUID defaultScheduleId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime createdBy;
    @CreationTimestamp
    private LocalDateTime modifiedBy;
    @CreationTimestamp
    private LocalDateTime modifiedAt;

}
