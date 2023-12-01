package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeAccount;
import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeAdditionalDetails;
import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeModified;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestResponse;
import com.leanplatform.MentorshipPlatform.services.MenteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentee")
public class MenteeController {

    @Autowired
    private MenteeService menteeService;

    @PostMapping("/addAccount")
    public ResponseEntity<MenteeModified> createMenteeAccount(@RequestBody MenteeAccount menteeAccount) {
        if (menteeAccount == null ||
                menteeAccount.getFirstName() == null ||
                menteeAccount.getLastName() == null ||
                menteeAccount.getEmail() == null ||
                menteeAccount.getPhoneNo() == null) {
            return new ResponseEntity<>(new MenteeModified
                    (
                            "0",
                            "Invalid Request to Add account , null request object received."
                    ), HttpStatus.BAD_REQUEST);
        }
        if (menteeService.isEmailAlreadyExists(menteeAccount.getEmail())) {
            return new ResponseEntity<>(new MenteeModified
                    (
                            "0",
                            "Email already exists - Resource Conflict"
                    ), HttpStatus.CONFLICT);
        }
        if (menteeService.isPhoneNumberAlreadyExists(menteeAccount.getPhoneNo())) {
            return new ResponseEntity<>(new MenteeModified
                    (
                            "0",
                            "Phone Number already exists - Resource Conflict"
                    ), HttpStatus.CONFLICT);
        }

        try {
            return menteeService.createMenteeAccount(menteeAccount);
        } catch (Exception e) {
            return new ResponseEntity<>(new MenteeModified
                    (
                            "0",
                            "Could Not create mentee account caught in catch block."
                    ), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateAccount")
    public ResponseEntity<MenteeModified> updateMenteeAccount(@RequestBody MenteeAdditionalDetails menteeAdditionalDetails) {
        if (menteeAdditionalDetails == null) {
            return new ResponseEntity<>(new MenteeModified
                    (
                            "0",
                            "Invalid Request to Add account , null request object received."
                    ), HttpStatus.BAD_REQUEST);
        }

        try {
            return menteeService.updateMenteeAccount(menteeAdditionalDetails);
        } catch (Exception e) {
            return new ResponseEntity<>(new MenteeModified
                    (
                            "0",
                            "Could Not Update mentee account caught in catch block."
                    ), HttpStatus.BAD_REQUEST);
        }
    }


}
