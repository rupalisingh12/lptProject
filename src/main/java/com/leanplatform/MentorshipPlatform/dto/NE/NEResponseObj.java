package com.leanplatform.MentorshipPlatform.dto.NE;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NEResponseObj {

    private String message;
    private int responseCode;
    private List<NEUserDto> users;
    private List<String> emails;
}
