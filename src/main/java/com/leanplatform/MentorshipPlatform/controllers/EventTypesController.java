package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.EventTypesController.CreateEventRequestObject;
import com.leanplatform.MentorshipPlatform.dto.EventTypesController.CreateEventResponse;
import com.leanplatform.MentorshipPlatform.dto.EventTypesController.DeleteEventResponse;
import com.leanplatform.MentorshipPlatform.dto.EventTypesController.GetAllEventResponse;
import com.leanplatform.MentorshipPlatform.services.EventTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/EventTypes")
public class EventTypesController {
    @Autowired
    private EventTypesService eventTypesService;


    @PostMapping("/createAnEvent")
    public ResponseEntity<CreateEventResponse>createAnEvent(@RequestParam(name="userId") UUID userId, @RequestBody CreateEventRequestObject createEventRequestObject){
        if(createEventRequestObject==null || createEventRequestObject.getLength()==null || createEventRequestObject.getTitle()==null || createEventRequestObject.getDescription()==null){
            return new ResponseEntity<>(new CreateEventResponse
                    (
                            "0",
                            "Invalid Request" ,
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return eventTypesService.createEvent(createEventRequestObject,userId);
        } catch (Exception e){
            return new ResponseEntity<>(new CreateEventResponse
                    (                            "0",
                            "Invalid Request - caught in catch block" ,
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        }
        @GetMapping("/gettALLEvents")
    public ResponseEntity<GetAllEventResponse>gettAllEvents(@RequestParam(name="userId") UUID userId){
            try {
                return eventTypesService.getALLEventsOfAUser(userId);
            } catch (Exception e){
                return new ResponseEntity<>(new GetAllEventResponse
                        (                            "0",
                                "Invalid Request - caught in catch block" ,
                                null
                        ),HttpStatus.BAD_REQUEST);
            }
        }
        @GetMapping("/getEvent/{eventId}")
        public ResponseEntity<CreateEventResponse>getEvents(@PathVariable UUID eventId,@RequestParam(name="userId") UUID userId){
            try {
                return eventTypesService.getEventOfAUser(eventId,userId);
            } catch (Exception e){
                return new ResponseEntity<>(new CreateEventResponse
                        (                            "0",
                                "Invalid Request - caught in catch block" ,
                                null
                        ),HttpStatus.BAD_REQUEST);
            }
        }
//        @PutMapping("/updateAnEvent")
//    public ResponseEntity<CreateEventResponse>updateEvent(@PathVariable UUID eventId,@RequestParam(name="userId") UUID userId){
//            try {
//                return eventTypesService.updateEvent(eventId,userId);
//            } catch (Exception e){
//                return new ResponseEntity<>(new CreateEventResponse
//                        (                            "0",
//                                "Invalid Request - caught in catch block" ,
//                                null
//                        ),HttpStatus.BAD_REQUEST);
//            }
//        }
    @DeleteMapping("/deleteAnEvent/{eventId}")
    public ResponseEntity<DeleteEventResponse>deleteEvent(@PathVariable UUID eventId,@RequestParam(name="userId") UUID userId){
//        try {
            return eventTypesService.deleteAEvent(eventId,userId);
//        } catch (Exception e){
//            return new ResponseEntity<>(new DeleteEventResponse
//                    (                            "0",
//                            "Invalid Request - caught in catch block" ,
//                            null
//                    ),HttpStatus.BAD_REQUEST);
//        }
    }
}

