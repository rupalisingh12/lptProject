package com.leanplatform.MentorshipPlatform.services.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.*;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreatorDetailsRequestLP2;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPage2.*;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.GetAvailabilityButtonsDto;
import com.leanplatform.MentorshipPlatform.entities.CreatorFeatureInfo;
import com.leanplatform.MentorshipPlatform.entities.FeedBackFeature;
import com.leanplatform.MentorshipPlatform.entities.LandingPage2;
import com.leanplatform.MentorshipPlatform.entities.UserEntity;
import com.leanplatform.MentorshipPlatform.mappers.FeedBackFeatureMapper;
import com.leanplatform.MentorshipPlatform.repositories.CreatorFeatureInfoRepository;
import com.leanplatform.MentorshipPlatform.repositories.FeedBackFeatureRepository;
import com.leanplatform.MentorshipPlatform.repositories.LandingPage2Repository;
import com.leanplatform.MentorshipPlatform.repositories.UserRepository;
import com.leanplatform.MentorshipPlatform.services.CreatorFeatureInfoLandingPage2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreatorFeatureInfoLandingPage2ServiceImpl implements CreatorFeatureInfoLandingPage2Service {
    @Autowired
    CreatorFeatureInfoRepository creatorFeatureInfoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LandingPage2Repository landingPage2Repository;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired FeedBackFeatureRepository feedBackFeatureRepository;


    public ResponseEntity<CreateDetailsForCreatorResponse> AddCreatorPersonalieFeatureLP2(String userName, CreatorDetailsRequestLP2 creatorDetailsRequestLP2 ){

        if ( creatorDetailsRequestLP2== null ||
                creatorDetailsRequestLP2.getMasterClass() == null || creatorDetailsRequestLP2.getLeadGenForm() == null ||
                creatorDetailsRequestLP2.getSlot() == null || creatorDetailsRequestLP2.getLandingPageRequest2() == null)

            return new ResponseEntity<>(new CreateDetailsForCreatorResponse
                ("1",
                        "Null object recieved", null), HttpStatus.OK);
        UserEntity userEntity= userRepository.findByUserName(userName);
        if(userEntity==null){
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "This user does not exist in the db", null), HttpStatus.BAD_REQUEST);

        }
        if(creatorFeatureInfoRepository.findByUserName(userName)!=null){
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "This user already has a creataureInfoDetails in db", null), HttpStatus.BAD_REQUEST);

        }

        CreatorFeatureInfo creatorFeatureInfo=new CreatorFeatureInfo();
        creatorFeatureInfo.setLeadGenForm(creatorDetailsRequestLP2.getLeadGenForm());
        creatorFeatureInfo.setMasterClass(creatorDetailsRequestLP2.getMasterClass());
        creatorFeatureInfo.setSlot(creatorDetailsRequestLP2.getSlot());
        creatorFeatureInfo.setUserId(userEntity.getUserId());
        LandingPage2 landingPage2=new LandingPage2();
        landingPage2.setUserName(userName);
        try {
            String json = convertObjectToJson(creatorDetailsRequestLP2);
            landingPage2.setHeroDto2(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson1(creatorDetailsRequestLP2);
            landingPage2.setSubHeroDto2(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson2(creatorDetailsRequestLP2);
            landingPage2.setGettingDto(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson3(creatorDetailsRequestLP2);
            landingPage2.setServicesAvail(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson4(creatorDetailsRequestLP2);
            landingPage2.setCharacteristicsOfService(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson6(creatorDetailsRequestLP2);
            landingPage2.setApplySectionDtoLP2(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson8(creatorDetailsRequestLP2);
            landingPage2.setHelpSection(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson9(creatorDetailsRequestLP2);
            landingPage2.setProductDescription(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson10(creatorDetailsRequestLP2);
            landingPage2.setSlotConsultationDtoLP2(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson11(creatorDetailsRequestLP2);
            landingPage2.setTestimonials(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        try {
//            String json = convertObjectToJson12creatorDetailsRequestLP2);
//            landingPage2.setTestimonials(json);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        try {
            String json = convertObjectToJson12(creatorDetailsRequestLP2);
            landingPage2.setCallToAction(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        landingPage2.setLandingPageVariantId(creatorDetailsRequestLP2.getLandingPageRequest2().getLandingPage2VariantId());
        landingPage2.setLandingPageId(creatorDetailsRequestLP2.getLandingPageRequest2().getLandingPage2Id());
        creatorFeatureInfo.setUserName(userName);
        landingPage2Repository.save(landingPage2);
        creatorFeatureInfoRepository.save(creatorFeatureInfo);
        return new ResponseEntity<>(new CreateDetailsForCreatorResponse
                ("1",
                        "Landing Page data saved", null), HttpStatus.OK);


    }
    public String convertObjectToJson(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getHeroDto2());
    }
    public String convertObjectToJson1(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getSubHeroDto2());
    }
    public String convertObjectToJson2(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getGettingDto());
    }
    public String convertObjectToJson3(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getServicesAvail());
    }
    public String convertObjectToJson4(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getCharacteristicsOfService());
    }
    public String convertObjectToJson5(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getCharacteristicsOfService());
    }
    public String convertObjectToJson6(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getApplySectionDtoLP2());
    }
//    public String convertObjectToJson7(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {
//        ObjectMapper objectMapper=null;
//        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getApplySectionDtoLP2());
//    }
    public String convertObjectToJson8(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getHelpSection());
    }
    public String convertObjectToJson9(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getProductDescription());
    }
    public String convertObjectToJson10(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getSlotConsultationDtoLP2());
    }
    public String convertObjectToJson11(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getTestimonials());
    }
    public String convertObjectToJson12(CreatorDetailsRequestLP2 createDetailsRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest2().getCallToAction());
    }
    public ResponseEntity<CreateDetailsResponseForCreatorLP2>GetCreatorPersonalieFeature1(String  userName){
        if (userName==null) {
            return new ResponseEntity<>
                    (new CreateDetailsResponseForCreatorLP2
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }
        if(creatorFeatureInfoRepository.findByUserName(userName)==null){
            return new ResponseEntity<>
                    (new CreateDetailsResponseForCreatorLP2
                            ("0",
                                    "This user does not exist in the creatureInfoDetails table", null), HttpStatus.NOT_FOUND);
        }
        CreatorFeatureInfo creatorFeatureInfo = creatorFeatureInfoRepository.findByUserName(userName);
        CreateDetailsForCreatorDtoLP2 createDetailsForCreatorDtoLP2 = new CreateDetailsForCreatorDtoLP2();
        createDetailsForCreatorDtoLP2.setSlot(creatorFeatureInfo.getSlot());
        createDetailsForCreatorDtoLP2.setLeadGenForm(creatorFeatureInfo.getLeadGenForm());
        createDetailsForCreatorDtoLP2.setMasterClass(creatorFeatureInfo.getMasterClass());
        LandingPage2Response landingPage2Response = new LandingPage2Response();
        LandingPage2 landingPage2 = landingPage2Repository.findByUserName(userName);
        try {
            String ans = landingPage2.getHeroDto2();
            HeroDto2 heroDto2= createDetailsRequestFromJsonString(ans);
            landingPage2Response.setHeroDto2(heroDto2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage2.getSubHeroDto2();
            SubHeroDto2 subHeroDto2= createDetailsRequestFromJsonString1(ans);
            landingPage2Response.setSubHeroDto2(subHeroDto2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage2.getGettingDto();
            GettingDto2 gettingDto2= createDetailsRequestFromJsonString2(ans);
            landingPage2Response.setGettingDto(gettingDto2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage2.getServicesAvail();
            ServicesAvail servicesAvail= createDetailsRequestFromJsonString3(ans);
            landingPage2Response.setServicesAvail(servicesAvail);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage2.getCharacteristicsOfService();
           CharacteristicsOfService characteristicsOfService= createDetailsRequestFromJsonString4(ans);
            landingPage2Response.setCharacteristicsOfService(characteristicsOfService);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage2.getApplySectionDtoLP2();
            ApplySectionDtoLP2 applySectionDtoLP2= createDetailsRequestFromJsonString5(ans);
            landingPage2Response.setApplySectionDtoLP2(applySectionDtoLP2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        try {
//            String ans = landingPage2.getIndividualCardsDto();
//            ApplySectionDtoLP2 applySectionDtoLP2= createDetailsRequestFromJsonString5(ans);
//            landingPage2Response.setApplySectionDtoLP2(applySectionDtoLP2);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        try {
            String ans = landingPage2.getHelpSection();
            HelpSection helpSection= createDetailsRequestFromJsonString6(ans);
            landingPage2Response.setHelpSection(helpSection);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage2.getProductDescription();
            ProductDescription productDescription= createDetailsRequestFromJsonString7(ans);
            landingPage2Response.setProductDescription(productDescription);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage2.getSlotConsultationDtoLP2();
            if(creatorFeatureInfo.getSlot()==true) {
                SlotConsultationLP2 slotConsultationLP2 = createDetailsRequestFromJsonString8(ans);
                landingPage2Response.setSlotConsultationDtoLP2(slotConsultationLP2);
            }
            else{
                landingPage2Response.setSlotConsultationDtoLP2(null);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage2.getTestimonials();
             Testimonials testimonials= createDetailsRequestFromJsonString9(ans);
            landingPage2Response.setTestimonials(testimonials);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage2.getCallToAction();
            CallToAction callToAction= createDetailsRequestFromJsonString10(ans);
            landingPage2Response.setCallToAction(callToAction);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        landingPage2Response.setLandingPage2VariantId(landingPage2.getLandingPageVariantId());
        landingPage2Response.setUserName(userName);
        List<FeedBackFeature> features = feedBackFeatureRepository.findByUserNameAndDisableOrenabled(userName,true);


        ArrayList<GetAvailabilityButtonsDto> list = new ArrayList<>();
        if(!features.isEmpty()) {
            for (int j = 0; j < features.size(); j++) {
                GetAvailabilityButtonsDto getAvailabilityButtonsDto = FeedBackFeatureMapper.converEntityToDTO(features.get(j));
                list.add(getAvailabilityButtonsDto);
            }
        }
        createDetailsForCreatorDtoLP2.setGetAvailabilityButtonsDto(list);
        createDetailsForCreatorDtoLP2.setUserId(creatorFeatureInfo.getUserId());
        createDetailsForCreatorDtoLP2.setLandingPageId(landingPage2.getLandingPageId());
        landingPage2Response.setUserId(creatorFeatureInfo.getUserId());

        landingPage2Response.setLandingPage2Id(landingPage2.getLandingPageId());
//        createDetailsForCreatorDtoLP2.setLandingPageRequest2(landingPage2Response);
        return new ResponseEntity<>(new  CreateDetailsResponseForCreatorLP2
                ("1",
                        "Landing Page data ", createDetailsForCreatorDtoLP2 ), HttpStatus.OK);





    }
    public HeroDto2 createDetailsRequestFromJsonString(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,HeroDto2.class );
    }
    public SubHeroDto2 createDetailsRequestFromJsonString1(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,SubHeroDto2.class );
    }
    public GettingDto2 createDetailsRequestFromJsonString2(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans, GettingDto2.class );
    }
    public ServicesAvail createDetailsRequestFromJsonString3(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans, ServicesAvail.class );
    }
    public CharacteristicsOfService createDetailsRequestFromJsonString4(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans, CharacteristicsOfService.class );
    }
    public ApplySectionDtoLP2 createDetailsRequestFromJsonString5(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans, ApplySectionDtoLP2.class );
    }
    public HelpSection createDetailsRequestFromJsonString6(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans, HelpSection.class );
    }
    public  ProductDescription createDetailsRequestFromJsonString7(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,  ProductDescription.class );
    }
    public SlotConsultationLP2 createDetailsRequestFromJsonString8(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans, SlotConsultationLP2.class );
    }
    public Testimonials createDetailsRequestFromJsonString9(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans, Testimonials.class );
    }
    public CallToAction createDetailsRequestFromJsonString10(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans, CallToAction.class );
    }
    @Override
    public ResponseEntity<UpdateSlotResponse>UpdateSlotButton2(String userName, UpdateSlotRequest updateSlotRequest){
        if(userName==null){
            return new ResponseEntity<>
                    (new UpdateSlotResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        CreatorFeatureInfo creatorFeatureInfo= creatorFeatureInfoRepository.findByUserName(userName);
        creatorFeatureInfo.setSlot(updateSlotRequest.getSlot());
        creatorFeatureInfoRepository.save(creatorFeatureInfo);
        return new ResponseEntity<>
                (new UpdateSlotResponse
                        ("1",
                                "The slot has been updated", null), HttpStatus.OK);

    }



}
