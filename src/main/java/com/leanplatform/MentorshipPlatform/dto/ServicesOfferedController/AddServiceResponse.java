package com.leanplatform.MentorshipPlatform.dto.ServicesOfferedController;

import com.leanplatform.MentorshipPlatform.entities.MentorEntity.ServicesOffered;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddServiceResponse {
    private String statusCode;
    private String responseMessage ;
    private ServicesOffered servicesOffered;
}
