package com.leanplatform.MentorshipPlatform.dto.AdminController;

import com.leanplatform.MentorshipPlatform.enums.RequestAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestToBeUpdated {
    private RequestAction action;
    private UUID mentorId;
}
