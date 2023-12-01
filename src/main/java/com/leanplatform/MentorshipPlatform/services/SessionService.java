package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.SessionController.*;
import org.springframework.http.ResponseEntity;

public interface SessionService {
    ResponseEntity<SessionBookedResponse> createNewSession(SessionBookingObject sessionBookingObject);

    ResponseEntity<SessionDeleteResponse> deleteSession(SessionDeleteRequest sessionDeleteRequest);

    ResponseEntity<PreSessionDetailsAdded> updateSessionDetails(UpdateSessionDetails updateSessionDetails);

    ResponseEntity<SessionSummaryResponse> getSessionSummary(SessionSummaryRequest sessionSummaryRequest);

    ResponseEntity<MentorSessionRatingResponse> rateMentorSession(MentorSessionRatingRequest mentorSessionRatingRequest);

    ResponseEntity<SessionDetailsResponse> getSessionDetails(SessionDetailsRequest sessionDetailsRequest);

    ResponseEntity<SessionsListResponse> getUpcomingSessions(SessionsListRequest sessionsListRequest);

    ResponseEntity<SessionsListResponse> getFinishedSessions(SessionsListRequest sessionsListRequest);
}
