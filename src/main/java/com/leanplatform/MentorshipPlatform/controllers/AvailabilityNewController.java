package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewRequest;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewResponse;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.GetAllAvailabilitiesResponse;
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
    public ResponseEntity<CreateAvailabilityNewResponse>addAvailability(@RequestParam("scheduleId") UUID scheduleId, @RequestBody CreateAvailabilityNewRequest createAvailabilityNewRequest) {
        try {
            return availabilityNewService.addAnAvailability(scheduleId, createAvailabilityNewRequest);

        } catch (Exception e) {
            return new ResponseEntity<>(new CreateAvailabilityNewResponse
                    (
                            "0",
                            "Failed the on the generation of the DTOs and was caught in side catch block" + e.getLocalizedMessage(),
                            null
                    ), HttpStatus.BAD_REQUEST);


        }
    }
//    @GetMapping("/allAvailabilities")
//    public ResponseEntity<GetAllAvailabilitiesResponse>getAllAvailabilities(@RequestParam(name="scheduleId")UUID scheduleId,@RequestParam(name="userId")UUID userId,
//                                                                            @RequestParam(name="eventTypeId")UUID eventTypeId,@RequestParam(name="dateFrom") LocalDate dateFrom,@RequestParam(name="dateTo")LocalDate dateTo){
//
//     try{
//         return availabilityNewService.getAllAvailability(scheduleId,userId,eventTypeId,dateTo,dateFrom);
//
//     }
//     catch(Exception e){
//
//        }
//
//
//
//
//    }


}
