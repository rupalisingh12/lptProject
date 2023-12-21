package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.ScheduleController.CreateScheduleResponse;
import com.leanplatform.MentorshipPlatform.dto.ServicesOfferedController.AddServiceResponse;
import com.leanplatform.MentorshipPlatform.repositories.ScheduleRepository;
import com.leanplatform.MentorshipPlatform.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
//    @PostMapping("/createSchedule")
//    public ResponseEntity<CreateScheduleResponse>createASchedule(@RequestParam (name = "bookingId") UUID bookingId){
//        try {
//            return scheduleService.createSchedules(UUID bookingId) ;
//        } catch (Exception e) {
//            return new ResponseEntity<>(new CreateScheduleResponse
//                    (
//                            "0",
//                            "Can not add service",
//                            null
//                    ), HttpStatus.BAD_REQUEST);
//        }
//
//    }
//
//}
}
