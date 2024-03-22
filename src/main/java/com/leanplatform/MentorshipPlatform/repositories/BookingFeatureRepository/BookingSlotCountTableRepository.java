package com.leanplatform.MentorshipPlatform.repositories.BookingFeatureRepository;

import com.leanplatform.MentorshipPlatform.entities.BookingFeature.BookingSlotCountTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Repository
public interface BookingSlotCountTableRepository extends JpaRepository<BookingSlotCountTable, UUID> {

    List<BookingSlotCountTable> findByDateAndEventTypeId(LocalDate date, UUID eventTypeId);
}
