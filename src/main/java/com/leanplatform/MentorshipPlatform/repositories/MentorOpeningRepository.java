package com.leanplatform.MentorshipPlatform.repositories;

import java.util.Optional;

public interface MentorOpeningRepository {
    Optional<Object> findById(Long id);
}
