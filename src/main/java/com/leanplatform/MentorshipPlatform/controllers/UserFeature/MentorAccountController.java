package com.leanplatform.MentorshipPlatform.controllers.UserFeature;

import com.leanplatform.MentorshipPlatform.dto.*;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.*;
import com.leanplatform.MentorshipPlatform.services.MentorService.MentorAccountService;
import com.leanplatform.MentorshipPlatform.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentor")
public class MentorAccountController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private MentorAccountService mentorRequestService;

    @GetMapping("/register")
    public String registerNum(Model model) {
        model.addAttribute("phoneNum", new PhoneNum());
        return "register";
    }

    @GetMapping("/OTP-verify")
    public String OTPvalidation(Model model) {
        model.addAttribute("otpNumber", new OtpValidationRequest());
        return "otp";
    }



    @PostMapping("/sendOTP")
    public void sendOTP( @ModelAttribute("phoneNumber")PhoneNum phoneNum) {

        System.out.println(phoneNum.getPhoneNumber());

         smsService.sendSMS(phoneNum);
    }

    @PostMapping("/verifyOTP")
    public void verifyOTP( @ModelAttribute("otpNumber") OtpValidationRequest otpValidationRequest) {

        smsService.validateOtp(otpValidationRequest);
    }

    @GetMapping("/fetchMentorRequest")
    public ResponseEntity<GetMentorRequestResponseObject> fetchMentorRequest(@RequestBody GetMentorRequestObject getMentorRequestObject){
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
        try {
            return mentorRequestService.getMentorRequests(getMentorRequestObject);
        } catch (Exception e) {
            return new ResponseEntity<>(new GetMentorRequestResponseObject
                    (
                            "0",
                            "Could Not Fetch Desired Object",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping("/addAccount")
    public ResponseEntity<MentorRequestResponse> createMentorRequestAccount(@RequestBody MentorRequestObject mentorRequestObjectDetails) {
        if (mentorRequestObjectDetails == null
                || mentorRequestObjectDetails.getFirstName() == null
                || mentorRequestObjectDetails.getFirstName().length()==0
                || mentorRequestObjectDetails.getLastName() == null
                || mentorRequestObjectDetails.getLastName().length() == 0
                || mentorRequestObjectDetails.getEmail() == null
                || mentorRequestObjectDetails.getEmail().length() == 0
                || mentorRequestObjectDetails.getPhoneNo() == null
                || mentorRequestObjectDetails.getPhoneNo().length() == 0) {
            //check the length should be greater than 0
            return new ResponseEntity<>(new MentorRequestResponse
                    (
                            "0",
                            "Invalid Request : Null object received."
                    ), HttpStatus.BAD_REQUEST);
        }
        if (mentorRequestService.isEmailAlreadyExists(mentorRequestObjectDetails.getEmail())) {
            return new ResponseEntity<>(new MentorRequestResponse
                    (
                            "0",
                            "Email already exists - Resource Conflict"
                    ), HttpStatus.CONFLICT);
        }
        if (mentorRequestService.isPhoneNumberAlreadyExists(mentorRequestObjectDetails.getPhoneNo())) {
            return new ResponseEntity<>(new MentorRequestResponse
                    (
                            "0",
                            "Phone Number already exists - Resource Conflict"
                    ), HttpStatus.CONFLICT);
        }

        try {
             return mentorRequestService.creatingMentorAccount(mentorRequestObjectDetails);
        } catch (Exception e) {
            return new ResponseEntity<>(new MentorRequestResponse
                    (
                            "0",
                            e.getLocalizedMessage()
                    ), HttpStatus.BAD_REQUEST);
        }

    }


    @PatchMapping("/updateMentorRequest")
    public ResponseEntity<MentorRequestResponse> updateMentorRequestAccount(@RequestBody MentorUpdateRequestObject mentorUpdateRequestObject){
        if (mentorUpdateRequestObject == null || mentorUpdateRequestObject.getLinkedIn_link() == null
                || mentorUpdateRequestObject.getResume() == null || mentorUpdateRequestObject.getMentorRoleApplied() == null ||
                mentorUpdateRequestObject.getEmail() == null) {
            return new ResponseEntity<>(new MentorRequestResponse
                    (
                            "0",
                            "Invalid"

                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return mentorRequestService.partialUpdate(mentorUpdateRequestObject);
        } catch (Exception e){
            return new ResponseEntity<>(new MentorRequestResponse
                    (
                            "0",
                            e.getLocalizedMessage()

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
}

