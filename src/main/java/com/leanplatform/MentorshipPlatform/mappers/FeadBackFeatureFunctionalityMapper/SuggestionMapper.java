package com.leanplatform.MentorshipPlatform.mappers.FeadBackFeatureFunctionalityMapper;
import com.leanplatform.MentorshipPlatform.entities.FeedBackFeatureWhole.Suggestion;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.AddSuggestionResponseDTO;

public class SuggestionMapper {

    public static AddSuggestionResponseDTO converEntityToDTO(Suggestion suggestion){
        AddSuggestionResponseDTO addSuggestionResponseDTO=new AddSuggestionResponseDTO();

       addSuggestionResponseDTO.setDetails(suggestion.getDetails());
       addSuggestionResponseDTO.setFileUrls(suggestion.getFileUrls());
       addSuggestionResponseDTO.setEmailId(suggestion.getEmailId());
       return addSuggestionResponseDTO;

    }
}
