package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.*;
import com.leanplatform.MentorshipPlatform.dto.BookingController.BookingRequest;
import com.leanplatform.MentorshipPlatform.dto.MentorController.MentorSearchResponseObject;
import com.leanplatform.MentorshipPlatform.services.AvailabilityNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;
@RestController
@RequestMapping("/AvailabilityNew")
public class AvailabilityNewController {
    @Autowired AvailabilityNewService availabilityNewService;
    @PostMapping("/addAvailability")
    public ResponseEntity<UpdateAvailabiliityNewResponse>addAvailability(@RequestParam("userId") UUID userId, @RequestBody CreateAvailabilityNewRequest createAvailabilityNewRequest) {
        try {
            return availabilityNewService.addAnAvailability(userId, createAvailabilityNewRequest);

        } catch (Exception e) {
            return new ResponseEntity<>(new UpdateAvailabiliityNewResponse
                    (
                            "0",
                            "Failed the on the generation of the DTOs and was caught in side catch block" + e.getLocalizedMessage(),
                            null
                    ), HttpStatus.BAD_REQUEST);


        }
    }
    @GetMapping("/allAvailabilities")
    public ResponseEntity<GetAllAvailabilitiesResponse>getAllAvailabilities(@RequestParam(name="userName")String userName,@RequestParam(name="userId")UUID userId,
                                                                            @RequestParam(name="eventTypeId")UUID eventTypeId,@RequestParam(name="dateFrom") LocalDate dateFrom,@RequestParam(name="dateTo")LocalDate dateTo){

     try{
         return availabilityNewService.getAllAvailability(userName,userId,eventTypeId,dateTo,dateFrom);

     }
     catch(Exception e){
         return new ResponseEntity<>(new GetAllAvailabilitiesResponse
                   (
                            "0",
                            "Failed the on the generation of the DTOs and was caught in side catch block" + e.getLocalizedMessage(),
                            null,null
                   ), HttpStatus.BAD_REQUEST);

        }



    }
    @PutMapping("/updateAvailability")
    public ResponseEntity<UpdateAvailabiliityNewResponse>updateAvailability(@RequestParam(name="scheduleId")UUID scheduleId, @RequestBody UpdateAvailabilityNewRequest updateAvailabilityNewRequest){
        try{
            return availabilityNewService.updateAvailabilitys(scheduleId,updateAvailabilityNewRequest);

        }
        catch(Exception e) {
            return new ResponseEntity<>(new UpdateAvailabiliityNewResponse
                    (
                            "0",
                            "Failed the on the generation of the DTOs and was caught in side catch block" + e.getLocalizedMessage(),
                            null
                    ), HttpStatus.BAD_REQUEST);

        }
    }
    @DeleteMapping("/deleteAvailability")
    public ResponseEntity<DeleteAvailabilityResponse>deleteAvailability(@RequestParam(name="scheduleId")UUID scheduleId,@RequestParam(name="day") Long day) {
        try {
            return availabilityNewService.deleteAvailability(scheduleId, day);

        } catch (Exception e) {
            return new ResponseEntity<>(new DeleteAvailabilityResponse
                    (
                            "0",
                            "Failed the on the generation of the DTOs and was caught in side catch block" + e.getLocalizedMessage()
                    ), HttpStatus.BAD_REQUEST);

        }
    }


}
