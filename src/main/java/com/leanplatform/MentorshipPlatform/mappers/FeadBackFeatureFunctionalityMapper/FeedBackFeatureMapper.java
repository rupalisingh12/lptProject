package com.leanplatform.MentorshipPlatform.mappers.FeadBackFeatureFunctionalityMapper;
import com.leanplatform.MentorshipPlatform.entities.FeedBackFeatureWhole.*;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.AddContactResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.AddFeedBackDetailsResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.AddRatingResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.GetAvailabilityButtonsDto;

public class FeedBackFeatureMapper {
    public static GetAvailabilityButtonsDto converEntityToDTO(FeedBackFeature feedBackFeature){
        GetAvailabilityButtonsDto getAvailabilityButtonsDto=new GetAvailabilityButtonsDto();

        getAvailabilityButtonsDto.setFormType(feedBackFeature.getFormType());
        getAvailabilityButtonsDto.setCreatedAt(feedBackFeature.getCreatedAt());
        return getAvailabilityButtonsDto;

    }
   public  static AddFeedBackDetailsResponseDTO convertEntityToDTO2(FeedBackDetails feedBackDetails){
        AddFeedBackDetailsResponseDTO addFeedBackDetailsResponseDTO=new AddFeedBackDetailsResponseDTO();
        addFeedBackDetailsResponseDTO.setDetails(feedBackDetails.getDetails());
        addFeedBackDetailsResponseDTO.setFileUrls(feedBackDetails.getFileUrls());
        addFeedBackDetailsResponseDTO.setCreatedAt(feedBackDetails.getCreatedAt());
        addFeedBackDetailsResponseDTO.setEmailId(feedBackDetails.getEmailId());
        return addFeedBackDetailsResponseDTO;
    }
    public  static AddFeedBackDetailsResponseDTO convertEntityToDTO3(Issue feedBackDetails){
        AddFeedBackDetailsResponseDTO addFeedBackDetailsResponseDTO=new AddFeedBackDetailsResponseDTO();
        addFeedBackDetailsResponseDTO.setDetails(feedBackDetails.getDetails());
        addFeedBackDetailsResponseDTO.setFileUrls(feedBackDetails.getFileUrls());
        addFeedBackDetailsResponseDTO.setEmailId(feedBackDetails.getEmailId());
        addFeedBackDetailsResponseDTO.setCreatedAt(feedBackDetails.getCreatedAt());
        return addFeedBackDetailsResponseDTO;
    }

    public static AddContactResponseDTO converEntityToDTO3(Contact contact){
        AddContactResponseDTO addContactResponseDTO=new AddContactResponseDTO();
        addContactResponseDTO.setDetails(contact.getDetails());
        addContactResponseDTO.setEmail(contact.getEmailId());
        addContactResponseDTO.setName(contact.getName());
        addContactResponseDTO.setCreatedAt(contact.getCreatedAt());
        addContactResponseDTO.setPhoneNumber(contact.getPhoneNumber());
        return addContactResponseDTO;

    }
    public static AddRatingResponseDTO converEntityToDTO4(Rating rating){
        AddRatingResponseDTO addRatingResponseDTO=new AddRatingResponseDTO();
        addRatingResponseDTO.setEmailId(rating.getEmailId());
        addRatingResponseDTO.setCreatedAt(rating.getCreatedAt());
        addRatingResponseDTO.setNumberOfStarts(rating.getNumberOfStars());
        return addRatingResponseDTO;
    }
}
