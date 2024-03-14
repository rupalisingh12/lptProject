package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.UserController.UserGetResponse;
import com.leanplatform.MentorshipPlatform.dto.UserController.UserGetResponseDto;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.UserEntity;
import com.leanplatform.MentorshipPlatform.mappers.MentorFunctionalityMapper.UserMapper;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.UserRepository;
import com.leanplatform.MentorshipPlatform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<UserGetResponse> getUsers(UUID userId){
        Optional<UserEntity> usersList=userRepository.findById(userId);
         if(usersList.isPresent()){
             UserGetResponseDto userGetResponseDtoList=UserMapper.convertUserEntityToDto(usersList);
             return new ResponseEntity<>(new UserGetResponse(
                     "1","The required user details are"
                     ,userGetResponseDtoList),HttpStatus.OK);
         }
         else{
             return new ResponseEntity<>(new UserGetResponse(
                     "0","No user with the " +
                     "reqiured userId exist",null),HttpStatus.NOT_FOUND);
         }

    }

}
