package com.leanplatform.MentorshipPlatform.entities;

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
public class LandingPage2 {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID landingPage;
    private UUID creatorFeatureInfoId;
    private int landingPageVariantId;
    private String userName;
    @Column(length =5000 )
    private String heroDto2;
    @Column(length =5000 )
    private String subHeroDto2;
    @Column(length =5000 )
    private String gettingDto;
    @Column(length =5000 )
    private String servicesAvail;
    @Column(length =5000 )
    private String characteristicsOfService;
    @Column(length =5000 )
    private String applySectionDtoLP2;
    @Column(length =5000 )
    private String helpSection;
    @Column(length =5000 )
    private String productDescription;

    @Column(length =5000 )
    private String slotConsultationDtoLP2;
    @Column(length =5000 )
    private String testimonials;
    private String landingPageId;
    @Column(length =5000 )
    private String callToAction;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime createdBy;
    @CreationTimestamp
    private LocalDateTime modifiedBy;
    @CreationTimestamp
    private LocalDateTime modifiedAt;
}
