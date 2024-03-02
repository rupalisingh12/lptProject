package com.leanplatform.MentorshipPlatform.repositories.MasterConfig;


import com.leanplatform.MentorshipPlatform.entities.MasterConfig.MasterConfigTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterConfigRepository extends JpaRepository<MasterConfigTable,Long> {

    MasterConfigTable findByGlobalParameter(String environmentName);
}
