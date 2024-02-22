package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LandingPageRequest {

    private String landingPageId; //(this is for like whoich landing page it is out of the two landing page available to the creator)
    private String landingPageVariantId;
    private HeroDto heroDto;
    private SubHeroDto subHeroDto;
    private LearningDto learningDto;
    private HelpDto helpDto;
    private IndividualCardsDto individualCardsDto;
    private ApplySectionDto applySectionDto;
    private BelowApplySection belowApplySection;
    private IndividualCardsDto2 individualCardsDto2;
    private SlotConsultation slotConsultationDto;
    private LearningDto2 learningDto2;
    private FinalGoDto finalGoDto;



}
