package com.leanplatform.MentorshipPlatform.dto.OverallStats;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@SqlResultSetMapping(
        name = "ServiceSessionCountMapping",
        classes = @ConstructorResult(
                targetClass = ServiceSessionCountDTO.class,
                columns = {
                        @ColumnResult(name = "serviceOffered", type = String.class),
                        @ColumnResult(name = "sessionCount", type = Long.class)
                }
        )
)
public class ServiceSessionCountDTO {
    @Column(name = "service_offered", columnDefinition = "VARCHAR")
    private String serviceOffered;

    @Column(name = "sessionCount", columnDefinition = "BIGINT")
    private Long sessionCount;

    public ServiceSessionCountDTO(String serviceOffered, BigInteger sessionCount) {
        this.serviceOffered = serviceOffered;
        this.sessionCount = sessionCount.longValue();
    }

}
