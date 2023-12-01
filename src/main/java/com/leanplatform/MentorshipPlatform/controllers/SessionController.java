package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.SessionController.*;
import com.leanplatform.MentorshipPlatform.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/bookSession")
    public ResponseEntity<SessionBookedResponse> createNewSession(@RequestBody SessionBookingObject sessionBookingObject){
        if (sessionBookingObject == null ||
        sessionBookingObject.getMenteeId() == null ||
        sessionBookingObject.getAvailabilityId() == null){
            return new ResponseEntity<>(new SessionBookedResponse
                    (
                            "0",
                            "Invalid Request Null Object Received."
                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return sessionService.createNewSession(sessionBookingObject);
        } catch (Exception e) {
            return new ResponseEntity<>(new SessionBookedResponse
                    (
                            "0",
                            "Could Not Create Session - Caught in catch block."
                    ), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteSession")
    public ResponseEntity<SessionDeleteResponse> deleteSession(@RequestBody SessionDeleteRequest sessionDeleteRequest){
        if(sessionDeleteRequest == null ||
                sessionDeleteRequest.getSessionId() == null){
            return new ResponseEntity<>(new SessionDeleteResponse
                    (
                            "0",
                            "Invalid Request - Null request object received. "
                    ),HttpStatus.BAD_REQUEST);
        }

        try {
            return sessionService.deleteSession(sessionDeleteRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new SessionDeleteResponse
                    (
                            "0",
                            "Invalid Request caught on Catch block as null object is fetched "
                    ),HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/mentee/updateSessionDetails")
    public ResponseEntity<PreSessionDetailsAdded> updateSessionDetails(@RequestBody UpdateSessionDetails updateSessionDetails){
        if (updateSessionDetails == null ||
                updateSessionDetails.getSessionId() == null){
            return new ResponseEntity<>(new PreSessionDetailsAdded
                    (
                            "0",
                            "Invalid Request - Null Object received."
                    ),HttpStatus.BAD_REQUEST);
        }

        try {
            return sessionService.updateSessionDetails(updateSessionDetails);
        } catch (Exception e) {
            return new ResponseEntity<>(new PreSessionDetailsAdded
                    (
                            "0",
                            "Could not add pre-session details."
                    ),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sessionSummary")
    public ResponseEntity<SessionSummaryResponse> getSessionSummary(@RequestBody SessionSummaryRequest sessionSummaryRequest){
        if (sessionSummaryRequest == null ||
                sessionSummaryRequest.getSessionId() == null){
            return new ResponseEntity<>(new SessionSummaryResponse
                    (
                            "0",
                            "Invalid Request - Null object received.",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        try {
            return sessionService.getSessionSummary(sessionSummaryRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new SessionSummaryResponse
                    (
                            "0",
                            "Could not fetch session summary.",
                            null
                    ),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/rateMentorSession")
    public ResponseEntity<MentorSessionRatingResponse> rateMentorSession (@RequestBody MentorSessionRatingRequest mentorSessionRatingRequest){
        if(mentorSessionRatingRequest == null ||
                mentorSessionRatingRequest.getSessionId() == null ||
                mentorSessionRatingRequest.getMentorId() == null ||
                mentorSessionRatingRequest.getMenteeId() == null ||
                mentorSessionRatingRequest.getServiceId() == null ||
                mentorSessionRatingRequest.getSessionRating() == null ){
            return new ResponseEntity<>(new MentorSessionRatingResponse
                    (
                            "0",
                            "Invalid request : Null Object received."
                    ),HttpStatus.BAD_REQUEST);
        }

        try {
            return sessionService.rateMentorSession(mentorSessionRatingRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new MentorSessionRatingResponse
                    (
                            "0",
                            "Failed to rate the session by Mentor."
                    ),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/upcomingSessions")
    public ResponseEntity<SessionsListResponse> upcomingSessions(@RequestBody SessionsListRequest sessionsListRequest){
        if (sessionsListRequest == null ||
        sessionsListRequest.getMenteeId() == null){
            return new ResponseEntity<>(new SessionsListResponse
                    (
                            "0",
                            "Invalid Request - Null object received on the given request object.",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

        try {
            return sessionService.getUpcomingSessions(sessionsListRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new SessionsListResponse
                    (
                            "0",
                            "Could not fetch upcoming session list.",
                            null
                    ),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/finishedSessions")
    public ResponseEntity<SessionsListResponse> finishedSessions(@RequestBody SessionsListRequest sessionsListRequest){
        if (sessionsListRequest == null ||
                sessionsListRequest.getMenteeId() == null){
            return new ResponseEntity<>(new SessionsListResponse
                    (
                            "0",
                            "Invalid Request - Null object received on the given request object.",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

        try {
            return sessionService.getFinishedSessions(sessionsListRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new SessionsListResponse
                    (
                            "0",
                            "Could not fetch upcoming session list.",
                            null
                    ),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getSessionDetails")
    public ResponseEntity<SessionDetailsResponse> getSessionDetails(@RequestBody SessionDetailsRequest sessionDetailsRequest){
        if (sessionDetailsRequest == null || sessionDetailsRequest.getSessionId() == null){
            return new ResponseEntity<>(new SessionDetailsResponse
                    (
                            "0",
                            "Invalid Request - Null Object Received.",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        try {
            return sessionService.getSessionDetails(sessionDetailsRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(new SessionDetailsResponse
                    (
                            "0",
                            "Could Not fetch required session details.",
                            null
                    ),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
