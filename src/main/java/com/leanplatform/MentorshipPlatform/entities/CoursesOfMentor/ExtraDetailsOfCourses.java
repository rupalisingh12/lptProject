package com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor;

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
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ExtraDetailsOfCourses {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID extraDetailsOfCourses;
    private UUID courseId;
    private String overview;
    private List<String> whoThisCourseIsFor;
    private List<String>thisCourseIncludes;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime createdBy;
    @CreationTimestamp
    private LocalDateTime modifiedBy;
    @CreationTimestamp
    private LocalDateTime modifiedAt;



}
