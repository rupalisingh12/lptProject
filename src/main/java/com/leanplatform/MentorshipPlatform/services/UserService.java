package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.UserController.UserGetResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;


public interface UserService {
    public ResponseEntity<UserGetResponse> getUsers(UUID userId);
}
