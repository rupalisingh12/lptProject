package com.leanplatform.MentorshipPlatform.services.CustomForm;


import com.leanplatform.MentorshipPlatform.dto.CustomForm.GetFormStructureAdminDto;
import com.leanplatform.MentorshipPlatform.dto.CustomForm.FormResponseData;
import com.leanplatform.MentorshipPlatform.dto.CustomForm.FormStructureDto;
import com.leanplatform.MentorshipPlatform.dto.CustomForm.FormStructureRequestDto;
import com.leanplatform.MentorshipPlatform.dto.CustomForm.FormStructureResponse;

import java.io.IOException;

public interface FormStructureService {
    FormStructureResponse createForm(String title, FormStructureDto formStructureDto) throws IOException;

    FormResponseData getForm(FormStructureRequestDto formStructureRequestDto);

    FormStructureResponse updateForm(String title, FormStructureDto formStructureDto) throws IOException;

    GetFormStructureAdminDto getFormDetails(String title);
}
