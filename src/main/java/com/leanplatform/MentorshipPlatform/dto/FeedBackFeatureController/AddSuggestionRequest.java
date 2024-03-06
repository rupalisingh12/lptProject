package com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddSuggestionRequest {
    private String emailId;
    private String details;
    private List<MultipartFile> fileUrls;
}
