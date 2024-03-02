package com.leanplatform.MentorshipPlatform.controllers.CustomForm;


import com.leanplatform.MentorshipPlatform.dto.CustomForm.*;
import com.leanplatform.MentorshipPlatform.services.CustomForm.FormResponseService;
import com.leanplatform.MentorshipPlatform.services.CustomForm.FormStructureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("form")
public class CustomFormController {
    @Autowired
    FormStructureService formStructureService;

    @Autowired
    FormResponseService formResponseService;

@PostMapping("/create")
FormStructureResponse createForm(@ModelAttribute FormStructureDto formStructureDto, @RequestParam("title") String title)
   {
       MultipartFile poster = formStructureDto.getPoster();
       if (poster != null && !poster.isEmpty() &&
               !("image/jpeg".equals(poster.getContentType()) || "image/png".equals(poster.getContentType()) || "image/svg+xml".equals(poster.getContentType()))) {
           return FormStructureResponse.builder().responseCode(0).message("Incorrect Format, Only jpeg, png, or svg images are supported").build();
       }

       try{
           return formStructureService.createForm(title,formStructureDto);
       }
       catch (Exception e){
           return FormStructureResponse.builder().responseCode(0).message("Error: "+e.getLocalizedMessage()).build();
       }
   }

   //The api used by frontend to get the structure of the form
   @PostMapping("/get")
    FormResponseData getForm(@RequestBody FormStructureRequestDto formStructureRequestDto)
   {
       if(formStructureRequestDto.getTitle().length() == 0)
           return FormResponseData.builder().responseCode(0).message("Please provide a title to get the form details").build();

       try{
           return formStructureService.getForm(formStructureRequestDto);
       }
       catch (Exception e){
           return FormResponseData.builder().responseCode(0).message("Error: "+e.getLocalizedMessage()).build();
       }

   }

    @PostMapping("/submit")
    GenericResponseObject submitForm(@Valid @RequestBody SubmitFormRequestObject submitFormRequestObject)
    {

        try{
            return formResponseService.submitForm(submitFormRequestObject);
        }
        catch (Exception e){
            return GenericResponseObject.builder().responseCode(0).message("Error: "+e.getLocalizedMessage()).build();
        }

    }

   @PostMapping("/update")
   FormStructureResponse updateForm( @ModelAttribute FormStructureDto formStructureDto, @RequestParam("title") String title)
   {
       MultipartFile poster = formStructureDto.getPoster();
       if (poster != null && !poster.isEmpty() &&
               !("image/jpeg".equals(poster.getContentType()) || "image/png".equals(poster.getContentType()) || "image/svg+xml".equals(poster.getContentType()))) {
           return FormStructureResponse.builder().responseCode(0).message("Incorrect Format, Only jpeg, png, or svg images are supported").build();
       }


       try{
           return formStructureService.updateForm(title,formStructureDto);
       }
       catch (Exception e){
           return FormStructureResponse.builder().responseCode(0).message("Error: "+e.getLocalizedMessage()).build();
       }
   }

   //The api needed by the admin to get the form structure and stats
   @GetMapping("/getFormDetails") GetFormStructureAdminDto getFormStructure(@RequestParam("title") String title)
   {
       try{
           return formStructureService.getFormDetails(title);
       }
       catch (Exception e){
           return GetFormStructureAdminDto.builder().responseCode(0).message("Error: "+e.getLocalizedMessage()).build();
       }
   }

}
