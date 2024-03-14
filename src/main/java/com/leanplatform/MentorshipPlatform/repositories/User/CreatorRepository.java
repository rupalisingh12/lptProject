package com.leanplatform.MentorshipPlatform.repositories.User;



import com.leanplatform.MentorshipPlatform.entities.User.Creator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CreatorRepository extends JpaRepository<Creator, UUID> {


    @Query("SELECT u FROM Creator u WHERE u.phoneNo=:phoneNo AND u.status='Active' ")
    Creator findByPhoneNo(@Param("phoneNo") String phoneNo);

    @Query("SELECT u FROM Creator u WHERE u.email=:email AND u.status='Active' ")
    Creator findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Creator u WHERE u.isEmailVerified=1")
    List<Creator> allUsersToSendMail();


    @Query("SELECT u.email FROM Creator u")
    List<String> getAllEmails();

//    @Query("SELECT u FROM User u WHERE u.lastLoggedIn<=cutOff")
//    List<User> getLastLoggedInUsers(@Param("lastDate") LocalDateTime cutOff);

    List<Creator> findByLastLoggedInBefore(LocalDateTime cutoffDateTime);

    @Query("SELECT u.fullName FROM Creator u WHERE u.id = :userId")
    String getFullName(@Param("userId") UUID userId);

    @Query("SELECT COUNT(ur) > 0 FROM Creator u JOIN u.roles ur WHERE u.id = :userId AND ur.name = :roleName")
    boolean userHasRole(@Param("userId") UUID userId,@Param("roleName") String roleName);

    @Query("SELECT u FROM Creator u WHERE u.phoneNo=:phoneNo AND u.status='Deactivated' ")
    Creator getDeactivatedAccount(@Param("phoneNo") String username);

    @Query("SELECT u FROM Creator u WHERE u.publicUrl=:publicUrl ")
    Creator getByPublicUrl(@Param("publicUrl") String publicUrl);

    @Query("SELECT u FROM Creator u WHERE u.email=:email AND u.status='Pending' ")
    Creator getPendingCreator(@Param("email") String email);

    @Query("SELECT u FROM Creator u WHERE u.email=:email AND u.status='Active' ")
    Creator getByEmailId(@Param("email") String emailId);

    @Query("SELECT u FROM Creator u WHERE u.email=:email AND u.status='Verified' ")
    Creator getVerifiedCreator(@Param("email") String userEmail);
}
