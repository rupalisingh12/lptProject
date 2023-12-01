package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.ServicesOfferedController.AddServiceResponse;
import com.leanplatform.MentorshipPlatform.dto.ServicesOfferedController.ServiceToAdd;
import com.leanplatform.MentorshipPlatform.services.ServicesOfferedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/services")
public class ServicesOfferedController {

    @Autowired
    private ServicesOfferedService servicesOfferedService;

    @PostMapping("/add-service")
    public ResponseEntity<AddServiceResponse> createNewService(@RequestBody ServiceToAdd serviceToAdd) {
        if(serviceToAdd == null){
            return new ResponseEntity<>(new AddServiceResponse
                    (
                            "0",
                            "Can not add service",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        try {
            return servicesOfferedService.createNewService(serviceToAdd) ;
        } catch (Exception e) {
            return new ResponseEntity<>(new AddServiceResponse
                    (
                            "0",
                            "Can not add service",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
    }
}
