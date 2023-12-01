package com.leanplatform.MentorshipPlatform.dto.MentorAccountController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteMentorRequestObject {
     private UUID mentorId;
}
