package com.leanplatform.MentorshipPlatform.services.implementation.LandingPageFeatureServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.*;
import com.leanplatform.MentorshipPlatform.entities.CreatorCustomDomain;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.GetAvailabilityButtonsDto;
import com.leanplatform.MentorshipPlatform.repositories.CreatorCustomDomainRepository;
import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.BelowApplySection;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.GetAvailabilityButtonsDto;
import com.leanplatform.MentorshipPlatform.entities.CoursesOfMentor.Courses;
import com.leanplatform.MentorshipPlatform.entities.FeedBackFeatureWhole.FeedBackFeature;
import com.leanplatform.MentorshipPlatform.entities.LandingPage.CreatorFeatureInfo;
import com.leanplatform.MentorshipPlatform.entities.LandingPage.LandingPage1;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.UserEntity;
import com.leanplatform.MentorshipPlatform.mappers.CourseFeatureMapper.CoursesMapper;
import com.leanplatform.MentorshipPlatform.mappers.FeadBackFeatureFunctionalityMapper.FeedBackFeatureMapper;
import com.leanplatform.MentorshipPlatform.repositories.CoursesFeatureRepository.CoursesRepository;
import com.leanplatform.MentorshipPlatform.repositories.FeedBackFeatureWholeRepository.FeedBackFeatureRepository;
import com.leanplatform.MentorshipPlatform.repositories.LandingPageFeatureRepository.CreatorFeatureInfoRepository;
import com.leanplatform.MentorshipPlatform.repositories.LandingPageFeatureRepository.LandingPage1Repository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.UserRepository;
import com.leanplatform.MentorshipPlatform.services.LandingPageFeatureService.CreatorFeatureInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreatorFeatureInfoServiceImpl implements CreatorFeatureInfoService {

    @Autowired
    CreatorFeatureInfoRepository creatorFeatureInfoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CoursesRepository coursesRepository;
    @Autowired
    LandingPage1Repository landingPageRepository;
    @Autowired
    FeedBackFeatureRepository feedBackFeatureRepository;

    @Autowired
    CreatorCustomDomainRepository customDomainRepository;

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
       UserEntity userEntity= userRepository.findByUserName(userName);
        if(userEntity==null){
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "This user does not exist in db", null), HttpStatus.BAD_REQUEST);

        }
        if(creatorFeatureInfoRepository.findByUserName(userName)!=null){
            return new ResponseEntity<>
                        (new CreateDetailsForCreatorResponse
                                ("0",
                                        "This user already has a landing page made in the db", null), HttpStatus.BAD_REQUEST);


            }



        CreatorFeatureInfo creatorFeatureInfo = new CreatorFeatureInfo();
        creatorFeatureInfo.setUserName(userName);
        // UserEntity userEntity = userRepository.findByUserName(userName);
        //  creatorFeatureInfo.setUserId(userEntity.getUserId());

        LandingPage1 landingPage = new LandingPage1();
        landingPage.setUserName(userName);
        creatorFeatureInfo.setLeadGenForm(createDetailsRequest.getLeadGenForm());
        creatorFeatureInfo.setMasterClass(createDetailsRequest.getMasterClass());
        creatorFeatureInfo.setSlot(createDetailsRequest.getSlot());
        creatorFeatureInfo.setUserId(userEntity.getUserId());

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
        creatorFeatureInfo.setUserId(userEntity.getUserId());
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


    public ResponseEntity<CreateDetailsForCreatorResponse> GetCreatorPersonalieFeature(String userName,Boolean flag) {
        if (userName == null) {
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "Invalid Request", null), HttpStatus.BAD_REQUEST);


        }
        if(flag.equals(true)){

            CreatorCustomDomain customDomain=customDomainRepository.getByDomain(userName);
            if(customDomain==null){
                return new ResponseEntity<>
                        (new CreateDetailsForCreatorResponse
                                ("0",
                                        "This user does not exist in creatureFeatureInfo", null), HttpStatus.NOT_FOUND);
            }

            userName=customDomain.getUsername();
        }
        if(creatorFeatureInfoRepository.findByUserName(userName)==null){
            return new ResponseEntity<>
                    (new CreateDetailsForCreatorResponse
                            ("0",
                                    "This user does not exist in creatureFeatureInfo", null), HttpStatus.NOT_FOUND);
        }

        CreatorFeatureInfo creatorFeatureInfo = creatorFeatureInfoRepository.findByUserName(userName);
        List<Courses> courses=coursesRepository.findByUserNameAndIsEnabled(userName,true);
        List<AddCoursesResponseDTO>ans=new ArrayList<>();
        for(int i=0;i<courses.size();i++){
            AddCoursesResponseDTO addCoursesResponseDTO=  CoursesMapper.convertEntityToDto(courses.get(i));
            ans.add(addCoursesResponseDTO);

        }


        CreateDetailsForCreatorDto createDetailsForCreatorDto = new CreateDetailsForCreatorDto();
        createDetailsForCreatorDto.setSlot(creatorFeatureInfo.getSlot());
        createDetailsForCreatorDto.setLeadGenForm(creatorFeatureInfo.getLeadGenForm());
        createDetailsForCreatorDto.setAddCoursesResponseDTO(ans);
        createDetailsForCreatorDto.setMasterClass(creatorFeatureInfo.getMasterClass());
        LandingPageResponse landingPageResponse = new LandingPageResponse();
        LandingPage1 landingPage = landingPageRepository.findByUserName(userName);
