package com.leanplatform.MentorshipPlatform.services.implementation.CustomForm;

import com.leanplatform.MentorshipPlatform.mappers.CustomForm.FormResponseConverter;
import com.leanplatform.MentorshipPlatform.dto.CustomForm.GenericResponseObject;
import com.leanplatform.MentorshipPlatform.dto.CustomForm.SubmitFormRequestObject;
import com.leanplatform.MentorshipPlatform.entities.CustomForm.FormResponse;
import com.leanplatform.MentorshipPlatform.entities.CustomForm.FormStructure;
import com.leanplatform.MentorshipPlatform.repositories.CustomForm.FormResponseRepository;
import com.leanplatform.MentorshipPlatform.repositories.CustomForm.FormStructureRepository;
import com.leanplatform.MentorshipPlatform.services.CustomForm.FormResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormResponseServiceImpl implements FormResponseService {

    @Autowired
    FormResponseRepository formResponseRepository;
    @Autowired
    FormStructureRepository formStructureRepository;
    @Override
    public GenericResponseObject submitForm(SubmitFormRequestObject submitFormRequestObject) {
        Optional<FormResponse> formResponse = formResponseRepository.findById(submitFormRequestObject.getResponseId());
        if(formResponse.isEmpty())
            return GenericResponseObject.builder().responseCode(0).message("Invalid id , no such form response exists").build();

        //To increase the form submission count by 1
        Optional<FormStructure> formStructure = formStructureRepository.findByTitle(formResponse.get().getTitle());
        if(formStructure.isEmpty())
            return GenericResponseObject.builder().responseCode(0).message("No such form exists").build();
        formStructure.get().setTotalResponses(formStructure.get().getTotalResponses()+1);
        formStructureRepository.save(formStructure.get());

        FormResponse saveFormResponse  = FormResponseConverter.dtoToEntityConverter(submitFormRequestObject,formResponse.get());
        formResponseRepository.save(saveFormResponse);

        return GenericResponseObject.builder().responseCode(1).message("Form has been submitted successfully").build();

    }
}
