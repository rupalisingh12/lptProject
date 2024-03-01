package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.AddSuggestionResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.GetAvailabilityButtonsDto;
import com.leanplatform.MentorshipPlatform.entities.FeedBackFeature;
import com.leanplatform.MentorshipPlatform.entities.Suggestion;

public class SuggestionMapper {

    public static AddSuggestionResponseDTO converEntityToDTO(Suggestion suggestion){
        AddSuggestionResponseDTO addSuggestionResponseDTO=new AddSuggestionResponseDTO();

       addSuggestionResponseDTO.setDetails(suggestion.getDetails());
       addSuggestionResponseDTO.setFileUrls(suggestion.getFileUrls());
       addSuggestionResponseDTO.setEmailId(suggestion.getEmailId());
       return addSuggestionResponseDTO;

    }
}
