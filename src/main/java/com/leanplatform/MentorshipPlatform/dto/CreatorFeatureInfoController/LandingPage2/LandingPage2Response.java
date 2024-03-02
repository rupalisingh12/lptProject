package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPage2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LandingPage2Response {
    private String landingPage2Id; //(this is for like whoich landing page it is out of the two landing page available to the creator)
    private int landingPage2VariantId;
    private HeroDto2 heroDto2;
    private SubHeroDto2 subHeroDto2;
    private GettingDto2 gettingDto;
    private UUID userId;

    private String userName;
    private ServicesAvail servicesAvail;
    private CharacteristicsOfService characteristicsOfService;
    private ApplySectionDtoLP2 applySectionDtoLP2;
    private HelpSection helpSection;
    private ProductDescription productDescription;
    private SlotConsultationLP2 slotConsultationDtoLP2;
    private Testimonials testimonials;
    private CallToAction callToAction;


}
