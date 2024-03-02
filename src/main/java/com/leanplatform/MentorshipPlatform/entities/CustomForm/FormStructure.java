    package com.leanplatform.MentorshipPlatform.entities.CustomForm;

    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.ColumnDefault;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;
    import org.springframework.web.multipart.MultipartFile;


    import java.time.LocalDateTime;
    import java.util.UUID;

    @Entity
    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public class FormStructure {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int form_id;
        @Column(nullable = false)
        private String title;
        @Column( columnDefinition = "VARCHAR(255) DEFAULT 'Your name'")
        private String name;
        @Column( columnDefinition = "VARCHAR(255) DEFAULT 'Mobile number'")
        private String phoneNumber;
        @Column( columnDefinition = "VARCHAR(255) DEFAULT 'Email'")
        private String email;
        private String others;
        @ColumnDefault("false")
        private boolean isExperience;
        private String formUrl;
        private String navigateTo;
        private String posterUrl;
        private  String description;
        @CreationTimestamp
        private LocalDateTime createdAt;
        private UUID createdBy;
        @UpdateTimestamp
        private LocalDateTime modifiedAt;
        private UUID modifiedBy;
        @ColumnDefault("0")
        private long totalOpens;
        @ColumnDefault("0")
        private long totalResponses;

        public FormStructure(){
            this.name = "Your name";
            this.email = "Email";
            this.phoneNumber="Mobile number";
        }

    }
