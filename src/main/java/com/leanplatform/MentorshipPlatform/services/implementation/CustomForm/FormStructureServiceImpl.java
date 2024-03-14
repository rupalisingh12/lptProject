package com.leanplatform.MentorshipPlatform.services.implementation.CustomForm;

import com.leanplatform.MentorshipPlatform.dto.CustomForm.*;
import com.leanplatform.MentorshipPlatform.dto.CustomForm.FormStructureResponse;
import com.leanplatform.MentorshipPlatform.entities.CustomForm.FormResponse;
import com.leanplatform.MentorshipPlatform.entities.CustomForm.FormStructure;
import com.leanplatform.MentorshipPlatform.mappers.CustomForm.FormStructureConverter;
import com.leanplatform.MentorshipPlatform.repositories.CustomForm.FormResponseRepository;
import com.leanplatform.MentorshipPlatform.repositories.CustomForm.FormStructureRepository;
import com.leanplatform.MentorshipPlatform.repositories.MasterConfig.MasterConfigRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.UserRepository;
import com.leanplatform.MentorshipPlatform.services.CustomForm.FormStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class FormStructureServiceImpl implements FormStructureService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MasterConfigRepository masterConfigRepository;
    @Autowired
    FormStructureRepository formStructureRepository;
    @Autowired
    FormResponseRepository formResponseRepository;
    @Value("${s3.bucket.baseUrl}")
    String s3BaseUrl;
    @Value("${aws.bucketName}")
    private String bucketName;
    @Autowired
    S3Client s3Client;
    @Override
    public FormStructureResponse createForm(String title, FormStructureDto formStructureDto) throws IOException {

//            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//            String phoneNo=authentication.getName();
//            User user=userRepository.findByPhoneNo(phoneNo);
//            UUID userId=user.getId();

          //Checking if the URL  to navigate to is provided or not
         if(formStructureDto.getNavigateTo() == null)
             return FormStructureResponse.builder().responseCode(0).message("Please provide a route to redirect to on form submission").build();

        //Checking whether title of this form already exists or not
        Optional<FormStructure> formStructureAlreadyExists = formStructureRepository.findByTitle(title);
         if(formStructureAlreadyExists.isPresent())
             return FormStructureResponse.builder().responseCode(0).message("Form already exists, title must be unique to create a new form").form_Url(formStructureAlreadyExists.get().getFormUrl()).build();

         //Extracting the base Url from the global parameter
        String baseUrl = String.valueOf(masterConfigRepository.findByGlobalParameter("leadGenBaseUrl").getValue());
        if(baseUrl.equals("null"))
            return FormStructureResponse.builder().responseCode(0).message("No base url present in the global parameter").build();

        String formUrl = baseUrl + "/form/" + title.replace(" ", "-");

        //Creating poster url
         MultipartFile poster = formStructureDto.getPoster();
         String posterUrl = savePosterInBucketAndCreateUrl(poster);

        FormStructure formStructure = FormStructureConverter.dtoToEntityConverterCreate(title,formStructureDto,formUrl,posterUrl);
        formStructureRepository.save(formStructure);
        return FormStructureResponse.builder().form_Url(formUrl).responseCode(1).message("Form has been created successfully").build();

    }

    @Override
    public FormResponseData getForm(FormStructureRequestDto formStructureRequestDto) {


        Optional<FormStructure> formStructure = formStructureRepository.findByTitle(formStructureRequestDto.getTitle());
        if(formStructure.isEmpty())
           return FormResponseData.builder().responseCode(0).message("No form of such title exists,Please send a valid title").build();

        formStructure.get().setTotalOpens(formStructure.get().getTotalOpens() + 1);

        formStructureRepository.save(formStructure.get());

        //If the user is opening this form for the first time
        if(formStructureRequestDto.getVisitorId() == null){
            UUID visitorId = UUID.randomUUID();
            FormResponse savedFormResponse = createNewEntryInFormResponseTable(formStructureRequestDto.getTitle(),visitorId);
            FormStructureData formStructureData = FormStructureConverter.entityToDtoConverter(formStructure.get(), visitorId, savedFormResponse.getResponse_id());
            return FormResponseData.builder().formStructureData(formStructureData).responseCode(1).message("Form structure fetched successfully").build();
        }

        Optional<FormResponse> formResponse = formResponseRepository.findByTitleAndVisitorId(formStructureRequestDto.getTitle(), formStructureRequestDto.getVisitorId());

        //If the user has previously submitted a form but not this title
        if(formResponse.isEmpty())
        {
            FormResponse savedFormResponse = createNewEntryInFormResponseTable(formStructureRequestDto.getTitle(),formStructureRequestDto.getVisitorId());
            FormStructureData formStructureData = FormStructureConverter.entityToDtoConverter(formStructure.get(), null, savedFormResponse.getResponse_id());
            return FormResponseData.builder().formStructureData(formStructureData).responseCode(1).message("Form structure fetched successfully").build();

        }

        //If the user has previously opened the same form
            formResponse.get().setOpenedAt(LocalDateTime.now());
            formResponseRepository.save(formResponse.get());
            FormStructureData formStructureData = FormStructureConverter.entityToDtoConverter(formStructure.get(), null, formResponse.get().getResponse_id());
            return FormResponseData.builder().formStructureData(formStructureData).responseCode(2).message("Form structure fetched successfully,the user has already opened this form previously").build();

    }

    @Override
    public FormStructureResponse updateForm(String title, FormStructureDto formStructureDto) throws IOException {
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        String phoneNo=authentication.getName();
//        User user=userRepository.findByPhoneNo(phoneNo);
//        UUID userId=user.getId();

        //Checking whether form with such title exists in the table or not
        Optional<FormStructure> formStructure = formStructureRepository.findByTitle(title);
        if(formStructure.isEmpty())
            return FormStructureResponse.builder().responseCode(0).message("No form of such title exists in the table").build();

        //Creating poster url
        MultipartFile poster = formStructureDto.getPoster();
        String posterUrl = savePosterInBucketAndCreateUrl(poster);

        FormStructure updatedFormStructure = FormStructureConverter.dtoToEntityConverterUpdate(formStructureDto,formStructure.get(),posterUrl);
        formStructureRepository.save(updatedFormStructure);
        return FormStructureResponse.builder().form_Url(updatedFormStructure.getFormUrl()).responseCode(1).message("Form has been updated successfully").build();
    }

    @Override
    public GetFormStructureAdminDto getFormDetails(String title) {

//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        String phoneNo=authentication.getName();
//        User user=userRepository.findByPhoneNo(phoneNo);
//        UUID userId=user.getId();

        Optional<FormStructure> formStructure = formStructureRepository.findByTitle(title);

        if(formStructure.isEmpty())
            return GetFormStructureAdminDto.builder().responseCode(0).message("No form of such title exists in the table").build();

        return FormStructureConverter.entityToDtoConverter(formStructure.get());
    }

    //Function to get the extension of the uploaded poster
    public  String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }
        return null;
    }

    public String savePosterInBucketAndCreateUrl(MultipartFile poster) throws IOException {
        if(poster!=null && !poster.isEmpty())
        {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String fileName = "poster-"  + currentDateTime +"." + getFileExtension(poster);
            InputStream inputStream=poster.getInputStream();
            PutObjectRequest request= PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            s3Client.putObject(request, RequestBody.fromInputStream(inputStream,inputStream.available()));
            return s3BaseUrl + "/" + fileName;
        }
        return null;
    }

     //Function to create new entry in the response table
    public FormResponse createNewEntryInFormResponseTable(String title,UUID visitorId)
    {
        FormResponse formResponse = new FormResponse();
        formResponse.setVisitorId(visitorId);
        formResponse.setTitle(title);
        formResponse.setOpenedAt(LocalDateTime.now());
        return formResponseRepository.save(formResponse);
    }
}
