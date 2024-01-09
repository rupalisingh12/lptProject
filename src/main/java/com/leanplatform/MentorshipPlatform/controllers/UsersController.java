package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.MentorController.MentorAddedServiceResponse;
import com.leanplatform.MentorshipPlatform.dto.UserController.UserGetResponse;
import com.leanplatform.MentorshipPlatform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/User")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public ResponseEntity<UserGetResponse> getUser(@RequestParam(name="userId") UUID userId){
        if (userId == null ) {
            return new ResponseEntity<>(new UserGetResponse
                    (
                            "0",
                            "Invalid Request",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        try{
            return userService.getUsers(userId);

        }
        catch(Exception e){return new ResponseEntity<>
                (new UserGetResponse
                        ("0","Error"+e.getLocalizedMessage(),
                                null), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
