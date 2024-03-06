package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FeedBackFeatureService {
   ResponseEntity<GetAvailableButtonsResponse> AddAvailableButtons(String userName, AddAvailableButtonsRequest addAvailableButtonsRequest);

   ResponseEntity<GetAvailableButtonsResponse> getAvailableButtons(String userName);
//   ResponseEntity<GetAvailableButtonsResponse>disableAvailableButtons(String userName,AddAvailableButtonsRequest addAvailableButtonsRequest);
   ResponseEntity<AddSuggestionResponse>  AddSuggestionForUser(String userName, AddSuggestionRequest addSuggestionRequest);
   ResponseEntity<GiveSuggestionResponse> getAllSuggestion(String userName);
   ResponseEntity<AddFeedBackDetailsResponse> addFeedBackDetailsOfUser(String userName,AddFeedBackFeatureDetailsRequest addFeedBackFeatureDetailsRequest);
   ResponseEntity<AddFeedBackDetailsResponse> getFeedBackDetailsOfUser(String userName);
   ResponseEntity<AddFeedBackDetailsResponse> addIssue(String  userName,AddFeedBackFeatureDetailsRequest addFeedBackFeatureDetailsRequest);
   ResponseEntity<AddFeedBackDetailsResponse>getIssue(String userName);
   ResponseEntity<AddContactResponse>addContactDetailsOfUser(String userName,AddContactRequest addContactRequest);
   ResponseEntity<AddContactResponse>getContactDetailsOfUser(String userName);
   ResponseEntity<AddContactResponse>addRating(String userName,  AddRatingRequest addRatingRequest);
   ResponseEntity<AddRatingResponse> getRatingDetails(String userName);
}


