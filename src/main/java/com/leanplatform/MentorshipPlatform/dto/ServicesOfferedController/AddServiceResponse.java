package com.leanplatform.MentorshipPlatform.dto.ServicesOfferedController;

import com.leanplatform.MentorshipPlatform.entities.ServicesOffered;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddServiceResponse {
    private String statusCode;
    private String responseMessage ;
    private ServicesOffered servicesOffered;
}
