package com.leanplatform.MentorshipPlatform.mappers.MentorMapper;

import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestObject;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.MentorRequest;

public class MentorMapper {

    public static MentorRequest convertDtoToEntity(MentorRequestObject mentorRequestObject){
        MentorRequest newMentorRequest = new MentorRequest();
        newMentorRequest.setFirstName(mentorRequestObject.getFirstName());
        newMentorRequest.setLastName(mentorRequestObject.getLastName());
        newMentorRequest.setPhoneNo(mentorRequestObject.getPhoneNo());
        newMentorRequest.setEmail(mentorRequestObject.getEmail());

        return newMentorRequest;
    }

}
