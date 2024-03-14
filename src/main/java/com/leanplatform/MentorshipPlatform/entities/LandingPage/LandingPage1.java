package com.leanplatform.MentorshipPlatform.entities.LandingPage;

import jakarta.persistence.*;
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
public class LandingPage1 {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID landingPage;
    private UUID creatorFeatureInfoId;
    private int landingPageVariantId;
    private String userName;
    @Column(length = 5000)
    private String subHeroDto;
    @Column(length = 5000)
    private String HeroDto;
    @Column(length = 5000)
    private String helpDto;
    @Column(length = 5000)
    private String learningDto;
    @Column(length = 5000)
    private String individualCardsDto;
    @Column(length = 5000)
    private String applySectionDto;
    @Column(length = 5000)
    private String belowApplySection;
    @Column(length = 5000)
    private String individualCardsDto2;
    @Column(length = 5000)
    private String slotConsultationDto;
    @Column(length = 5000)
    private String learningDto2;
    @Column(length = 5000)
    private String finalGoDto;
    private UUID userId;
    private String landingPageId; //the id which is creattor ging to choose out of the two landingPage
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime createdBy;
    @CreationTimestamp
    private LocalDateTime modifiedBy;
    @CreationTimestamp
    private LocalDateTime modifiedAt;

}

