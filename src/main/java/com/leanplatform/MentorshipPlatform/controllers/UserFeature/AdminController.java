package com.leanplatform.MentorshipPlatform.controllers.UserFeature;

import com.leanplatform.MentorshipPlatform.dto.AdminController.*;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.DeleteMentorRequestObject;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestDeletedResponse;
import com.leanplatform.MentorshipPlatform.services.MentorService.MentorAccountService;
import com.leanplatform.MentorshipPlatform.services.MentorService.MentorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MentorAccountService mentorAccountService;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private MentorAccountService mentorRequestService;

    @GetMapping("/fetchDesiredRequests")
    public ResponseEntity<MentorRequestListResponse> getAllDesiredRequests(@RequestBody FetchDesiredRequestDto desiredRequestDto) {

        if(desiredRequestDto == null || desiredRequestDto.getStatus() == null){
            return new ResponseEntity<>(new MentorRequestListResponse
                    (
                    "0",
                    "Invalid Request" ,
                    null
                    ),HttpStatus.BAD_REQUEST);
        }
        try {
            return mentorAccountService.getDesiredMentorRequests(desiredRequestDto);
        } catch (Exception e){
            return new ResponseEntity<>(new MentorRequestListResponse
                    (
                    "0",
                    "Invalid Request - caught in catch block" ,
                    null
                    ),HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<AdminDecidesRequestResponse> updateStatusOfRequest(@RequestBody RequestToBeUpdated adminRequest) {
        if (adminRequest == null || adminRequest.getMentorId() == null || adminRequest.getAction() == null) {
            return new ResponseEntity<>(new AdminDecidesRequestResponse
                    (
                            "0",
                            "Invalid Action Request",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return mentorAccountService.processMentorRequest(adminRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new AdminDecidesRequestResponse
                    (
                            "0",
                            "Invalid Action Request Caught inside catch block",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }

    }



    @PatchMapping("/updateAdditionalDetails")
    public ResponseEntity<AdminAddsDetailsResponse> updateMentorAccount(@RequestBody AddFurtherDetails furtherDetails){
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

        try {
            return mentorService.addAdditional(furtherDetails);
        } catch (Exception e) {
            return new ResponseEntity<>(new AdminAddsDetailsResponse
                    (
                            "0",
                            "Failed To Update Data : Caught inside catch block.",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/deleteMentorRequest")
    public ResponseEntity<MentorRequestDeletedResponse> deleteMentorRequest(@RequestBody DeleteMentorRequestObject deleteMentorRequestObject){
        if (deleteMentorRequestObject == null ||
                deleteMentorRequestObject.getMentorId() == null ){

            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "0",
                            "Invalid Object - Null Object received"
                    ),HttpStatus.BAD_REQUEST);
        }
        try {
            return mentorRequestService.deleteMentorRequest(deleteMentorRequestObject);
        } catch (Exception e) {
            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "0",
                            "Could Not Delete the desired Mentor Request."
                    ),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteMentor")
    public ResponseEntity<MentorRequestDeletedResponse> deleteMentor(@RequestBody DeleteMentorRequestObject deleteMentorRequestObject){
        if (deleteMentorRequestObject == null ||
                deleteMentorRequestObject.getMentorId() == null ){

            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "0",
                            "Invalid Object - Null Object received"
                    ),HttpStatus.BAD_REQUEST);
        }
        try {
            return mentorService.deleteMentor(deleteMentorRequestObject);
        } catch (Exception e) {
            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "0",
                            "Could Not Delete the desired Mentor Request."
                    ),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
