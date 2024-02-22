package com.leanplatform.MentorshipPlatform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Cards {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID cardsId;
    private String img;
    private String title;
    private String paragraph;
    private UUID learningSection;
    private UUID userId;
    private UUID landingPageId;


}
