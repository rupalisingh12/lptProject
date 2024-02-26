package com.leanplatform.MentorshipPlatform.entities;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.*;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.BelowApplySection;
import jakarta.persistence.*;
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
public class LandingPage {
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
}
