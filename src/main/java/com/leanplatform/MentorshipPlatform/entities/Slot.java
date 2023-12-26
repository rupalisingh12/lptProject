package com.leanplatform.MentorshipPlatform.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long slotId;
    private LocalTime startTime;
    private LocalTime endTime;

}
