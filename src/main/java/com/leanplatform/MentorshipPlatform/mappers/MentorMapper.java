package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestObject;
import com.leanplatform.MentorshipPlatform.entities.MentorRequest;

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
