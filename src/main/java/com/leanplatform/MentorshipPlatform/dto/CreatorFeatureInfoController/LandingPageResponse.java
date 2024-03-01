package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LandingPageResponse {
    private UUID landingPage;
    private UUID creatorFeatureInfoId;
    private String userName;


    private SubHeroDto subHeroDto;

    private HeroDto heroDto;
    private int landingPageVariantId;
    private HelpDto helpDto;

    private LearningDto learningDto;

    private IndividualCardsDto individualCardsDto;

    private ApplySectionDto applySectionDto;

    private BelowApplySection belowApplySection;

    private IndividualCardsDto2 individualCardsDto2;

    private SlotConsultation slotConsultationDto;

    private LearningDto2 learningDto2;

    private FinalGoDto finalGoDto;
    private UUID userId;
    private String landingPageId; //the id which is creattor ging to choose out of the two landingPage

}
