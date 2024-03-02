package com.leanplatform.MentorshipPlatform.services.CustomForm;


import com.leanplatform.MentorshipPlatform.dto.CustomForm.GenericResponseObject;
import com.leanplatform.MentorshipPlatform.dto.CustomForm.SubmitFormRequestObject;

public interface FormResponseService {
    GenericResponseObject submitForm(SubmitFormRequestObject submitFormRequestObject);
}
