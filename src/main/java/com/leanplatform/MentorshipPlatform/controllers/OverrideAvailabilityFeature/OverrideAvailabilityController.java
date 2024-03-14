package com.leanplatform.MentorshipPlatform.controllers.OverrideAvailabilityFeature;

import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.*;
import com.leanplatform.MentorshipPlatform.services.OverrideAvailabilityFeatureService.OverrideAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<AddOverrideAvailabilityRespone> addOverride(@RequestParam UUID scheduleId, @RequestBody AddavailabilityOverrideCombinedRequest addavailabilityOverrideCombinedRequest) {
        if (scheduleId == null || addavailabilityOverrideCombinedRequest== null) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
        }
        try {
            return overrideAvailabilityService.UpdateOverrideAvailability(scheduleId,addavailabilityOverrideCombinedRequest);

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
            return overrideAvailabilityService.DeleteOverrideAvailability(scheduleId, deleteOverrideRequest);

        } catch (Exception e) {
            return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);


        }
    }
        @DeleteMapping("/DeleteOverrideUnavailability")
        public ResponseEntity<AddOverrideAvailabilityRespone> DeleteOverrideUnavailability(@RequestParam UUID scheduleId,@RequestBody DeleteOverrideUnavailabilityRequest deleteOverrideUnavailability){
            if (scheduleId == null || deleteOverrideUnavailability == null) {
                return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);
            }
            try {
                return overrideAvailabilityService.DeleteOverrideUnAvailabilitys(scheduleId,deleteOverrideUnavailability );

            } catch (Exception e) {
                return new ResponseEntity<>(new AddOverrideAvailabilityRespone("0", "Invalid Request", null), HttpStatus.BAD_REQUEST);


            }
        }
}
