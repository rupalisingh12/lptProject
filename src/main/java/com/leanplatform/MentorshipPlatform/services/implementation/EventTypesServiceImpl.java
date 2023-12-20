package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.EventTypesController.*;

import com.leanplatform.MentorshipPlatform.dto.OverallStats.ActiveMentorsResponse;
import com.leanplatform.MentorshipPlatform.dto.OverallStats.ActiveMentorsResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.EventType;
import com.leanplatform.MentorshipPlatform.mappers.BookingMapper;
import com.leanplatform.MentorshipPlatform.mappers.EventMapper;
import com.leanplatform.MentorshipPlatform.mappers.StatsMapper;
import com.leanplatform.MentorshipPlatform.repositories.EventTypesRepository;
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
        if (createEventRequestObject.getPrice()>0) {
            eventType.setPrice(createEventRequestObject.getPrice());

        } else {
            eventType.setPrice(null);
        }
        eventType.setPrice(createEventRequestObject.getPrice());
        eventType.setTitle(createEventRequestObject.getTitle());
        eventType.setDescription(createEventRequestObject.getDescription());
        eventTypesRepository.save(eventType);
        CreateEventDTO createEventDTO=new CreateEventDTO();
        createEventDTO.setDescription(eventType.getDescription());
        createEventDTO.setLength(eventType.getLength());
        createEventDTO.setPrice(eventType.getPrice());
        createEventDTO.setTitle(eventType.getTitle());
        createEventDTO.setUserId(userId);
        createEventDTO.setHidden(eventType.getHidden());
        createEventDTO.setScheduleId(eventType.getScheduleId());
        return new ResponseEntity<>(new 
                CreateEventResponse("0",
                "The event is created",createEventDTO),HttpStatus.CREATED);



    }
    @Override
    public ResponseEntity<GetAllEventResponse>getALLEventsOfAUser(UUID userId){
        List<EventType> eventType= eventTypesRepository.findAllByUserId(userId);
        if (eventType.isEmpty()) {
            return new ResponseEntity<>(new GetAllEventResponse
                    ("0",
                            "No events found",
                            null), HttpStatus.NOT_FOUND);
        }
        List<CreateEventDTO> createEventDTOList = EventMapper.convertEntityToDTO(eventType);
        return new ResponseEntity<>(new GetAllEventResponse
                ("1",
                        "The list of events is:",createEventDTOList), HttpStatus.OK);




    }
    @Override
    public ResponseEntity<CreateEventResponse>getEventOfAUser(UUID eventId,UUID userId){
        EventType eventType= eventTypesRepository.findByUserIdAndEventId(eventId,userId);
        CreateEventDTO createEventDTO  = EventMapper.convertEntityToDTO1(eventType);
        return new ResponseEntity<>(new CreateEventResponse
                ("1",
                        "The Events is:",createEventDTO), HttpStatus.OK);




    }
//    @Override
//   public ResponseEntity<CreateEventResponse>updateEvent(UUID eventId,UUID userId){
//        EventType eventType= eventTypesRepository.findByUserIdAndEventId(eventId,userId);
//
//    }
    @Transactional
    @Override
   public ResponseEntity<DeleteEventResponse>deleteAEvent(UUID eventId, UUID userId){
        eventTypesRepository.deleteByUserIdAndEventId(eventId,userId);

        return new ResponseEntity<>(new DeleteEventResponse
                ("1",
                        "The Events is:",eventId), HttpStatus.OK);



    }



}
