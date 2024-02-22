package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LearningDto {
    private String Heading;
    private ArrayList<CardsDto> cards;

}
