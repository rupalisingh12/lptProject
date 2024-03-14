package com.leanplatform.MentorshipPlatform.entities.User;


import com.leanplatform.MentorshipPlatform.enums.UserAccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    private String fullName;

    @Column(nullable = false)
    private String email;

    private String phoneNo;

    private String pin;

    @Column(columnDefinition = "int default 0")
    private int isEmailVerified;


    @Column(columnDefinition="int default 0")
    private int isCampusUser;


    private String avatarId;

    @Enumerated(EnumType.STRING)
    private UserAccountStatus status;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")}
    )
    private List<Role> roles=new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime lastLoggedIn;


    private LocalDateTime lastLoginAttemptedAt;

    @Column(columnDefinition = "int default 0")
    private int failedAttempt;

    private String publicUrl;

    private String url;

    public Creator(){
        this.id=UUID.randomUUID();
    }
}
