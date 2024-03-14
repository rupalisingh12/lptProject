package com.leanplatform.MentorshipPlatform.services.User;

import com.leanplatform.MentorshipPlatform.dto.GenericResponseObject;
import com.leanplatform.MentorshipPlatform.dto.UserRegistration.JwtResponse;
import com.leanplatform.MentorshipPlatform.dto.UserRegistration.PinDto;
import com.leanplatform.MentorshipPlatform.dto.UserRegistration.CreatorDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.leanplatform.MentorshipPlatform.dto.UserRegistration.ResponseObj;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface CreatorService {

    ResponseEntity<ResponseObj> registerUser(CreatorDto creatorDto);

    ResponseEntity<ResponseObj> verifyOtp(CreatorDto creatorDto) throws JsonProcessingException, ExecutionException, InterruptedException;

    ResponseEntity<ResponseObj> setPin(CreatorDto creatorDto, String data);

    ResponseEntity<ResponseObj> deleteUser(String username);

    ResponseEntity<JwtResponse> authenticateAndGetToken(String data);

    ResponseEntity<ResponseObj> changePin(PinDto dto);

    ResponseEntity<ResponseObj> sendOtpReset(PinDto dto) throws IOException, ExecutionException, InterruptedException;

    ResponseEntity<ResponseObj> resetPin(PinDto dto);

//    void processOAuthPostLogin(String email,String name,String phoneNo);

    ResponseEntity<ResponseObj> checkUser(CreatorDto creatorDto);

    ResponseEntity<ResponseObj> getDetails();

    void verifyEmail(HttpServletResponse response, String data) throws IOException;

    ResponseObj checkEmail(String email);

    GenericResponseObject addExpertRole(String phoneNo, String linkedInUrl);
}
