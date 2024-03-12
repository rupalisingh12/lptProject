package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeAccount;
import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeAdditionalDetails;
import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeModified;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Mentee;
import com.leanplatform.MentorshipPlatform.mappers.MenteeMapper;
import com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository.MenteeRepository;
import com.leanplatform.MentorshipPlatform.services.MenteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenteeServiceImpl implements MenteeService {

    @Autowired
    private MenteeRepository menteeRepository ;
    @Override
    public ResponseEntity<MenteeModified> createMenteeAccount(MenteeAccount menteeAccount) {
        if (menteeAccount == null ||
                menteeAccount.getFirstName() == null ||
                menteeAccount.getLastName() == null ||
                menteeAccount.getEmail() == null ||
                menteeAccount.getPhoneNo() == null) {
            return new ResponseEntity<>(new MenteeModified
                    (
                            "0",
                            "Invalid Request to Add account , null request object received."
                    ), HttpStatus.BAD_REQUEST);
        }

        menteeRepository.save(MenteeMapper.convertDtoToEntity(menteeAccount));

        return new ResponseEntity<>(new MenteeModified
                (
                        "1",
                        "Mentee Account Successfully created."
                ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MenteeModified> updateMenteeAccount(MenteeAdditionalDetails menteeAdditionalDetails) {
        if (menteeAdditionalDetails == null) {
            return new ResponseEntity<>(new MenteeModified
                    (
                            "0",
                            "Invalid Request to Update account , null request object received."
                    ), HttpStatus.BAD_REQUEST);
        }
        Optional<Mentee> optionalMentee =  menteeRepository.findById(menteeAdditionalDetails.getMenteeId());
        if (optionalMentee.isPresent()){
            Mentee existingMentee = optionalMentee.get();

            if (menteeAdditionalDetails.getAge() != null && menteeAdditionalDetails.getAge()>0) {
                existingMentee.setAge(menteeAdditionalDetails.getAge());
            }
            if (menteeAdditionalDetails.getEmployed() != null) {
                existingMentee.setEmployed(menteeAdditionalDetails.getEmployed());
            }
            if (menteeAdditionalDetails.getEducationalQualification() != null) {
                existingMentee.setEducationalQualification(menteeAdditionalDetails.getEducationalQualification());
            }
            if (menteeAdditionalDetails.getJobRole() != null) {
                existingMentee.setJobRole(menteeAdditionalDetails.getJobRole());
            }

            menteeRepository.save(existingMentee);

        }else {
            return new ResponseEntity<>(new MenteeModified
                    (
                            "0",
                            "Invalid Request to Update account , Mentee doesn't exist."
                    ), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new MenteeModified
                (
                        "1",
                        "Mentee Account Successfully updated."
                ), HttpStatus.OK);
    }

    @Override
    public boolean isEmailAlreadyExists(String email) {
        return menteeRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean isPhoneNumberAlreadyExists(String phoneNo) {
        return menteeRepository.findByPhoneNo(phoneNo).isPresent();
    }


}
