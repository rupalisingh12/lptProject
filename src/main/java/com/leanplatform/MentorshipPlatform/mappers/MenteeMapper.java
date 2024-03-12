package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeAccount;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Mentee;

public class MenteeMapper {
    public static Mentee convertDtoToEntity(MenteeAccount menteeAccount){
        Mentee mentee = new Mentee();
        if (!menteeAccount.getFirstName().isEmpty()) {
            mentee.setFirstName(menteeAccount.getFirstName());
        } else {
            mentee.setFirstName("User did not set first name");
        }
        if (!menteeAccount.getLastName().isEmpty()) {
            mentee.setLastName(menteeAccount.getLastName());
        } else {
            mentee.setLastName("User did not set last name");
        }
        if (!menteeAccount.getPhoneNo().isEmpty()) {
            mentee.setPhoneNo(menteeAccount.getPhoneNo());
        } else {
            mentee.setPhoneNo("User did not provide phone number");
        }
        if (!menteeAccount.getEmail().isEmpty()) {
            mentee.setEmail(menteeAccount.getEmail());
        } else {
            mentee.setEmail("User did not provide email");
        }

        return mentee;
    }
}
