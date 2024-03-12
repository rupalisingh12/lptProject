package com.leanplatform.MentorshipPlatform.controllers.UserFeature;

import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.DeleteMentorRequestObject;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestDeletedResponse;
import com.leanplatform.MentorshipPlatform.dto.MentorController.*;
import com.leanplatform.MentorshipPlatform.services.MentorService.MentorService;
import com.leanplatform.MentorshipPlatform.services.ServicesByMentorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/mentors")
public class MentorController {

    @Autowired
    private ServicesByMentorsService servicesByMentorsService;
    @Autowired
    private MentorService mentorService;

    @PostMapping("/add-service")
    public ResponseEntity<MentorAddedServiceResponse> createServicesByMentor(@RequestBody MentorAddsServices mentorAddsServices) {
        if (mentorAddsServices == null ||
                mentorAddsServices.getService_id() == null ||
                mentorAddsServices.getPrice() == null) {
            return new ResponseEntity<>(new MentorAddedServiceResponse
                    (
                            "0",
                            "Invalid Request",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        try {
            return servicesByMentorsService.addServiceByMentor(mentorAddsServices);
        } catch (Exception e) {
            return new ResponseEntity<>(new MentorAddedServiceResponse
                    (
                            "0",
                            "Invalid Request",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateMentorService")
    public ResponseEntity<UpdateMentorServiceResponse> updateMentorService(@RequestBody UpdateMentorServiceObject updateMentorServiceObject){
        if (updateMentorServiceObject == null ||
                updateMentorServiceObject.getId() == null ||
                updateMentorServiceObject.getPrice() == null) {
            return new ResponseEntity<>(new UpdateMentorServiceResponse
                    (
                            "0",
                            "Invalid"

                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return servicesByMentorsService.serviceUpdate(updateMentorServiceObject);
        } catch (Exception e){
            return new ResponseEntity<>(new UpdateMentorServiceResponse
                    (
                            "0",
                            e.getLocalizedMessage()
                    ), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteService")
    public ResponseEntity<DeleteMentorServiceResponse> deleteService(@RequestBody DeleteMentorServiceObject deleteMentorServiceObject){
        if (deleteMentorServiceObject == null ||
                deleteMentorServiceObject.getId() == null ){

            return new ResponseEntity<>(new DeleteMentorServiceResponse
                    (
                            "0",
                            "Invalid Object - Null Object received"
                    ),HttpStatus.BAD_REQUEST);
        }
        try {
            return servicesByMentorsService.deleteService(deleteMentorServiceObject);
        } catch (Exception e) {
            return new ResponseEntity<>(new DeleteMentorServiceResponse
                    (
                            "0",
                            "Could Not Delete the desired Mentor Service."
                    ),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/sort")
    public ResponseEntity<List<MentorSearchResponseDto>> sortMentors(@RequestParam("sortByYOE") boolean sortByYOE) {
        if (sortByYOE) {
            return mentorService.sortMentorsByYOE();
        } else {
            // Handle other sorting criteria if needed
            return mentorService.getAllMentors();
        }
    }

    @GetMapping("/searchMentors")
    public ResponseEntity<MentorSearchResponseObject> searchMentors(@RequestBody SearchCriteria criteria) {
        if (criteria == null){
            return new ResponseEntity<>(new MentorSearchResponseObject
                    (
                            "0",
                            "Failed the null check upon method call",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

        try {
            return mentorService.searchMentors(criteria);

        } catch (Exception e) {
            return new ResponseEntity<>(new MentorSearchResponseObject
                    (
                            "0",
                            "Failed the on the generation of the DTOs and was caught in side catch block" + e.getLocalizedMessage(),
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/accountUpdationByMentor")
    public ResponseEntity<MentorUpdatesAccountResponse> accountUpdationByMentor(@RequestBody MentorUpdatesAccount mentorUpdatesAccount){
        if(mentorUpdatesAccount == null){
            return new ResponseEntity<>(new MentorUpdatesAccountResponse
                    (
                            "0",
                            "Null Check Received"
                    ),HttpStatus.CREATED);
        }
        try {
            return mentorService.updateMentorObject(mentorUpdatesAccount);
        } catch (Exception e) {
            return new ResponseEntity<>(new MentorUpdatesAccountResponse
                    (
                            "0",
                            "Failed to update mentor account object"
                    ),HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/deleteMentor")
    public ResponseEntity<MentorRequestDeletedResponse> deleteMentor(@RequestBody DeleteMentorRequestObject deleteMentorRequestObject){
        if (deleteMentorRequestObject == null ||
                deleteMentorRequestObject.getMentorId() == null ){

            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "0",
                            "Invalid Object - Null Object received"
                    ),HttpStatus.BAD_REQUEST);
        }
        try {
            return mentorService.deleteMentor(deleteMentorRequestObject);
        } catch (Exception e) {
            return new ResponseEntity<>(new MentorRequestDeletedResponse
                    (
                            "0",
                            "Could Not Delete the desired Mentor Request."
                    ),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/displayMentor")
    public ResponseEntity<DisplayMentorResponse> displayMentor(@RequestBody DisplayMentorObject displayMentorObject){
        if (displayMentorObject == null ||
        displayMentorObject.getMentor_id() == null){
            return new ResponseEntity<>(new DisplayMentorResponse
                    (
                            "0",
                            "Null Object Fetched",
                            null,
                            null,
                            null,
                            null,
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        try {
            return mentorService.displayMentor(displayMentorObject);
        } catch (Exception e) {
            return new ResponseEntity<>(new DisplayMentorResponse
                    (
                            "0",
                            "Could Not Display Mentor Page",
                            null,
                            null,
                            null,
                            null,
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
    }
}

