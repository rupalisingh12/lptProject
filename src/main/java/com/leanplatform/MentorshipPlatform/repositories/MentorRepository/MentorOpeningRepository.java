package com.leanplatform.MentorshipPlatform.repositories.MentorRepository;

import java.util.Optional;

public interface MentorOpeningRepository {
    Optional<Object> findById(Long id);
}
