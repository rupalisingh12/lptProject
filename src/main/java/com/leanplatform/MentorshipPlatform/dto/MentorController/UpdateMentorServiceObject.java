package com.leanplatform.MentorshipPlatform.dto.MentorController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateMentorServiceObject {
    private UUID id;
    private Double price;
}
