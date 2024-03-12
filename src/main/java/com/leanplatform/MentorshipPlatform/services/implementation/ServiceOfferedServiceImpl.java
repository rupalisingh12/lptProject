package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.ServicesOfferedController.AddServiceResponse;
import com.leanplatform.MentorshipPlatform.dto.ServicesOfferedController.ServiceToAdd;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.ServicesOffered;
import com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository.ServicesOfferedRepository;
import com.leanplatform.MentorshipPlatform.services.ServicesOfferedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ServiceOfferedServiceImpl implements ServicesOfferedService {

    @Autowired
    private ServicesOfferedRepository servicesOfferedRepository;
    @Override
    public ResponseEntity<AddServiceResponse> createNewService(@RequestBody ServiceToAdd serviceToAdd) {
        if(serviceToAdd == null || serviceToAdd.getServiceOffered() == null){
            return new ResponseEntity<>(new AddServiceResponse
                    (
                            "0",
                            "Can not add service due to Null object fetched.",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        ServicesOffered newServiceOffered = new ServicesOffered();
        if (!serviceToAdd.getServiceOffered().isEmpty()) {
            newServiceOffered.setServiceOffered(serviceToAdd.getServiceOffered());
        } else {
            newServiceOffered.setServiceOffered("Empty Service given.");
        }
        servicesOfferedRepository.save(newServiceOffered);
        return new ResponseEntity<>(new AddServiceResponse
                (
                        "1",
                        "New Service Added",
                        newServiceOffered
                ), HttpStatus.CREATED);
    }
}
