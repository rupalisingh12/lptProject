package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.OverallStats.*;
import com.leanplatform.MentorshipPlatform.entities.Mentee;
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
    public static List<RegisteredDoneMentorsResponseDTO> convertEntityToDtoSession(List<Mentor>mentorDoneSessions) {
        List<RegisteredDoneMentorsResponseDTO> sessionDoneMentorsResponseDTOS = new ArrayList<>();
        for (int i = 0; i < mentorDoneSessions.size(); i++) {
            Mentor mentor = mentorDoneSessions.get(i);
            RegisteredDoneMentorsResponseDTO responseDTO = new RegisteredDoneMentorsResponseDTO();
            responseDTO.setFirstName(mentor.getFirstName());
            responseDTO.setLastName(mentor.getLastName());
            responseDTO.setPhoneNo(mentor.getPhoneNo());
            responseDTO.setResume(mentor.getResume());
            responseDTO.setEmail(mentor.getEmail());
            responseDTO.setLinkedIn_link(mentor.getLinkedIn_link());
            responseDTO.setYearsOfExperience(mentor.getYearsOfExperience());
            responseDTO.setEducationQualification(mentor.getEducationQualification());
            responseDTO.setOverAllRating(mentor.getOverAllRating());
            sessionDoneMentorsResponseDTOS.add(responseDTO);

        }
        return sessionDoneMentorsResponseDTOS;
    }
    public static List<RegisteredMenteeDTOResponse>convertEntityToDTOmentee(List<Mentee>mentee){
        List<RegisteredMenteeDTOResponse> registeredMentorsResponseDTOS = new ArrayList<>();
        for (int i = 0; i < mentee.size(); i++) {
            Mentee mentee1 = mentee.get(i);
            RegisteredMenteeDTOResponse responseDTO = new RegisteredMenteeDTOResponse();
            responseDTO.setFirstName(mentee1.getFirstName());
            responseDTO.setLastName(mentee1.getLastName());
            responseDTO.setPhoneNo(mentee1.getPhoneNo());
           // responseDTO.setResume(mentee1.getResume());
            responseDTO.setEmail(mentee1.getEmail());
            responseDTO.setAge(mentee1.getAge());
           // responseDTO.setLinkedIn_link(mentee1.getLinkedIn_link());
            //responseDTO.setYearsOfExperience(mentee1.getYearsOfExperience());
            responseDTO.setEducationalQualification(mentee1.getEducationalQualification());
          //  responseDTO.setOverAllRating(mentee1.getOverAllRating());
            registeredMentorsResponseDTOS.add(responseDTO);

        }
        return registeredMentorsResponseDTOS;
    }
    public static List<SessionDoneMenteeResponseDTO>convertEntityToDTOmenteeSessionDone(List<Mentee>mentee){
        List<SessionDoneMenteeResponseDTO> sessionDoneMenteeResponseDTOS = new ArrayList<>();
        for (int i = 0; i < mentee.size(); i++) {
            Mentee mentee1 = mentee.get(i);
            SessionDoneMenteeResponseDTO responseDTO = new SessionDoneMenteeResponseDTO();
            responseDTO.setFirstName(mentee1.getFirstName());
            responseDTO.setLastName(mentee1.getLastName());
            responseDTO.setPhoneNo(mentee1.getPhoneNo());
            // responseDTO.setResume(mentee1.getResume());
            responseDTO.setEmail(mentee1.getEmail());
            responseDTO.setAge(mentee1.getAge());
            // responseDTO.setLinkedIn_link(mentee1.getLinkedIn_link());
            //responseDTO.setYearsOfExperience(mentee1.getYearsOfExperience());
            responseDTO.setEducationalQualification(mentee1.getEducationalQualification());
            //  responseDTO.setOverAllRating(mentee1.getOverAllRating());
            sessionDoneMenteeResponseDTOS.add(responseDTO);

        }
        return sessionDoneMenteeResponseDTOS;
    }

    public static List<TotalSessionResponseDTO> convertdtoTodto(List<ServiceSessionCountDTO> serviceSessionCounts) {
        List<TotalSessionResponseDTO> totalSessionResponsedto = new ArrayList<>();

        for (int i = 0; i < serviceSessionCounts.size(); i++) {
            ServiceSessionCountDTO dto = serviceSessionCounts.get(i);
            TotalSessionResponseDTO totalSessionResponseDTO=new TotalSessionResponseDTO();
            totalSessionResponseDTO.setServiceOffered(dto.getServiceOffered());
            totalSessionResponseDTO.setSessionCount(dto.getSessionCount());
            totalSessionResponsedto.add(totalSessionResponseDTO);
        }

        // You might have other logic to set additional properties in TotalSessionResponse

        return totalSessionResponsedto;
    }

//


}



