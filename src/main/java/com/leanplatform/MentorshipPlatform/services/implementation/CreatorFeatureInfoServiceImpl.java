package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsForCreatorResponse;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsRequest;
import com.leanplatform.MentorshipPlatform.entities.CreatorFeatureInfo;
import com.leanplatform.MentorshipPlatform.entities.HeroSection;
import com.leanplatform.MentorshipPlatform.entities.LandingPage;
import com.leanplatform.MentorshipPlatform.entities.SubHeroSection;
import com.leanplatform.MentorshipPlatform.repositories.CreatorFeatureInfoRepository;
import com.leanplatform.MentorshipPlatform.repositories.HeroSectionRepository;
import com.leanplatform.MentorshipPlatform.repositories.LandingPageRepository;
import com.leanplatform.MentorshipPlatform.services.CreatorFeatureInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CreatorFeatureInfoServiceImpl implements CreatorFeatureInfoService {

    @Autowired  CreatorFeatureInfoRepository creatorFeatureInfoRepository;
    @Autowired LandingPageRepository landingPageRepository;
    @Autowired HeroSectionRepository heroSectionRepository;

    @Override
    public ResponseEntity<CreateDetailsForCreatorResponse> AddCreatorPersonalieFeature(UUID userId, CreateDetailsRequest createDetailsRequest) {
        if (createDetailsRequest == null ||
                createDetailsRequest.getMasterClass() == null || createDetailsRequest.getLeadGenForm() == null ||
                createDetailsRequest.getSlot() == null || createDetailsRequest.getLandingPageRequest() == null) {
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
        CreatorFeatureInfo creatorFeatureInfo=new CreatorFeatureInfo();
        //setting the slot as true or false
        creatorFeatureInfo.setSlot(createDetailsRequest.getSlot());
        //setting the masterClass
        creatorFeatureInfo.setMasterClass(createDetailsRequest.getMasterClass());
        //setting the leadGenForm
        creatorFeatureInfo.setLeadGenForm(createDetailsRequest.getLeadGenForm());


        //save the creatorFeatureInfo in the db
        creatorFeatureInfoRepository.save(creatorFeatureInfo);

        //setting the contenta of the landing page
        LandingPage landingPage=new LandingPage();
        landingPage.setCreatorFeatureInfoId(creatorFeatureInfo.getCreatorFeatureInfoId());
        // set userId for landingPage
        landingPage.setUserId(userId);
        //set the landingPageId
//        landingPage.setLandingPageId(createDetailsRequest.getLandingPageRequest().getLandingPageId());
        landingPageRepository.save(landingPage);
        HeroSection heroSection =new HeroSection();
        heroSection.setBody(createDetailsRequest.getLandingPageRequest().getHeroDto().getBody());
        heroSection.setHeading(createDetailsRequest.getLandingPageRequest().getHeroDto().getHeading());
        heroSection.setCtaText(createDetailsRequest.getLandingPageRequest().getHeroDto().getCtaText());
        heroSection.setCtaNavigation(createDetailsRequest.getLandingPageRequest().getHeroDto().getCtaNavigation());
        heroSection.setLandingPageId(landingPage.getLandingPageId());
        heroSection.setUserId(landingPage.getUserId());
        heroSectionRepository.save(heroSection);
        SubHeroSection subHeroSection =new SubHeroSection();
        subHeroSection.setBody(createDetailsRequest.getLandingPageRequest().getSubHeroDto().getBody());
        subHeroSection.setHeading(createDetailsRequest.getLandingPageRequest().getSubHeroDto().getHeading());
        subHeroSection.setImg(createDetailsRequest.getLandingPageRequest().getSubHeroDto().getImg());





        return new ResponseEntity<>(new CreateDetailsForCreatorResponse
               ("1",
                "Booking Created", null), HttpStatus.OK);

    }

}
