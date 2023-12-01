package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.MentorController.MentorSearchResponseDto;
import com.leanplatform.MentorshipPlatform.entities.Mentor;

public class MentorToMentorSearchResponseMapper {
    public static MentorSearchResponseDto convertEntityToDto(Mentor mentor){
        MentorSearchResponseDto mentorSearchResponseDto = new MentorSearchResponseDto();
        mentorSearchResponseDto.setFirstName(mentor.getFirstName());
        mentorSearchResponseDto.setLastName(mentor.getLastName());
        mentorSearchResponseDto.setPhoneNo(mentor.getPhoneNo());
        mentorSearchResponseDto.setResume(mentor.getResume());
        mentorSearchResponseDto.setEmail(mentor.getEmail());
        mentorSearchResponseDto.setLinkedIn_link(mentor.getLinkedIn_link());
        mentorSearchResponseDto.setYearsOfExperience(mentor.getYearsOfExperience());
        mentorSearchResponseDto.setLastPlaceOfEmployment(mentor.getLastPlaceOfEmployment());
        mentorSearchResponseDto.setCurrentPlaceOfEmployment(mentor.getCurrentPlaceOfEmployment());
        mentorSearchResponseDto.setPriorToLastPlaceOfEmployment(mentor.getPriorToLastPlaceOfEmployment());
        mentorSearchResponseDto.setPrioritySkill(mentor.getPrioritySkill());

        return mentorSearchResponseDto;
    }


}
