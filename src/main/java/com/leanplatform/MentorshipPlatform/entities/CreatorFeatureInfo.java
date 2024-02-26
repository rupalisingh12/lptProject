package com.leanplatform.MentorshipPlatform.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CreatorFeatureInfo {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID creatorFeatureInfoId;
    //remove this , make a separate btable for landing page

  //  private String tryingToSaveInJson;
    private UUID userId;
    private String userName;
    private String leadGenForm;
    private String masterClass;
    private Boolean slot;

}
