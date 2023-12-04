package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.OverallStats.ActiveMentorsResponse;
import com.leanplatform.MentorshipPlatform.dto.OverallStats.ActiveMentorsResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.OverallStats.RegisteredMentorsResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.Mentor;
import com.leanplatform.MentorshipPlatform.entities.MentorRequest;

import java.util.ArrayList;
import java.util.List;

public class StatsMapper {
    public static List<RegisteredMentorsResponseDTO> convertEntityToDto(List<MentorRequest> registeredMentors) {
        List<RegisteredMentorsResponseDTO> registeredMentorsResponseDTOs =new ArrayList<>();


        for (int i = 0; i < registeredMentors.size(); i++) {
            MentorRequest mentor = registeredMentors.get(i);

            RegisteredMentorsResponseDTO responseDTO = new RegisteredMentorsResponseDTO();

            // Set values from MentorRequest entity to RegisteredMentorsResponseDTO
            responseDTO.setFirstName(mentor.getFirstName());
            responseDTO.setLastName(mentor.getLastName());
            responseDTO.setPhoneNo(mentor.getPhoneNo());
            responseDTO.setResume(mentor.getResume());
            responseDTO.setEmail(mentor.getEmail());
            responseDTO.setLinkedIn_link(mentor.getLinkedIn_link());
            responseDTO.setYearsOfExperience(mentor.getYearsOfExperience());

            // Add the converted DTO to the list
            registeredMentorsResponseDTOs.add(responseDTO);
        }


        return registeredMentorsResponseDTOs;
    }
    public static List<ActiveMentorsResponseDTO> convertEntityToDtoActive(List<Mentor> activeMentors){
        List<ActiveMentorsResponseDTO> activeMentorsResponseDTOs =new ArrayList<>();
        for (int i = 0; i < activeMentors.size(); i++) {
            Mentor mentor = activeMentors.get(i);

            ActiveMentorsResponseDTO responseDTO = new ActiveMentorsResponseDTO();

            // Set values from MentorRequest entity to RegisteredMentorsResponseDTO
            responseDTO.setFirstName(mentor.getFirstName());
            responseDTO.setLastName(mentor.getLastName());
            responseDTO.setPhoneNo(mentor.getPhoneNo());
            responseDTO.setResume(mentor.getResume());
            responseDTO.setEmail(mentor.getEmail());
            responseDTO.setLinkedIn_link(mentor.getLinkedIn_link());
            responseDTO.setYearsOfExperience(mentor.getYearsOfExperience());
            responseDTO.setEducationQualification(mentor.getEducationQualification());
            responseDTO.setOverAllRating(mentor.getOverAllRating());

            // Add the converted DTO to the list
            activeMentorsResponseDTOs.add(responseDTO);
        }


        return activeMentorsResponseDTOs;
    }

}
