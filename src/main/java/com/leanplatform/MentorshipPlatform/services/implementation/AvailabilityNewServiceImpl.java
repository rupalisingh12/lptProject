package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewRequest;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewResponse;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestResponse;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;
import com.leanplatform.MentorshipPlatform.entities.Days;
import com.leanplatform.MentorshipPlatform.enums.DaysOfTheWeek;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityNewMapper;
import com.leanplatform.MentorshipPlatform.repositories.AvailabilityNewRepository;
import com.leanplatform.MentorshipPlatform.repositories.DaysRepository;
import com.leanplatform.MentorshipPlatform.services.AvailabilityNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class AvailabilityNewServiceImpl implements AvailabilityNewService {
    @Autowired AvailabilityNewRepository availabilityNewRepository;
    @Autowired
    DaysRepository daysRepository;
    public ResponseEntity<CreateAvailabilityNewResponse> addAnAvailability(UUID scheduleId, CreateAvailabilityNewRequest createAvailabilityNewRequest) {
        AvailabilityNew availabilityNew=new AvailabilityNew();
        availabilityNew.setScheduleId(createAvailabilityNewRequest.getScheduleId());
        availabilityNew.setStartTime(createAvailabilityNewRequest.getStartTime());
        availabilityNew.setEndTime(createAvailabilityNewRequest.getEndTime());
        AvailabilityNew availabilityNew1=availabilityNewRepository.save(availabilityNew);
        for(int i=0;i<createAvailabilityNewRequest.getDays().size();i++){
            Days day = new Days();
            Long ans=createAvailabilityNewRequest.getDays().get(i);
            day.setAvailabilityId(availabilityNew1.getAvailabilityId());
            day.setDay(ans);
            day.setScheduleId(availabilityNew1.getScheduleId());
            daysRepository.save(day);



        }
        List<Long>ans=daysRepository.findDistinctDaysByScheduleId(availabilityNew1.getScheduleId());




        CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO=AvailabilityNewMapper.convertDtoToEntity(  availabilityNew,ans);

        return new ResponseEntity<>(new CreateAvailabilityNewResponse
                (
                        "1",
                        "Availability created",createAvailabilityNewResponseDTO

                ), HttpStatus.CREATED);




    }

}
