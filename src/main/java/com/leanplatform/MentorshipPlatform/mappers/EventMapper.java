package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.EventTypesController.CreateEventDTO;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestObject;
import com.leanplatform.MentorshipPlatform.entities.EventType;
import com.leanplatform.MentorshipPlatform.entities.MentorRequest;

import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    public static  List<CreateEventDTO> convertEntityToDTO(List<EventType>eventType) {
        List<CreateEventDTO> createEventDTOS=new ArrayList<>();
        // CreateEventDTO createEventDTO=new CreateEventDTO();
        for(int i=0;i<eventType.size();i++){
            EventType element=eventType.get(i);
            CreateEventDTO ans=greatEvent(element);
            createEventDTOS.add(ans);


        }
        return createEventDTOS;


    }
    public static CreateEventDTO greatEvent(EventType element){
        CreateEventDTO createEventDTO=new CreateEventDTO();
        createEventDTO.setId(element.getEventId());
        createEventDTO.setScheduleId(element.getScheduleId());
        createEventDTO.setDescription(element.getDescription());
        createEventDTO.setTitle(element.getTitle());
        createEventDTO.setHidden(element.getHidden());
        createEventDTO.setPrice(element.getPrice());
        createEventDTO.setLength(element.getLength());
        createEventDTO.setUserId(element.getUserId());
        return createEventDTO;
    }



    public static  CreateEventDTO convertEntityToDTO1( EventType eventType) {

        CreateEventDTO createEventDTO=new CreateEventDTO();

            createEventDTO.setId(eventType.getEventId());
            createEventDTO.setScheduleId(eventType.getScheduleId());
            createEventDTO.setDescription(eventType.getDescription());
            createEventDTO.setTitle(eventType.getTitle());
            createEventDTO.setHidden(eventType.getHidden());
            createEventDTO.setPrice(eventType.getPrice());
            createEventDTO.setLength(eventType.getLength());
            createEventDTO.setUserId(eventType.getUserId());
            return createEventDTO;

        }



}
