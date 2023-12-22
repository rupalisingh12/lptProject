package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.UserController.UserGetResponseDto;
import com.leanplatform.MentorshipPlatform.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMapper {
    public static List<UserGetResponseDto>convertUserEntityToDto(Optional<UserEntity> usersList){
        List<UserGetResponseDto> response = new ArrayList<>();

        if (usersList.isPresent()) {
            UserEntity userEntity = usersList.get();
            UserGetResponseDto userGetResponseDto = new UserGetResponseDto();
//            userGetResponseDto.setUser_name(userEntity.getUser_name());
//            userGetResponseDto.setName(userEntity.getName());
            userGetResponseDto.setAvatar(userEntity.getAvatar());
            userGetResponseDto.setBio(userEntity.getBio());
            userGetResponseDto.setEmail(userEntity.getEmail());
            userGetResponseDto.setDefaultScheduleId(userEntity.getDefaultScheduleId());
            response.add(userGetResponseDto);


    }
        return response;
    }
}
