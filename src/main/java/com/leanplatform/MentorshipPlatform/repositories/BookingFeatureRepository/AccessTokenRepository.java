package com.leanplatform.MentorshipPlatform.repositories.BookingFeatureRepository;

import com.leanplatform.MentorshipPlatform.entities.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken,UUID> {
    //AccessTokenTable findRefreshTokenByUserId(UUID userId);
    @Query(value = "SELECT * FROM AccessToken WHERE userId = :userId", nativeQuery = true)
    AccessToken findAccessTokenByUserId(@Param("userId") UUID userId);


    @Query(value = "SELECT b FROM AccessToken b WHERE b.userId = :userId")
    AccessToken findRefreshTokenByUserId(@Param("userId") UUID userId);

}
