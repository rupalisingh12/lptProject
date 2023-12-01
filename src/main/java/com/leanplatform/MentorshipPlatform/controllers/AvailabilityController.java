package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityController.*;
import com.leanplatform.MentorshipPlatform.entities.Availability;
import com.leanplatform.MentorshipPlatform.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;


    @GetMapping("/availability")
    public ResponseEntity<List<Availability>> getWeeklyAvailability() {
        List<Availability> availabilities = availabilityService.findAll();
        return ResponseEntity.ok(availabilities);
    }

    @PostMapping("/addAvailability")
    public ResponseEntity<AvailabilityByMentorResponse> addYourAvailability(@RequestBody AvailabilityByMentor availabilityByMentor){
        if(availabilityByMentor == null){
            return new ResponseEntity<>(new AvailabilityByMentorResponse
                    (
                            "0",
                            "Invalid Request on the controller as null object is fetched",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

        try {
            return availabilityService.createAvailability(availabilityByMentor);

        } catch (Exception e) {
            return new ResponseEntity<>(new AvailabilityByMentorResponse
                    (
                            "0",
                            "Invalid Request caught on Catch block as null object is fetched "+e.getLocalizedMessage(),
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/displayMentorAvailability")
    public ResponseEntity<AvailabilityListOfMentor> displayMentorAvailability(@RequestBody DisplayMentorAvailability displayMentorAvailability){
        if (displayMentorAvailability == null ||
        displayMentorAvailability.getMentorId() == null){
            return new ResponseEntity<>(new AvailabilityListOfMentor
                    (
                            "0",
                            "Invalid Request Object",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

        try {
            return availabilityService.getDesiredMentorAvailability(displayMentorAvailability);
        } catch (Exception e) {
            return new ResponseEntity<>(new AvailabilityListOfMentor
                    (
                            "0",
                            "Could not send availability list by mentor.",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/updateMentorAvailability")
    public ResponseEntity<AvailabilityByMentorResponse> updateMentorAvailability(@RequestBody UpdateMentorAvailability updateMentorAvailability){
        if(updateMentorAvailability == null ||
        updateMentorAvailability.getAvailabilityId() == null){
            return new ResponseEntity<>(new AvailabilityByMentorResponse
                    (
                            "0",
                            "Invalid Request - Null request object received in controller. ",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

        try {
            return availabilityService.updateMentorAvailability(updateMentorAvailability);
        } catch (Exception e) {
            return new ResponseEntity<>(new AvailabilityByMentorResponse
                    (
                            "0",
                            "Invalid Request caught on Catch block as null object is fetched ",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/deleteMentorAvailability")
    public ResponseEntity<MentorAvailabilityDeletedResponse> deleteMentorAvailability(@RequestBody DeleteMentorAvailability deleteMentorAvailability){
        if (deleteMentorAvailability == null ||
        deleteMentorAvailability.getAvailabilityId() == null){

            return new ResponseEntity<>(new MentorAvailabilityDeletedResponse
                    (
                            "0",
                            "Invalid Object - Null Object received"
                    ),HttpStatus.BAD_REQUEST);
        }
        try {
            return availabilityService.deleteMentorAvailability(deleteMentorAvailability);
        } catch (Exception e) {
            return new ResponseEntity<>(new MentorAvailabilityDeletedResponse
                    (
                            "0",
                            "Could Not Delete the desired Mentor Availability."
                    ),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

