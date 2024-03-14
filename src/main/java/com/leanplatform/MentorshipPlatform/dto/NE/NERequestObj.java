package com.leanplatform.MentorshipPlatform.dto.NE;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NERequestObj {

    private String to;
    private String subject;
    private String data;
    private String template;
    private List<String> placeholders;
}
