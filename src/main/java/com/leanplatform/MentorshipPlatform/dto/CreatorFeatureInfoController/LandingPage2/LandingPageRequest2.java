package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPage2;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collector;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LandingPageRequest2 {
    private String landingPage2Id; //(this is for like whoich landing page it is out of the two landing page available to the creator)
    private String landingPage2VariantId;
    private HeroDto2 heroDto2;
    private SubHeroDto2 subHeroDto2;
    private GettingDto2 gettingDto;
    private ServicesAvail servicesAvail;
    private CharacteristicsOfService individualCardsDto;
    private ApplySectionDtoLP2 applySectionDtoLP2;
    private HelpSection helpSection;
    private ProductDescription productDescription;
    private SlotConsultationLP2 slotConsultationDtoLP2;
    private Testimonials testimonials;
    private CallToAction callToAction;


}
