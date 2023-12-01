package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.AdminController.AddFurtherDetails;
import com.leanplatform.MentorshipPlatform.dto.AdminController.AdminAddsDetailsResponse;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.DeleteMentorRequestObject;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestDeletedResponse;
import com.leanplatform.MentorshipPlatform.dto.MentorController.*;
import com.leanplatform.MentorshipPlatform.entities.Mentor;
import com.leanplatform.MentorshipPlatform.entities.MentorRequest;
import com.leanplatform.MentorshipPlatform.entities.ServicesByMentors;
import com.leanplatform.MentorshipPlatform.entities.ServicesOffered;
import com.leanplatform.MentorshipPlatform.mappers.MentorToMentorSearchResponseMapper;
import com.leanplatform.MentorshipPlatform.mappers.ServicesByMentorsMapper;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository;
import com.leanplatform.MentorshipPlatform.repositories.ServicesByMentorsRepository;
import com.leanplatform.MentorshipPlatform.repositories.ServicesOfferedRepository;
import com.leanplatform.MentorshipPlatform.services.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MentorServiceImpl implements MentorService {

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private ServicesByMentorsRepository servicesByMentorsRepository;

    @Autowired
    private ServicesOfferedRepository servicesOfferedRepository;

    @Override
    public ResponseEntity<AdminAddsDetailsResponse> addAdditional(AddFurtherDetails furtherDetails) {
        if (furtherDetails == null ||
                furtherDetails.getMentor_id() == null ||
                furtherDetails.getPrioritySkill() == null ||
                furtherDetails.getCurrentPlaceOfEmployment() == null ||
                furtherDetails.getLastPlaceOfEmployment() == null ||
                furtherDetails.getPriorToLastPlaceOfEmployment() == null) {
            return new ResponseEntity<>(new AdminAddsDetailsResponse
                    (
                            "0",
                            "Failed to Update data : caught inside Null check on incoming Request object",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }

        Optional<Mentor> mentorOptional = mentorRepository.findById(furtherDetails.getMentor_id());

        if (mentorOptional.isPresent()) {
            Mentor existingMentor = mentorOptional.get();
            existingMentor.setLastPlaceOfEmployment(furtherDetails.getLastPlaceOfEmployment());
            existingMentor.setCurrentPlaceOfEmployment(furtherDetails.getCurrentPlaceOfEmployment());
            existingMentor.setPriorToLastPlaceOfEmployment(furtherDetails.getPriorToLastPlaceOfEmployment());
            existingMentor.setPrioritySkill(furtherDetails.getPrioritySkill());
            existingMentor.setEducationQualification(furtherDetails.getEducationQualification());
            mentorRepository.save(existingMentor);
        } else {
            return new ResponseEntity<>(new AdminAddsDetailsResponse
                    (
                            "0",
                            "Invalid Mentor id no record found",
                            null
                    ), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new AdminAddsDetailsResponse
                (
                        "1",
                        "Additional Details added successfully !",
                        furtherDetails
                ), HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<List<MentorSearchResponseDto>> sortMentorsByYOE() {
        return null;
    }

    @Override
    public ResponseEntity<List<MentorSearchResponseDto>> getAllMentors() {
        return null;
    }

    public ResponseEntity<MentorSearchResponseObject> searchMentors(SearchCriteria criteria) {
        if (criteria == null ||
                criteria.getCompanyName() == null) {
            return new ResponseEntity<>(new MentorSearchResponseObject
                    (
                            "0",
                            "Failed the null check upon method call",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
//        ServicesOffered servicesOffered = servicesOfferedRepository.findByServiceOffered(criteria.getServiceNeeded());
//        UUID serviceId = null;
//        if (servicesOffered != null){
//            serviceId = servicesOffered.getService_id();
//        }
        List<Mentor> mentors = mentorRepository.getByCriteria(
                criteria.getMentorshipRole(),
                criteria.getCompanyName(),
                criteria.getServiceNeeded(),
                criteria.getYearsOfExperience()
        );

        if (mentors.isEmpty()){
            return new ResponseEntity<>(new MentorSearchResponseObject
                    (
                            "1",
                            "Currently There are no mentors with the requested filter.",
                            null
                    ), HttpStatus.OK);
        }

        List<MentorSearchResponseDto> mentorSearchResponseDtos = new ArrayList<>();
        List<MentorSearchResponseDto> current = new ArrayList<>();
        List<MentorSearchResponseDto> last = new ArrayList<>();
        List<MentorSearchResponseDto> priorTolast = new ArrayList<>();
        for (Mentor mentor : mentors) {
            MentorSearchResponseDto mentorSearchResponseDto = (MentorToMentorSearchResponseMapper.convertEntityToDto(mentor));
            if (mentorSearchResponseDto.getCurrentPlaceOfEmployment().equals(criteria.getCompanyName())){
                current.add(mentorSearchResponseDto);
            } else if (mentorSearchResponseDto.getLastPlaceOfEmployment().equals(criteria.getCompanyName())) {
                last.add(mentorSearchResponseDto);
            }else {
                priorTolast.add(mentorSearchResponseDto);
            }
        }
//        for (MentorSearchResponseDto mentorSearchResponseDto : mentorSearchResponseDtos){
//            if (mentorSearchResponseDto.getCurrentPlaceOfEmployment().equals(criteria.getCompanyName())){
//                current.add(mentorSearchResponseDto);
//            } else if (mentorSearchResponseDto.getLastPlaceOfEmployment().equals(criteria.getCompanyName())) {
//                last.add(mentorSearchResponseDto);
//            }else {
//                priorTolast.add(mentorSearchResponseDto);
//            }
//        }
//        mentorSearchResponseDtos.clear();
        mentorSearchResponseDtos.addAll(current);
        mentorSearchResponseDtos.addAll(last);
        mentorSearchResponseDtos.addAll(priorTolast);
//        for(MentorSearchResponseDto mentorSearchResponseDto : current){
//            mentorSearchResponseDtos.add(mentorSearchResponseDto);
//        }
//        for(MentorSearchResponseDto mentorSearchResponseDto : last){
//            mentorSearchResponseDtos.add(mentorSearchResponseDto);
//        }
//        for(MentorSearchResponseDto mentorSearchResponseDto : priorTolast){
//            mentorSearchResponseDtos.add(mentorSearchResponseDto);
//        }


        return new ResponseEntity<>(new MentorSearchResponseObject
                (
                        "1",
                        "Successfully sent the list of mentors.",
                        mentorSearchResponseDtos
                ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MentorUpdatesAccountResponse> updateMentorObject(MentorUpdatesAccount mentorUpdatesAccount) {
        if (mentorUpdatesAccount == null) {
            return new ResponseEntity<>(new MentorUpdatesAccountResponse
                    (
                            "0",
                            "Invalid request - Null object received."
                    ), HttpStatus.CREATED);
        }
        Optional<Mentor> mentorOptional = mentorRepository.findById(mentorUpdatesAccount.getMentor_id());
        if (mentorOptional.isPresent()) {
            Mentor existingMentor = mentorOptional.get();

            if (mentorUpdatesAccount.getResume() != null) {
                existingMentor.setResume(mentorUpdatesAccount.getResume());
            }
            if (mentorUpdatesAccount.getLinkedIn_link() != null) {
                existingMentor.setLinkedIn_link(mentorUpdatesAccount.getLinkedIn_link());
            }
            if (mentorUpdatesAccount.getYearsOfExperience() != null) {
                existingMentor.setYearsOfExperience(mentorUpdatesAccount.getYearsOfExperience());
            }
            if (mentorUpdatesAccount.getMentorRoleApplied() != null) {
                existingMentor.setMentorRoleApplied(mentorUpdatesAccount.getMentorRoleApplied());
            }
            if (mentorUpdatesAccount.getLastPlaceOfEmployment() != null) {
                existingMentor.setLastPlaceOfEmployment(mentorUpdatesAccount.getLastPlaceOfEmployment());
            }
            if (mentorUpdatesAccount.getCurrentPlaceOfEmployment() != null) {
                existingMentor.setCurrentPlaceOfEmployment(mentorUpdatesAccount.getCurrentPlaceOfEmployment());
            }
            if (mentorUpdatesAccount.getPriorToLastPlaceOfEmployment() != null) {
                existingMentor.setPriorToLastPlaceOfEmployment(mentorUpdatesAccount.getPriorToLastPlaceOfEmployment());
            }
            if (mentorUpdatesAccount.getPrioritySkill() != null) {
                existingMentor.setPrioritySkill(mentorUpdatesAccount.getPrioritySkill());
            }

            mentorRepository.save(existingMentor);
        }else {
            return new ResponseEntity<>(new MentorUpdatesAccountResponse
                    (
                            "0",
                            "Failed to update Mentor Account"
                    ), HttpStatus.CREATED);
        }


        return new ResponseEntity<>(new MentorUpdatesAccountResponse
                (
                        "1",
                        "Mentor Account Object Updated Successfully"
                ), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DisplayMentorResponse> displayMentor(DisplayMentorObject displayMentorObject) {
        if (displayMentorObject == null ||
                displayMentorObject.getMentor_id() == null) {
            return new ResponseEntity<>(new DisplayMentorResponse
                    (
                            "0",
                            "Null Object Fetched",
                            null,
                            null,
                            null,
                            null,
                            null
                    ), HttpStatus.BAD_REQUEST);

        }
        Optional<Mentor> optionalMentor = mentorRepository.findById(displayMentorObject.getMentor_id());
        DisplayMentorResponse displayMentorResponse = new DisplayMentorResponse();
        if (optionalMentor.isPresent()){
            Mentor existingMentor = optionalMentor.get();
            displayMentorResponse.setStatusCode("1");
            displayMentorResponse.setResponseMessage("Mentor Page displayed Successfully !");
            displayMentorResponse.setFirstName(existingMentor.getFirstName());
            displayMentorResponse.setLastName(existingMentor.getLastName());
            displayMentorResponse.setMentorRoleApplied(existingMentor.getMentorRoleApplied());
            displayMentorResponse.setCurrentPlaceOfEmployment(existingMentor.getCurrentPlaceOfEmployment());
            List<ServicesByMentors> servicesByMentors = servicesByMentorsRepository.getDesiredMentorServices(displayMentorObject.getMentor_id());
            List<ServicesByMentorDto> servicesByMentorDtos = ServicesByMentorsMapper.convertEntityToDto(servicesByMentors);
            displayMentorResponse.setServicesByMentorsDto(servicesByMentorDtos);
        }else {
            return new ResponseEntity<>(new DisplayMentorResponse
                    (
                            "0",
                            "Invalid Mentor ID",
                            null,
                            null,
                            null,
                            null,
                            null
                    ), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(displayMentorResponse, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<MentorRequestDeletedResponse> deleteMentor(DeleteMentorRequestObject deleteMentorRequestObject) {
        if (deleteMentorRequestObject == null ||
                deleteMentorRequestObject.getMentorId() == null ){

            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "0",
                            "Invalid Object - Null Object received"
                    ),HttpStatus.BAD_REQUEST);
        }
        Optional<Mentor> optionalMentor = mentorRepository.findById(deleteMentorRequestObject.getMentorId());
        if (optionalMentor.isPresent()){
            Mentor existingMentor = optionalMentor.get();
            mentorRepository.delete(existingMentor);
        }else {
            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "0",
                            "Mentor with the given ID does not exist."
                    ),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MentorRequestDeletedResponse
                (
                        "1",
                        "Mentor  Successfully Deleted."
                ),HttpStatus.NO_CONTENT);
    }

}
