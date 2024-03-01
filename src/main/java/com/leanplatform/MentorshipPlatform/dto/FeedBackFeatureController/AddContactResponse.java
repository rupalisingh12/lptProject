package com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddContactResponse {
    private String statusCode;
    private String responseMessage;
    private List<AddContactResponseDTO> addContactResponseDTOList;
}
