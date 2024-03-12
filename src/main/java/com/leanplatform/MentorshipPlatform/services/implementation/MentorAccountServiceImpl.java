package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.AdminController.AdminDecidesRequestResponse;
import com.leanplatform.MentorshipPlatform.dto.AdminController.FetchDesiredRequestDto;
import com.leanplatform.MentorshipPlatform.dto.AdminController.MentorRequestListResponse;
import com.leanplatform.MentorshipPlatform.dto.AdminController.RequestToBeUpdated;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityController.AdminChecksRequestsDto;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.*;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.Mentor;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.MentorRequest;
import com.leanplatform.MentorshipPlatform.enums.MentorEnums;
import com.leanplatform.MentorshipPlatform.enums.RequestAction;
import com.leanplatform.MentorshipPlatform.mappers.MentorMapper.MentorMapper;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.MentorRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.MentorRequestRepository;
import com.leanplatform.MentorshipPlatform.services.AdminChecksRequestsDtoService;
import com.leanplatform.MentorshipPlatform.services.MentorService.MentorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MentorAccountServiceImpl implements MentorAccountService {
    @Autowired
    private MentorRequestRepository mentorRequestRepository;

    @Autowired
    private AdminChecksRequestsDtoService adminChecksRequestsDtoService;

    @Autowired
    private MentorRepository mentorRepository;



    public ResponseEntity<MentorRequestResponse> creatingMentorAccount(MentorRequestObject mentorRequestObject) {
        //A null check on the incoming object.
        if (mentorRequestObject == null
                || mentorRequestObject.getFirstName() == null
                || mentorRequestObject.getFirstName().length()==0
                || mentorRequestObject.getLastName() == null
                || mentorRequestObject.getLastName().length() == 0
                || mentorRequestObject.getEmail() == null
                || mentorRequestObject.getEmail().length() == 0
                || mentorRequestObject.getPhoneNo() == null
                || mentorRequestObject.getPhoneNo().length() == 0) {
            //check the length should be greater than 0
            return new ResponseEntity<>(new MentorRequestResponse
                    (
                            "0",
                            "Invalid Request : Null object received."
                    ), HttpStatus.BAD_REQUEST);
        }

        MentorRequest newMentorRequest = MentorMapper.convertDtoToEntity(mentorRequestObject);
        mentorRequestRepository.save(newMentorRequest);

        return new ResponseEntity<>(new MentorRequestResponse
                (
                        "1",
                        "Mentor account created successfully"

                ), HttpStatus.CREATED);
    }

    public ResponseEntity<MentorRequestResponse> partialUpdate(MentorUpdateRequestObject mentorUpdateRequestObject) throws IOException {
        if (mentorUpdateRequestObject == null || mentorUpdateRequestObject.getLinkedIn_link() == null
                || mentorUpdateRequestObject.getResume() == null || mentorUpdateRequestObject.getMentorRoleApplied() == null ||
        mentorUpdateRequestObject.getEmail() == null) {
            return new ResponseEntity<>(new MentorRequestResponse
                    (
                            "0",
                            "Invalid"

                    ), HttpStatus.BAD_REQUEST);
        }
        Optional<MentorRequest> mentorRequestOptional = mentorRequestRepository.findByEmail(mentorUpdateRequestObject.getEmail());

        if (mentorRequestOptional.isPresent()) {
            MentorRequest existingMentorRequest = mentorRequestOptional.get();

            //when doing it for the first time set it to incomplete - MAKE AN ENUM (pending ,deleted, incomplete , active , approved , rejected)
            existingMentorRequest.setStatus(MentorEnums.Pending);
            existingMentorRequest.setLinkedIn_link(mentorUpdateRequestObject.getLinkedIn_link());
            if (mentorUpdateRequestObject.getYearsOfExperience() >= 0) {
                existingMentorRequest.setYearsOfExperience(mentorUpdateRequestObject.getYearsOfExperience());
            } else {
                existingMentorRequest.setYearsOfExperience(0);
            }
            existingMentorRequest.setResume(mentorUpdateRequestObject.getResume());
            if (mentorUpdateRequestObject.getMentorRoleApplied().length()>0) {
                existingMentorRequest.setMentorRoleApplied(mentorUpdateRequestObject.getMentorRoleApplied());
            } else {
                existingMentorRequest.setMentorRoleApplied("No mentor role specified.");
            }
            mentorRequestRepository.save(existingMentorRequest);

        }else {
            return new ResponseEntity<>(new MentorRequestResponse
                    (
                            "0",
                            "Mentor Account Doesn't exist."

                    ), HttpStatus.CONFLICT);
        }

        MentorRequestResponse mentorRequestResponse = new MentorRequestResponse();
        mentorRequestResponse.setStatusCode("1");
        mentorRequestResponse.setResponseMessage("Mentor account created successfully");

        return new ResponseEntity<>(mentorRequestResponse, HttpStatus.CREATED);
    }


    public ResponseEntity<MentorRequestListResponse> getDesiredMentorRequests(FetchDesiredRequestDto desiredRequestDto){
        if(desiredRequestDto == null || desiredRequestDto.getStatus() == null){
            return new ResponseEntity<>(new MentorRequestListResponse
                    (
                            "0",
                            "Invalid Request" ,
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        List<MentorRequest> mentorRequests =  mentorRequestRepository.getDesiredMentorRequests(desiredRequestDto.getStatus());
        List<AdminChecksRequestsDto> adminChecksRequestsDtos = adminChecksRequestsDtoService.convertToDTOList(mentorRequests);

        return new ResponseEntity<>(new MentorRequestListResponse
                (
                        "1",
                        "Desired Requests Fetched",
                        adminChecksRequestsDtos
                ),HttpStatus.CREATED);
    }

    public ResponseEntity<AdminDecidesRequestResponse> processMentorRequest(RequestToBeUpdated adminRequest) {
        if (adminRequest == null || adminRequest.getMentorId() == null || adminRequest.getAction() == null) {
            return new ResponseEntity<>(new AdminDecidesRequestResponse
                    (
                            "0",
                            "Invalid Action Request",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        RequestAction action = adminRequest.getAction();
        UUID mentorId = adminRequest.getMentorId();
        Optional<MentorRequest> mentorRequestOptional = mentorRequestRepository.findById(mentorId);

        if (mentorRequestOptional.isPresent()) {
            MentorRequest existingMentorRequest = mentorRequestOptional.get();

            if (action.equals(RequestAction.Approved)) {
                existingMentorRequest.setStatus(MentorEnums.Approved);

                // Make the approved mentor request object as a Mentor object.
                Mentor newMentor = new Mentor();
                newMentor.setFirstName(existingMentorRequest.getFirstName());
                newMentor.setLastName(existingMentorRequest.getLastName());
                newMentor.setPhoneNo(existingMentorRequest.getPhoneNo());
                newMentor.setResume(existingMentorRequest.getResume());
                newMentor.setEmail(existingMentorRequest.getEmail());
                newMentor.setLinkedIn_link(existingMentorRequest.getLinkedIn_link());
                newMentor.setYearsOfExperience(existingMentorRequest.getYearsOfExperience());
                newMentor.setMentorRoleApplied(existingMentorRequest.getMentorRoleApplied());


                // Save the new Mentor object
                mentorRepository.save(newMentor);
            } else if (action.equals(RequestAction.Rejected)) {
                existingMentorRequest.setStatus(MentorEnums.Rejected);
            }

            mentorRequestRepository.save(existingMentorRequest);
        }else {
            return new ResponseEntity<>(new AdminDecidesRequestResponse
                    (
                            "0",
                            "Mentor with the provided id not present",
                            null
                    ), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new AdminDecidesRequestResponse
                        (
                                "1",
                                "Status of Mentor Request Updated",
                                mentorId
                        ), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GetMentorRequestResponseObject> getMentorRequests(GetMentorRequestObject getMentorRequestObject) {
        if (getMentorRequestObject == null ||
                getMentorRequestObject.getMentor_id() == null ||
                getMentorRequestObject.getEmail() == null){

            return new ResponseEntity<>(new GetMentorRequestResponseObject
                    (
                            "0",
                            "Invalid Object - Null Object received",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
         Optional<MentorRequest> mentorRequestOptional = mentorRequestRepository.findById(getMentorRequestObject.getMentor_id());
        MentorRequest mentorRequest = new MentorRequest();
        if (mentorRequestOptional.isPresent()){
             mentorRequest = mentorRequestOptional.get();
        }else {
            return new ResponseEntity<>(new GetMentorRequestResponseObject
                    (
                            "0",
                            "Requested Resource Not Found",
                            null
                    ),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new GetMentorRequestResponseObject
                (
                        "1",
                        "Mentor Request fetched Successfully",
                        mentorRequest
                ),HttpStatus.CREATED);
    }

    @Override
    public boolean isEmailAlreadyExists(String email) {
        return mentorRequestRepository.findByEmail(email).isPresent();
    }
    @Override
    public boolean isPhoneNumberAlreadyExists(String phoneNo) {
        return mentorRequestRepository.findByPhoneNo(phoneNo).isPresent();
    }

    @Override
    public ResponseEntity<MentorRequestDeletedResponse> deleteMentorRequest(DeleteMentorRequestObject deleteMentorRequestObject) {

        if (deleteMentorRequestObject == null ||
                deleteMentorRequestObject.getMentorId() == null ){

            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "0",
                            "Invalid Object - Null Object received"
                    ),HttpStatus.BAD_REQUEST);
        }
        Optional<MentorRequest> optionalMentorRequest = mentorRequestRepository.findById(deleteMentorRequestObject.getMentorId());
        if (optionalMentorRequest.isPresent()){
            MentorRequest existingMentorRequest = optionalMentorRequest.get();
            mentorRequestRepository.delete(existingMentorRequest);
        }else {
            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "0",
                            "Mentor Request with the given ID does not exist."
                    ),HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "1",
                            "Mentor Request Successfully Deleted."
                    ),HttpStatus.OK);




    }


}





