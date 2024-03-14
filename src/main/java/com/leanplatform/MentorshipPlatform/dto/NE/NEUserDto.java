package com.leanplatform.MentorshipPlatform.dto.NE;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NEUserDto {

    private String email;
    private List<String> placeholder;
}
