package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.EventTypesController.*;

import com.leanplatform.MentorshipPlatform.entities.AvailabilityV2;
import com.leanplatform.MentorshipPlatform.entities.EventType;
import com.leanplatform.MentorshipPlatform.entities.Schedule;
import com.leanplatform.MentorshipPlatform.mappers.EventMapper;
import com.leanplatform.MentorshipPlatform.repositories.AvailabilityV2Repository;
import com.leanplatform.MentorshipPlatform.repositories.EventTypesRepository;
import com.leanplatform.MentorshipPlatform.repositories.ScheduleRepository;
import com.leanplatform.MentorshipPlatform.services.EventTypesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class EventTypesServiceImpl implements EventTypesService {
    @Autowired EventTypesRepository eventTypesRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    AvailabilityV2Repository availabilityV2Repository;
    @Override
   public ResponseEntity<CreateEventResponse> createEvent(CreateEventRequestObject createEventRequestObject,UUID userId){
        if(createEventRequestObject==null || createEventRequestObject.getLength()==null || createEventRequestObject.getTitle()==null || createEventRequestObject.getDescription()==null){
            return new ResponseEntity<>(new CreateEventResponse
                    (
                            "0",
                            "Invalid Request" ,
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        EventType eventType=new EventType();
        eventType.setLength(createEventRequestObject.getLength());
        eventType.setTitle(createEventRequestObject.getTitle());
//        if (createEventRequestObject.getPrice()>0) {
//            eventType.setPrice(createEventRequestObject.getPrice());
//
//        } else {
//            eventType.setPrice(null);
//        }
        eventType.setPrice(createEventRequestObject.getPrice());
        //eventType.setPrice(createEventRequestObject.getPrice());
        //eventType.setTitle(createEventRequestObject.getTitle());
        eventType.setDescription(createEventRequestObject.getDescription());
        eventType.setUserId(userId);
        eventType.setHidden(true);
        EventType eventType1= eventTypesRepository.save(eventType);
        CreateEventDTO createEventDTO=new CreateEventDTO();
        createEventDTO.setDescription(eventType1.getDescription());
        createEventDTO.setLength(eventType1.getLength());
        createEventDTO.setPrice(eventType1.getPrice());
        createEventDTO.setTitle(eventType1.getTitle());
        createEventDTO.setUserId(userId);
        createEventDTO.setId(eventType1.getEventId());
        createEventDTO.setHidden(true);
        createEventDTO.setScheduleId(eventType1.getScheduleId());
        return new ResponseEntity<>(new 
                CreateEventResponse("1",
                "The event is created",createEventDTO),HttpStatus.CREATED);



    }
    @Override
    public ResponseEntity<GetAllEventResponse>getALLEventsOfAUser(UUID userId){
        if(userId==null){
            return new ResponseEntity<>(new GetAllEventResponse
                    (
                            "0",
                            "Invalid Request" ,
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        List<EventType> eventType= eventTypesRepository.findAllByUserId(userId);
        if (eventType.isEmpty()) {
            return new ResponseEntity<>(new GetAllEventResponse
                    ("1",
                            "No events found or a given user or no no userId exist with this userId exist ",
                            null), HttpStatus.OK);
        }
        //use a for loop ,send only one object
        List<CreateEventDTO> createEventDTOList = EventMapper.convertEntityToDTO(eventType);
        return new ResponseEntity<>(new GetAllEventResponse
                ("1",
                        "The list of events is:",createEventDTOList), HttpStatus.OK);




    }
    @Override
    public ResponseEntity<CreateEventResponse>getEventOfAUser(UUID eventId,UUID userId){
        if(eventId==null || userId==null){
            return new ResponseEntity<>(new CreateEventResponse
                    (
                            "0",
                            "Invalid Request" ,
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        EventType eventType= eventTypesRepository.findByUserIdAndEventId(eventId,userId);
        if(eventType==null){
            return new ResponseEntity<>(new CreateEventResponse
                    ("0",
                            "No user exist with this userId or no eventId exist with this eventId ",
                            null), HttpStatus.NOT_FOUND);
        }
        //null check if eventTYpe not found
        CreateEventDTO createEventDTO  = EventMapper.convertEntityToDTO1(eventType);
        return new ResponseEntity<>(new CreateEventResponse
                ("1",
                        "The Events is:",createEventDTO), HttpStatus.OK);




    }
    @Override
   public ResponseEntity<CreateEventResponse>updateEvent(UUID eventId,UUID userId,UpdateEventRequest updateEventRequest){
        if(eventId==null || userId==null || updateEventRequest==null  ){
            return new ResponseEntity<>(new CreateEventResponse
                    (
                            "0",
                            "Invalid Request" ,
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        EventType eventType= eventTypesRepository.findByUserIdAndEventId(eventId,userId);
        if(eventType==null){
            return new ResponseEntity<>(new CreateEventResponse
                    ("0",
                            "This event does not exist:", null), HttpStatus.NOT_FOUND);
        }
        if(updateEventRequest.getScheduleId()!=null) {
            Schedule schedule = scheduleRepository.findByScheduleId(updateEventRequest.getScheduleId());
            if (schedule == null) {
                return new ResponseEntity<>(new CreateEventResponse
                        ("0",
                                "This scheduleId does not exist :", null), HttpStatus.NOT_FOUND);
            }
            else{
                if (updateEventRequest.getDescription() != null) {
                    eventType.setDescription(updateEventRequest.getDescription());

                }
                if (updateEventRequest.getHidden() != null) {
                    eventType.setHidden(updateEventRequest.getHidden());
                }
                if (updateEventRequest.getTitle() != null) {
                    eventType.setTitle(updateEventRequest.getTitle());
                }
                if (updateEventRequest.getLength() != null) {
                    eventType.setLength(updateEventRequest.getLength());
                }
                List<AvailabilityV2> availabilityV2s = availabilityV2Repository.findByScheduleId(updateEventRequest.getScheduleId());
                if (!availabilityV2Repository.findByScheduleId(updateEventRequest.getScheduleId()).isEmpty()) {
                    eventType.setScheduleId(updateEventRequest.getScheduleId());
                } else {
                    return new ResponseEntity<>(new CreateEventResponse
                            ("0",
                                    "This scheduleId does not contain any availability:", null), HttpStatus.BAD_REQUEST);
                }
                if (updateEventRequest.getPrice() != null) {
                    eventType.setPrice(updateEventRequest.getPrice());
                }
//                EventType eventType1 = eventTypesRepository.save(eventType);
//                CreateEventDTO createEventDTO = EventMapper.convertEntityToDTO1(eventType1);
//                return new ResponseEntity<>(new CreateEventResponse
//                        ("1",
//                                "The Event has been updated:", createEventDTO), HttpStatus.OK);


            }
            EventType eventType1 = eventTypesRepository.save(eventType);
            CreateEventDTO createEventDTO = EventMapper.convertEntityToDTO1(eventType1);
            return new ResponseEntity<>(new CreateEventResponse
                    ("1",
                            "The Event has been updated:", createEventDTO), HttpStatus.OK);

        }


        //null check put
        else {
            if (updateEventRequest.getDescription() != null) {
                eventType.setDescription(updateEventRequest.getDescription());

            }
            if (updateEventRequest.getHidden() != null) {
                eventType.setHidden(updateEventRequest.getHidden());
            }
            if (updateEventRequest.getTitle() != null) {
                eventType.setTitle(updateEventRequest.getTitle());
            }
            if (updateEventRequest.getLength() != null) {
                eventType.setLength(updateEventRequest.getLength());
            }
            if (updateEventRequest.getPrice() != null) {
                eventType.setPrice(updateEventRequest.getPrice());
            }
            if (updateEventRequest.getScheduleId() != null) {
                if (scheduleRepository.findByScheduleId(updateEventRequest.getScheduleId()) != null) {
                    List<AvailabilityV2> availabilityV2s = availabilityV2Repository.findByScheduleId(updateEventRequest.getScheduleId());
                    if (!availabilityV2Repository.findByScheduleId(updateEventRequest.getScheduleId()).isEmpty()) {
                        eventType.setScheduleId(updateEventRequest.getScheduleId());
                    } else {
                        return new ResponseEntity<>(new CreateEventResponse
                                ("0",
                                        "This scheduleId does not contain any availability:", null), HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>(new CreateEventResponse
                            ("0",
                                    "This scheduleId does not exist:", null), HttpStatus.BAD_REQUEST);
                }
                eventType.setScheduleId(updateEventRequest.getScheduleId());
            }
        }
            EventType eventType1 = eventTypesRepository.save(eventType);
            CreateEventDTO createEventDTO = EventMapper.convertEntityToDTO1(eventType1);
            return new ResponseEntity<>(new CreateEventResponse
                    ("1",
                            "The Event has been updated:", createEventDTO), HttpStatus.OK);


    }
    @Transactional
    @Override
   public ResponseEntity<DeleteEventResponse>deleteAEvent(UUID eventId, UUID userId){
        if(userId==null){
            return new ResponseEntity<>(new DeleteEventResponse
                    (
                            "0",
                            "Invalid Request" ,
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        EventType eventType=eventTypesRepository.findByUserIdAndEventId(eventId,userId);
        //null check
        //use the automated delete from repository,send the object

        if(eventType==null){
            return new ResponseEntity<>(new DeleteEventResponse
                    (
                            "0",
                            "The required userId or eventId does not exist " ,
                            null
                    ), HttpStatus.BAD_REQUEST);

        }
        eventTypesRepository.delete(eventType);


        return new ResponseEntity<>(new DeleteEventResponse
                ("1",
                        "The Event is Deleted:",eventId), HttpStatus.OK);



    }



}
