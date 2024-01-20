package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.AddOverrideAvailabilityRequest;
import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.AddOverrideAvailabilityRespone;
import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.AddOverrideAvailabilityResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.DeleteOverrideRequest;
import com.leanplatform.MentorshipPlatform.services.OverrideAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/OverrideAvailability")
public class OverrideAvailabilityController {
    @Autowired
    OverrideAvailabilityService overrideAvailabilityService;

    //    @PostMapping("/addOverride")
//        public ResponseEntity<AddOverrideAvailabilityRespone>addOverride(@RequestParam UUID scheduleId , @RequestBody List<AddOverrideAvailabilityRequest> addOverrideAvailabilityRequest){
//         if(scheduleId==null ||addOverrideAvailabilityRequest==null){
//             return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0","Invalid Request",null),HttpStatus.BAD_REQUEST);
//         }
//         try{
//             return overrideAvailabilityService.addOverrideAvailability(scheduleId,addOverrideAvailabilityRequest);
//
//         }
//         catch(Exception e){
//            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0","Invalid Request",null),HttpStatus.BAD_REQUEST);
//
//
//        }
//
//    }
    @PutMapping("/updateOverride")
    public ResponseEntity<AddOverrideAvailabilityRespone> addOverride(@RequestParam UUID scheduleId, @RequestBody List<AddOverrideAvailabilityRequest> addOverrideAvailabilityRequest) {
        if (scheduleId == null || addOverrideAvailabilityRequest == null) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try {
            return overrideAvailabilityService.UpdateOverrideAvailability(scheduleId, addOverrideAvailabilityRequest);

        } catch (Exception e) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);


        }
    }

    @GetMapping("/getOverride")
    public ResponseEntity<AddOverrideAvailabilityRespone> getOverride(@RequestParam UUID scheduleId) {
        if (scheduleId == null) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try {
            return overrideAvailabilityService.GetOverrideAvailability(scheduleId);

        } catch (Exception e) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);


        }
    }

    @DeleteMapping("/DeleteOverride")
    public ResponseEntity<AddOverrideAvailabilityRespone> DeleteOverride(@RequestParam UUID scheduleId, @RequestBody DeleteOverrideRequest deleteOverrideRequest) {
        if (scheduleId == null || deleteOverrideRequest == null) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try {
            return overrideAvailabilityService.DeleteOverrideAvailability(scheduleId,deleteOverrideRequest );

        } catch (Exception e) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);


        }
    }
}
