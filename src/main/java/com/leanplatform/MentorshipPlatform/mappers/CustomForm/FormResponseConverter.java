package com.leanplatform.MentorshipPlatform.mappers.CustomForm;



import com.leanplatform.MentorshipPlatform.dto.CustomForm.SubmitFormRequestObject;
import com.leanplatform.MentorshipPlatform.entities.CustomForm.FormResponse;

import java.time.LocalDateTime;

public class FormResponseConverter {

    public static FormResponse dtoToEntityConverter(SubmitFormRequestObject submitFormRequestObject, FormResponse formResponse)
    {
           formResponse.setName(submitFormRequestObject.getName());
           formResponse.setEmail(submitFormRequestObject.getEmail());
           formResponse.setOthers(submitFormRequestObject.getOthers());
           formResponse.setPhoneNumber(submitFormRequestObject.getPhoneNumber());
           formResponse.setSubmittedAt(LocalDateTime.now().plusHours(5).plusMinutes(30));

           return formResponse;
    }
}