//        try {
//            String ans = landingPage.getHeroDto();
//           HeroDto heroDto= createDetailsRequestFromJsonString(ans);
//           landingPageResponse.setHeroDto(heroDto);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            String ans = landingPage.getApplySectionDto();
//            ApplySectionDto applySectionDto= createDetailsRequestFromJsonString1(ans);
//            landingPageResponse.setApplySectionDto(applySectionDto);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            String ans = landingPage.getIndividualCardsDto();
//            IndividualCardsDto individualCardsDto= createDetailsRequestFromJsonString2(ans);
//            landingPageResponse.setIndividualCardsDto(individualCardsDto);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            String ans = landingPage.getFinalGoDto();
//            FinalGoDto finalGoDto= createDetailsRequestFromJsonString4(ans);
//            landingPageResponse.setFinalGoDto(finalGoDto);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            String ans = landingPage.getSlotConsultationDto();
//            if(creatorFeatureInfo.getSlot()==true) {
//                SlotConsultation slotConsultation = createDetailsRequestFromJsonString5(ans);
//                landingPageResponse.setSlotConsultationDto(slotConsultation);
//            }
//            else{
//                landingPageResponse.setSlotConsultationDto(null);
//            }
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            String ans = landingPage.getHelpDto();
//            HelpDto helpDto= createDetailsRequestFromJsonString6(ans);
//            landingPageResponse.setHelpDto(helpDto);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            String ans = landingPage.getLearningDto2();
//            LearningDto2 learningDto2= createDetailsRequestFromJsonString7(ans);
//            landingPageResponse.setLearningDto2(learningDto2);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            String ans = landingPage.getLearningDto();
//            LearningDto learningDto= createDetailsRequestFromJsonString8(ans);
//            landingPageResponse.setLearningDto(learningDto);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            String ans = landingPage.getBelowApplySection();
//            BelowApplySection belowApplySection= createDetailsRequestFromJsonString9(ans);
//            landingPageResponse.setBelowApplySection(belowApplySection);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            String ans = landingPage.getIndividualCardsDto2();
//            IndividualCardsDto2 individualCardsDto2= createDetailsRequestFromJsonString10(ans);
//            landingPageResponse.setIndividualCardsDto2(individualCardsDto2);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            String ans = landingPage.getSubHeroDto();
//           SubHeroDto subHeroDto= createDetailsRequestFromJsonString11(ans);
//            landingPageResponse.setSubHeroDto(subHeroDto);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        //landingPageResponse.setLandingPageVariantId(landingPage.getLandingPageVariantId());
        landingPageResponse.setUserName(userName);
//        createDetailsForCreatorDto.setUserId(creatorFeatureInfo.getUserId());
//        createDetailsForCreatorDto.setLandingPageId(landingPage.getLandingPageId());
        landingPageResponse.setUserId(creatorFeatureInfo.getUserId());
        landingPageResponse.setLandingPageId(landingPage.getLandingPageId());
         createDetailsForCreatorDto.setLandingPageResponse(landingPageResponse);
        List<FeedBackFeature> features = feedBackFeatureRepository.findByUserNameAndDisableOrenabled(userName,true);


        ArrayList<GetAvailabilityButtonsDto> list = new ArrayList<>();
        if(!features.isEmpty()) {
            for (int j = 0; j < features.size(); j++) {
                GetAvailabilityButtonsDto getAvailabilityButtonsDto = FeedBackFeatureMapper.converEntityToDTO(features.get(j));
                list.add(getAvailabilityButtonsDto);
            }
        }
        createDetailsForCreatorDto.setFeedBackEnabled(list);






//        landingPageResponse.setApplySectionDto(landingPage.getApplySectionDto());
//        landingPageResponse.setIndividualCardsDto(landingPage.getIndividualCardsDto());
//        landingPageResponse.setLearningDto(landingPage.getLearningDto());
//        landingPageResponse.setBelowApplySection(landingPage.getBelowApplySection());
//        landingPageResponse.setIndividualCardsDto2(landingPage.getIndividualCardsDto2());
//        landingPageResponse.setLearningDto2(landingPage.getLearningDto2());
//        landingPageResponse.setFinalGoDto(landingPage.getFinalGoDto());
//        landingPageResponse.setSlotConsultationDto(landingPage.getSlotConsultationDto());
//        landingPageResponse.setHelpDto(landingPageResponse.getHelpDto());
//        createDetailsForCreatorDto.setLandingPageResponse(landingPageResponse);
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
    public ResponseEntity<UpdateSlotResponse>UpdateSlotButton(String userName,UpdateSlotRequest updateSlotRequest){
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
