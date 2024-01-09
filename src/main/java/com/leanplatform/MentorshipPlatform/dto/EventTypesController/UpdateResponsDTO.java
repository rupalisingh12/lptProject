package com.leanplatform.MentorshipPlatform.dto.EventTypesController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateResponsDTO {
    private UUID id;
    private String title;
    private Integer length;
    private Boolean hidden ;
    private UUID userId;
    private UUID scheduleId;
    private Double price;
    private String description;
}
