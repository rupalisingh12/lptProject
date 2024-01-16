package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.ScheduleController.CreateScheduleRequest;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.CreateScheduleResponse;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.DeleteSchedule;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.GetAllScheduleResponse;
import com.leanplatform.MentorshipPlatform.dto.ServicesOfferedController.AddServiceResponse;
import com.leanplatform.MentorshipPlatform.repositories.ScheduleRepository;
import com.leanplatform.MentorshipPlatform.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @PostMapping("/createSchedule")
    public ResponseEntity<CreateScheduleResponse>createASchedule(@RequestParam (name = "userId") UUID userId, @RequestBody CreateScheduleRequest createScheduleRequest){
        if(userId==null || createScheduleRequest==null || createScheduleRequest.getName()==null){
            return new ResponseEntity<>(new CreateScheduleResponse
                    (
                            "0",
                            "Invalid Request",
                            null
                    ), HttpStatus.BAD_REQUEST);


        }
        try {
            return scheduleService.createSchedules( userId,createScheduleRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateScheduleResponse
                    (
                            "0",
                            "Caught in catch block",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getAllSchedule")
    public ResponseEntity<GetAllScheduleResponse>getAllSchedule(@RequestParam(name = "userId") UUID userId){
        if(userId==null){
            return new ResponseEntity<>(new GetAllScheduleResponse
                    (
                            "0",
                            "Invalid request",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return scheduleService.getSchedules( userId);
        } catch (Exception e) {
            return new ResponseEntity<>(new GetAllScheduleResponse
                    (
                            "0",
                            "Caught in catch block",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getSchdeule/{scheduleId}")
    public ResponseEntity<CreateScheduleResponse>getSchedule(@PathVariable UUID scheduleId , @RequestParam(name = "userId") UUID userId) {
        if(userId==null || scheduleId==null){
            return new ResponseEntity<>(new CreateScheduleResponse
                    (
                            "0",
                            "Invalid Request",
                            null
                    ), HttpStatus.BAD_REQUEST);


        }

        try {
            return scheduleService.getSchdeule(scheduleId, userId);
        } catch (Exception e) {
            return new ResponseEntity<>(new CreateScheduleResponse
                    (
                            "0",
                            "Caught in catch block",
                            null
                    ), HttpStatus.BAD_REQUEST);

        }
    }
    @DeleteMapping("/deleteScheduleId{scheduleId}")
    public ResponseEntity<DeleteSchedule>deleteSchedule(@PathVariable UUID scheduleId,@RequestParam(name = "userId") UUID userId){
        if(userId==null || scheduleId==null){
            return new ResponseEntity<>(new DeleteSchedule
                    (
                            "0",
                            "Invalid Request",
                            null
                    ), HttpStatus.BAD_REQUEST);


        }
        try {
            return scheduleService.deleteSchedule(scheduleId,userId);
        } catch (Exception e) {
            return new ResponseEntity<>(new DeleteSchedule
                    (
                            "0",
                            "Caught in catch block",
                            null
                    ), HttpStatus.BAD_REQUEST);

        }

    }
//
//}
}
