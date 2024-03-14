package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.MentorController.*;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.ServicesByMentors;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.ServicesOffered;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.ServicesByMentorsRepository;
import com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository.ServicesOfferedRepository;
import com.leanplatform.MentorshipPlatform.services.ServicesByMentorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesByMentorsImpl implements ServicesByMentorsService {

    @Autowired
    private ServicesByMentorsRepository servicesByMentorsRepository;
    @Autowired
    private ServicesOfferedRepository servicesOfferedRepository;
    @Override
    public ResponseEntity<MentorAddedServiceResponse> addServiceByMentor(MentorAddsServices mentorAddsServices) {
        if (mentorAddsServices == null ||
                mentorAddsServices.getService_id() == null ||
                mentorAddsServices.getPrice() == null) {
            return new ResponseEntity<>(new MentorAddedServiceResponse
                    (
                            "0",
                            "Invalid Request",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        Optional<ServicesOffered> servicesByMentorsOptional = servicesOfferedRepository.findById(mentorAddsServices.getService_id());
        if(servicesByMentorsOptional.isPresent()) {
            ServicesByMentors servicesByMentors = new ServicesByMentors();
            servicesByMentors.setMentor_id(mentorAddsServices.getMentor_id());
            servicesByMentors.setService_id(mentorAddsServices.getService_id());
            if (mentorAddsServices.getPrice()>0) {
                servicesByMentors.setPrice(mentorAddsServices.getPrice());
            } else {
                servicesByMentors.setPrice(null);
            }
            servicesByMentorsRepository.save(servicesByMentors);
        }else {
            return new ResponseEntity<>(new MentorAddedServiceResponse
                    (
                            "0",
                            "Invalid mentor ID",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new MentorAddedServiceResponse
                (
                        "1",
                        "Service by a mentor has been added successfully",
                        mentorAddsServices
                ), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UpdateMentorServiceResponse> serviceUpdate(UpdateMentorServiceObject updateMentorServiceObject) {
        if (updateMentorServiceObject == null ||
                updateMentorServiceObject.getId() == null ||
                updateMentorServiceObject.getPrice() == null) {
            return new ResponseEntity<>(new UpdateMentorServiceResponse
                    (
                            "0",
                            "Invalid Request : Null object received."
                    ), HttpStatus.BAD_REQUEST);
        }
        Optional<ServicesByMentors> servicesByMentorsOptional = servicesByMentorsRepository.findById(updateMentorServiceObject.getId());
        if (servicesByMentorsOptional.isPresent()){
            ServicesByMentors existingServiceByMentor = servicesByMentorsOptional.get();
            if (updateMentorServiceObject.getPrice()>0) {
                existingServiceByMentor.setPrice(updateMentorServiceObject.getPrice());
            } else {
                existingServiceByMentor.setPrice(null);
            }
            servicesByMentorsRepository.save(existingServiceByMentor);
        }else {
            return new ResponseEntity<>(new UpdateMentorServiceResponse
                    (
                            "0",
                            "Requested Resource Not Found."
                    ), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UpdateMentorServiceResponse
                (
                        "1",
                        "The requested service has been edited."
                ), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DeleteMentorServiceResponse> deleteService(DeleteMentorServiceObject deleteMentorServiceObject) {
        if (deleteMentorServiceObject == null ||
                deleteMentorServiceObject.getId() == null ){

            return new ResponseEntity<>(new DeleteMentorServiceResponse
                    (
                            "0",
                            "Invalid Object - Null Object received"
                    ),HttpStatus.BAD_REQUEST);
        }
        Optional<ServicesByMentors> serviceByMentorsOptional = servicesByMentorsRepository.findById(deleteMentorServiceObject.getId());
        if (serviceByMentorsOptional.isPresent()){
            ServicesByMentors existingServiceByMentor = serviceByMentorsOptional.get();
            servicesByMentorsRepository.delete(existingServiceByMentor);
        }else {
            return new ResponseEntity<>(new DeleteMentorServiceResponse
                    (
                            "0",
                            "Mentor Service with the given ID does not exist."
                    ),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DeleteMentorServiceResponse
                (
                        "1",
                        "Mentor Service Successfully Deleted."
                ),HttpStatus.NO_CONTENT);
    }

}
