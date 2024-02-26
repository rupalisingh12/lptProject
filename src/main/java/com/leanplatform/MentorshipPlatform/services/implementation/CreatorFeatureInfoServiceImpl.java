package com.leanplatform.MentorshipPlatform.services.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.*;
import com.leanplatform.MentorshipPlatform.entities.CreatorFeatureInfo;
import com.leanplatform.MentorshipPlatform.entities.LandingPage;
import com.leanplatform.MentorshipPlatform.entities.UserEntity;
import com.leanplatform.MentorshipPlatform.repositories.CreatorFeatureInfoRepository;
import com.leanplatform.MentorshipPlatform.repositories.HeroSectionRepository;
import com.leanplatform.MentorshipPlatform.repositories.LandingPageRepository;
import com.leanplatform.MentorshipPlatform.repositories.UserRepository;
import com.leanplatform.MentorshipPlatform.services.CreatorFeatureInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CreatorFeatureInfoServiceImpl implements CreatorFeatureInfoService {

    @Autowired
    CreatorFeatureInfoRepository creatorFeatureInfoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LandingPageRepository landingPageRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseEntity<CreateDetailsForCreatorResponse> AddCreatorPersonalieFeature(String userName, CreateDetailsRequest createDetailsRequest) {
        if (createDetailsRequest == null ||
                createDetailsRequest.getMasterClass() == null || createDetailsRequest.getLeadGenForm() == null ||
                createDetailsRequest.getSlot() == null || createDetailsRequest.getLandingPageRequest() == null) {
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);

        }

        CreatorFeatureInfo creatorFeatureInfo = new CreatorFeatureInfo();
        creatorFeatureInfo.setUserName(userName);
        // UserEntity userEntity = userRepository.findByUserName(userName);
        //  creatorFeatureInfo.setUserId(userEntity.getUserId());

        LandingPage landingPage = new LandingPage();
        landingPage.setUserName(userName);
        creatorFeatureInfo.setLeadGenForm(createDetailsRequest.getLeadGenForm());
        creatorFeatureInfo.setMasterClass(createDetailsRequest.getMasterClass());
        creatorFeatureInfo.setSlot(createDetailsRequest.getSlot());

        try {
            String json = convertObjectToJson(createDetailsRequest);
            landingPage.setHeroDto(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson1(createDetailsRequest);
            landingPage.setSubHeroDto(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson2(createDetailsRequest);
            landingPage.setApplySectionDto(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson3(createDetailsRequest);
            landingPage.setBelowApplySection(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson4(createDetailsRequest);
            landingPage.setIndividualCardsDto(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson5(createDetailsRequest);
            landingPage.setIndividualCardsDto2(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson6(createDetailsRequest);
            landingPage.setLearningDto(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson7(createDetailsRequest);
            landingPage.setLearningDto2(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson8(createDetailsRequest);
            landingPage.setSlotConsultationDto(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson9(createDetailsRequest);
            landingPage.setFinalGoDto(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String json = convertObjectToJson10(createDetailsRequest);
            landingPage.setHelpDto(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        landingPage.setLandingPageVariantId(createDetailsRequest.getLandingPageRequest().getLandingPageVariantId());
        landingPage.setLandingPageId(createDetailsRequest.getLandingPageRequest().getLandingPageId());
        landingPageRepository.save(landingPage);
        creatorFeatureInfoRepository.save(creatorFeatureInfo);


        return new ResponseEntity<>(new CreateDetailsForCreatorResponse
                ("1",
                        "Landing Page data saved", null), HttpStatus.OK);

    }


    public String convertObjectToJson(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getHeroDto());
    }

    public String convertObjectToJson1(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getSubHeroDto());
    }

    public String convertObjectToJson2(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getApplySectionDto());
    }

    public String convertObjectToJson3(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getBelowApplySection());
    }

    public String convertObjectToJson4(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getIndividualCardsDto());
    }

    public String convertObjectToJson5(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getIndividualCardsDto2());
    }

    public String convertObjectToJson6(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getLearningDto());
    }

    public String convertObjectToJson7(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getLearningDto2());
    }

    public String convertObjectToJson8(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getSlotConsultationDto());
    }

    public String convertObjectToJson9(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getFinalGoDto());

    }
    public String convertObjectToJson10(CreateDetailsRequest createDetailsRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(createDetailsRequest.getLandingPageRequest().getHelpDto());
    }


    public ResponseEntity<CreateDetailsForCreatorResponse> GetCreatorPersonalieFeature(String userName) {
        if (userName == null) {
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);


        }
        CreatorFeatureInfo creatorFeatureInfo = creatorFeatureInfoRepository.findByUserName(userName);
        CreateDetailsForCreatorDto createDetailsForCreatorDto = new CreateDetailsForCreatorDto();
        createDetailsForCreatorDto.setSlot(creatorFeatureInfo.getSlot());
        createDetailsForCreatorDto.setLeadGenForm(creatorFeatureInfo.getLeadGenForm());
        createDetailsForCreatorDto.setMasterClass(creatorFeatureInfo.getMasterClass());
        LandingPageResponse landingPageResponse = new LandingPageResponse();
        LandingPage landingPage = landingPageRepository.findByUserName(userName);
        try {
            String ans = landingPage.getHeroDto();
           HeroDto heroDto= createDetailsRequestFromJsonString(ans);
           landingPageResponse.setHeroDto(heroDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage.getApplySectionDto();
            ApplySectionDto applySectionDto= createDetailsRequestFromJsonString1(ans);
            landingPageResponse.setApplySectionDto(applySectionDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage.getIndividualCardsDto();
            IndividualCardsDto individualCardsDto= createDetailsRequestFromJsonString2(ans);
            landingPageResponse.setIndividualCardsDto(individualCardsDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try {
            String ans = landingPage.getFinalGoDto();
            FinalGoDto finalGoDto= createDetailsRequestFromJsonString4(ans);
            landingPageResponse.setFinalGoDto(finalGoDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage.getSlotConsultationDto();
            if(creatorFeatureInfo.getSlot()==true) {
                SlotConsultation slotConsultation = createDetailsRequestFromJsonString5(ans);
                landingPageResponse.setSlotConsultationDto(slotConsultation);
            }
            else{
                landingPageResponse.setSlotConsultationDto(null);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage.getHelpDto();
            HelpDto helpDto= createDetailsRequestFromJsonString6(ans);
            landingPageResponse.setHelpDto(helpDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage.getLearningDto2();
            LearningDto2 learningDto2= createDetailsRequestFromJsonString7(ans);
            landingPageResponse.setLearningDto2(learningDto2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage.getLearningDto();
            LearningDto learningDto= createDetailsRequestFromJsonString8(ans);
            landingPageResponse.setLearningDto(learningDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage.getBelowApplySection();
            BelowApplySection belowApplySection= createDetailsRequestFromJsonString9(ans);
            landingPageResponse.setBelowApplySection(belowApplySection);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage.getIndividualCardsDto2();
            IndividualCardsDto2 individualCardsDto2= createDetailsRequestFromJsonString10(ans);
            landingPageResponse.setIndividualCardsDto2(individualCardsDto2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            String ans = landingPage.getSubHeroDto();
           SubHeroDto subHeroDto= createDetailsRequestFromJsonString11(ans);
            landingPageResponse.setSubHeroDto(subHeroDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        landingPageResponse.setLandingPageVariantId(landingPage.getLandingPageVariantId());
        landingPageResponse.setUserName(userName);
        landingPageResponse.setLandingPageId(landingPage.getLandingPageId());






//        landingPageResponse.setApplySectionDto(landingPage.getApplySectionDto());
//        landingPageResponse.setIndividualCardsDto(landingPage.getIndividualCardsDto());
//        landingPageResponse.setLearningDto(landingPage.getLearningDto());
//        landingPageResponse.setBelowApplySection(landingPage.getBelowApplySection());
//        landingPageResponse.setIndividualCardsDto2(landingPage.getIndividualCardsDto2());
//        landingPageResponse.setLearningDto2(landingPage.getLearningDto2());
//        landingPageResponse.setFinalGoDto(landingPage.getFinalGoDto());
//        landingPageResponse.setSlotConsultationDto(landingPage.getSlotConsultationDto());
//        landingPageResponse.setHelpDto(landingPageResponse.getHelpDto());
        createDetailsForCreatorDto.setLandingPageResponse(landingPageResponse);
        return new ResponseEntity<>(new CreateDetailsForCreatorResponse
                ("1",
                        "Landing Page data saved", createDetailsForCreatorDto), HttpStatus.OK);

    }

    public HeroDto createDetailsRequestFromJsonString(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,HeroDto.class );
    }
    public ApplySectionDto createDetailsRequestFromJsonString1(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,ApplySectionDto.class );
    }
    public IndividualCardsDto createDetailsRequestFromJsonString2(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,IndividualCardsDto.class );
    }
    public LearningDto createDetailsRequestFromJsonString3(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,LearningDto.class );
    }
    public FinalGoDto createDetailsRequestFromJsonString4(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,FinalGoDto.class );
    }
    public SlotConsultation createDetailsRequestFromJsonString5(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,SlotConsultation.class );
    }
    public HelpDto createDetailsRequestFromJsonString6(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,HelpDto.class );
    }
    public LearningDto2 createDetailsRequestFromJsonString7(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,LearningDto2.class );
    }
    public LearningDto createDetailsRequestFromJsonString8(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,LearningDto.class );
    }
    public BelowApplySection createDetailsRequestFromJsonString9(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,BelowApplySection.class );
    }
    public IndividualCardsDto2 createDetailsRequestFromJsonString10(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,IndividualCardsDto2.class );
    }
    public SubHeroDto createDetailsRequestFromJsonString11(String ans) throws JsonProcessingException {
        return objectMapper.readValue(ans,SubHeroDto.class );
    }






}
