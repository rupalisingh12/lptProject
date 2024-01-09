package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.UserController.UserGetResponseDto;
import com.leanplatform.MentorshipPlatform.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMapper {
    public static UserGetResponseDto convertUserEntityToDto(Optional<UserEntity> usersList){
      //  UserGetResponseDto response = new UserGetResponseDto();
        UserGetResponseDto userGetResponseDto = new UserGetResponseDto();
        if (usersList.isPresent()) {
            UserEntity userEntity = usersList.get();
         //   UserGetResponseDto userGetResponseDto = new UserGetResponseDto();
            userGetResponseDto.setUsername(userEntity.getUserName());
           userGetResponseDto.setName(userEntity.getName());
            userGetResponseDto.setAvatar(userEntity.getAvatar());
            userGetResponseDto.setBio(userEntity.getBio());
            userGetResponseDto.setEmail(userEntity.getEmail());
            userGetResponseDto.setCreatedDate(userEntity.getCreatedAt());
            userGetResponseDto.setId(userEntity.getUserId());
            userGetResponseDto.setDefaultScheduleId(userEntity.getDefaultScheduleId());



    }
        return userGetResponseDto;
    }
}
