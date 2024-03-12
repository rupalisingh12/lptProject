package com.leanplatform.MentorshipPlatform.mappers.MentorMapper;

import com.leanplatform.MentorshipPlatform.dto.MentorController.ServicesByMentorDto;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.ServicesByMentors;

import java.util.List;

public class ServicesByMentorsMapper {
    public static List<ServicesByMentorDto> convertEntityToDto(List<ServicesByMentors> servicesByMentors){
        List<ServicesByMentorDto> servicesByMentorDtos = null;
        for(ServicesByMentors s : servicesByMentors){
            ServicesByMentorDto servicesByMentorDto = new ServicesByMentorDto();
            servicesByMentorDto.setService_id(s.getService_id());
            servicesByMentorDto.setPrice(s.getPrice());
            servicesByMentorDtos.add(servicesByMentorDto);
        }
        return servicesByMentorDtos;
    }
}
