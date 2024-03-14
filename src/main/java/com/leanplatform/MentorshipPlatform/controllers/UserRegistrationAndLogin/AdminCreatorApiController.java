package com.leanplatform.MentorshipPlatform.controllers.UserRegistrationAndLogin;

import com.leanplatform.MentorshipPlatform.dto.GenericResponseObject;
import com.leanplatform.MentorshipPlatform.services.User.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCreatorApiController {

    @Autowired
    CreatorService creatorService;

    @GetMapping("/admin/user/addExpertRole")
    public GenericResponseObject addExpertRole(@RequestParam("phoneNo")String phoneNo, @RequestParam("linkedInUrl")String linkedInUrl){

        try {
            return creatorService.addExpertRole(phoneNo,linkedInUrl);
        }
        catch (Exception e){
            return GenericResponseObject.builder().responseCode(0).message("Error: "+e.getLocalizedMessage()).build();
        }
    }
}
