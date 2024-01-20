package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller.*;

import com.leanplatform.MentorshipPlatform.services.AvailabilityV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;
@RestController
@RequestMapping("/AvailabilityV2")
public class AvailabilityV2 {
    @Autowired
    AvailabilityV2Service availabilityNewService;
    @PostMapping("/addAvailability")
    public ResponseEntity<UpdateAvailabiliityNewResponse>addAvailability(@RequestParam("userId") UUID userId, @RequestBody CreateAvailabilityNewRequest createAvailabilityNewRequest) {

        if (createAvailabilityNewRequest == null || createAvailabilityNewRequest.getDays() == null || createAvailabilityNewRequest.getEndTime() == null || createAvailabilityNewRequest.getStartTime() == null ||
                createAvailabilityNewRequest.getScheduleId() == null || createAvailabilityNewRequest.getDays().isEmpty()) {

            return new ResponseEntity<>(new UpdateAvailabiliityNewResponse
                    (
                            "0",
                            "Invalid Request : Null object received.",null
                    ), HttpStatus.BAD_REQUEST);
        }
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

        if(userName==null || userId==null || eventTypeId==null || dateFrom==null || dateTo==null){
            return new ResponseEntity<>(new GetAllAvailabilitiesResponse
                    (
                            "0",
                            "Invalid Request : Null object received.",null,null
                    ), HttpStatus.BAD_REQUEST);
        }
     try{
         return availabilityNewService.getAllAvailability(userName,userId,eventTypeId,dateFrom, dateTo);

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
    @PutMapping("/updateAvailability/{scheduleId}")
    public ResponseEntity<UpdateAvailabiliityNewResponse>updateAvailability(@PathVariable UUID scheduleId,@RequestParam(name="userId")UUID userId, @RequestBody UpdateAvailabilityNewRequest updateAvailabilityNewRequest){

        if(scheduleId==null || userId==null || updateAvailabilityNewRequest==null ){
            return new ResponseEntity<>(new UpdateAvailabiliityNewResponse
                    (
                            "0",
                            "Invalid Request recieved" ,
                            null
                    ), HttpStatus.BAD_REQUEST);

        }
        try{
            return availabilityNewService.updateAvailabilitys(scheduleId,userId,updateAvailabilityNewRequest);

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
        if(scheduleId==null || day==null){
            return new ResponseEntity<>(new DeleteAvailabilityResponse
                    (
                            "0",
                            "Invalid Request recieved"
                    ), HttpStatus.BAD_REQUEST);


        }
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
