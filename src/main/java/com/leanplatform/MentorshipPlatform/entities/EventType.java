package com.leanplatform.MentorshipPlatform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
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
public class EventType {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID eventId;
    private String title; //1
    private Integer length;
    private Boolean hidden ;
    private UUID userId;
    private UUID scheduleId;//2
    private Double price;
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
