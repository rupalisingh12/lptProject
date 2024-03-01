package com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddFeedBackDetailsResponseDTO {
    private String userName;
    private String emailId;
    private String details;
    private List<String>fileUrls;
    private LocalDateTime createdAt;
}
