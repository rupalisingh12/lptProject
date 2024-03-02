package com.leanplatform.MentorshipPlatform.mappers.CustomForm;



import com.leanplatform.MentorshipPlatform.dto.CustomForm.GetFormStructureAdminDto;
import com.leanplatform.MentorshipPlatform.dto.CustomForm.FormStructureData;
import com.leanplatform.MentorshipPlatform.dto.CustomForm.FormStructureDto;
import com.leanplatform.MentorshipPlatform.entities.CustomForm.FormStructure;

import java.time.LocalDateTime;
import java.util.UUID;

public class FormStructureConverter {

    //This is used while creation of the form
    public static FormStructure dtoToEntityConverterCreate(String title, FormStructureDto formStructureDto, String formUrl, String posterUrl)
    {
       FormStructure formStructure = new FormStructure();
       if(formStructureDto.getName()!=null)formStructure.setName(formStructureDto.getName());
       if(formStructureDto.getEmail()!=null)formStructure.setEmail(formStructureDto.getEmail());
       if(formStructureDto.getPhoneNumber()!=null)formStructure.setPhoneNumber(formStructureDto.getPhoneNumber());
       if(formStructureDto.getNavigateTo()!=null) formStructure.setNavigateTo(formStructureDto.getNavigateTo());
       if(formStructureDto.getDescription()!=null) formStructure.setDescription(formStructureDto.getDescription());
       if(posterUrl!=null)formStructure.setPosterUrl(posterUrl);

        if (formStructureDto.getOthers() != null && formStructureDto.getOthers().equalsIgnoreCase("Experience")) {
            formStructure.setExperience(true);
            formStructure.setOthers("Experience");
        } else if (formStructureDto.getOthers() != null) {
            formStructure.setOthers(formStructureDto.getOthers());
        }
        formStructure.setTitle(title);
        //formStructure.setCreatedBy(userId);
        LocalDateTime IST_time = LocalDateTime.now().plusHours(5).plusMinutes(30);
        formStructure.setCreatedAt(IST_time);
        formStructure.setFormUrl(formUrl);

       return  formStructure;
    }

    //This is used while updating  the form
    public static FormStructure dtoToEntityConverterUpdate( FormStructureDto formStructureDto,FormStructure formStructure,String posterUrl)
    {
        if(formStructureDto.getName()!=null)formStructure.setName(formStructureDto.getName());
        if(formStructureDto.getEmail()!=null)formStructure.setEmail(formStructureDto.getEmail());
        if(formStructureDto.getPhoneNumber()!=null)formStructure.setPhoneNumber(formStructureDto.getPhoneNumber());
        if(formStructureDto.getNavigateTo()!=null) formStructure.setNavigateTo(formStructureDto.getNavigateTo());
        if(formStructureDto.getDescription()!=null) formStructure.setDescription(formStructureDto.getDescription());
        if(posterUrl!=null) formStructure.setPosterUrl(posterUrl);

        if (formStructureDto.getOthers() != null && formStructureDto.getOthers().equalsIgnoreCase("Experience")) {
            formStructure.setExperience(true);
            formStructure.setOthers("Experience");
        } else if (formStructureDto.getOthers() != null) {
            formStructure.setOthers(formStructureDto.getOthers());
        }
        LocalDateTime IST_time = LocalDateTime.now().plusHours(5).plusMinutes(30);
        formStructure.setModifiedAt(IST_time);
        //formStructure.setModifiedBy(userId);
        return  formStructure;
    }

    //Get Form Structure for frontend
    public static FormStructureData entityToDtoConverter(FormStructure formStructure, UUID visitorId, int response_id)
    {
        FormStructureData formStructureData = new FormStructureData();

        formStructureData.setName(formStructure.getName());
        formStructureData.setTitle(formStructure.getTitle());
        formStructureData.setEmail(formStructure.getEmail());
        formStructureData.setExperience(formStructure.isExperience());
        formStructureData.setNavigateTo(formStructure.getNavigateTo());
        formStructureData.setOthers(formStructure.getOthers());
        formStructureData.setPhoneNumber(formStructure.getPhoneNumber());
        formStructureData.setVisitorId(visitorId);
        formStructureData.setResponseId(response_id);
        formStructureData.setDescription(formStructure.getDescription());
        formStructureData.setPosterUrl(formStructure.getPosterUrl());

        return formStructureData;
    }

    //Get Form Structure
    public static GetFormStructureAdminDto entityToDtoConverter(FormStructure formStructure)
    {
         return GetFormStructureAdminDto.builder().responseCode(1).message("Form details fetched successfully").name(formStructure.getName()).email(formStructure.getEmail())
                 .formUrl(formStructure.getFormUrl()).createdAt(formStructure.getCreatedAt()).modifiedAt(formStructure.getModifiedAt())
                 .navigateTo(formStructure.getNavigateTo()).others(formStructure.getOthers())
                 .phoneNumber(formStructure.getPhoneNumber()).totalResponses(formStructure.getTotalResponses())
                 .totalOpens(formStructure.getTotalOpens()).title(formStructure.getTitle()).isExperience(formStructure.isExperience())
                 .description(formStructure.getDescription()).posterUrl(formStructure.getPosterUrl())
                 .build();
    }
}

