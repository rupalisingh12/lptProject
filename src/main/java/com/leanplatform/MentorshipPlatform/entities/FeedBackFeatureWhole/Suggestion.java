package com.leanplatform.MentorshipPlatform.entities.FeedBackFeatureWhole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table
@ToString
public class Suggestion {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID suggestionId;
    private String emailId;
    private String details;
    private String userName;
    private List<String> fileUrls;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime createdBy;
    @CreationTimestamp
    private LocalDateTime modifiedBy;
    @CreationTimestamp
    private LocalDateTime modifiedAt;

}
