package com.leanplatform.MentorshipPlatform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


import java.util.UUID;
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Schedule {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID scheduleId;
    private UUID userId;
    private String name;
    @CreationTimestamp
    private LocalDateTime createdAt;




}
