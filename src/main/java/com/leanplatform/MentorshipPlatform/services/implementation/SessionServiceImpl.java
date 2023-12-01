package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.SessionController.*;
import com.leanplatform.MentorshipPlatform.entities.*;
import com.leanplatform.MentorshipPlatform.repositories.*;
import com.leanplatform.MentorshipPlatform.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private MenteeRepository menteeRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private ServicesByMentorsRepository servicesByMentorsRepository;

    @Autowired
    private ServicesOfferedRepository servicesOfferedRepository;
    @Override
    public ResponseEntity<SessionBookedResponse> createNewSession(SessionBookingObject sessionBookingObject) {
        if (sessionBookingObject == null ||
                sessionBookingObject.getMenteeId() == null ||
                sessionBookingObject.getAvailabilityId() == null){
            return new ResponseEntity<>(new SessionBookedResponse
                    (
                            "0",
                            "Invalid Request Null Object Received."
                    ), HttpStatus.BAD_REQUEST);
        }
        Optional<Availability> optionalAvailability = availabilityRepository.findById(sessionBookingObject.getAvailabilityId());
        Optional<Mentee> optionalMentee = menteeRepository.findById(sessionBookingObject.getMenteeId());
        if (optionalAvailability.isPresent() && optionalMentee.isPresent()){
            Session newSession = new Session();
            newSession.setAvailabilityId(sessionBookingObject.getAvailabilityId());
            newSession.setMenteeId(sessionBookingObject.getMenteeId());
            sessionRepository.save(newSession);

            //Flag the availability object as true, because it is booked now.
            Availability existingAvailability = optionalAvailability.get();
            existingAvailability.setIsBooked(true);
            availabilityRepository.save(existingAvailability);
        }else {
            return new ResponseEntity<>(new SessionBookedResponse
                    (
                            "0",
                            "Invalid Request - Provided Ids are not present in request object."
                    ), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new SessionBookedResponse
                (
                        "1",
                        "Session Booked Successfully."
                ), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SessionDeleteResponse> deleteSession(SessionDeleteRequest sessionDeleteRequest) {
        if(sessionDeleteRequest == null ||
                sessionDeleteRequest.getSessionId() == null){
            return new ResponseEntity<>(new SessionDeleteResponse
                    (
                            "0",
                            "Invalid Request - Null request object received. "
                    ),HttpStatus.BAD_REQUEST);
        }

        Optional<Session> optionalSession = sessionRepository.findById(sessionDeleteRequest.getSessionId());
        Optional<Availability> optionalAvailability = availabilityRepository.findById(sessionRepository.getAvailabilityId(sessionDeleteRequest.getSessionId()));
        if (optionalSession.isPresent() && optionalAvailability.isPresent()){
            Session existingSession = optionalSession.get();
            sessionRepository.delete(existingSession);
            Availability existingAvailability = optionalAvailability.get();
            existingAvailability.setIsBooked(false);
            availabilityRepository.save(existingAvailability);
        }else {
            return new ResponseEntity<>(new SessionDeleteResponse
                    (
                            "0",
                            "Invalid Request - Resource with provided credentials not found. "
                    ),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new SessionDeleteResponse
                (
                        "1",
                        "Session Deleted Successfully."
                ),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PreSessionDetailsAdded> updateSessionDetails(UpdateSessionDetails updateSessionDetails) {
        if (updateSessionDetails == null ||
                updateSessionDetails.getSessionId() == null){
            return new ResponseEntity<>(new PreSessionDetailsAdded
                    (
                            "0",
                            "Invalid Request - Null Object received."
                    ),HttpStatus.BAD_REQUEST);
        }

        Optional<Session> optionalSession = sessionRepository.findById(updateSessionDetails.getSessionId());
        if (optionalSession.isPresent()){
            Session existingSession = optionalSession.get();
            if (updateSessionDetails.getSessionExpectations() != null) {
                existingSession.setSessionExpectations(updateSessionDetails.getSessionExpectations());
            }
            if (updateSessionDetails.getReasonForSession() != null) {
                existingSession.setReasonForSession(updateSessionDetails.getReasonForSession());
            }
            if (updateSessionDetails.getSessionSummary() != null) {
                existingSession.setSessionSummary(updateSessionDetails.getSessionSummary());
            }
            sessionRepository.save(existingSession);
        }else {
            return new ResponseEntity<>(new PreSessionDetailsAdded
                    (
                            "0",
                            "Resource with the given credentials not found."
                    ),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PreSessionDetailsAdded
                (
                        "1",
                        "Session Details Successfully updated."
                ),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SessionSummaryResponse> getSessionSummary(SessionSummaryRequest sessionSummaryRequest) {
        if (sessionSummaryRequest == null ||
                sessionSummaryRequest.getSessionId() == null){
            return new ResponseEntity<>(new SessionSummaryResponse
                    (
                            "0",
                            "Invalid Request - Null object received.",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        Optional<Session> optionalSession = sessionRepository.findById(sessionSummaryRequest.getSessionId());
        String sessionSummary = null;
        if (optionalSession.isPresent()){
            Session existingSession = optionalSession.get();
            if (existingSession.getSessionSummary() != null){
                sessionSummary = existingSession.getSessionSummary();
            }else {
                sessionSummary = "Mentor has not yet uploaded the session summary.";
            }
        }else {
            return new ResponseEntity<>(new SessionSummaryResponse
                    (
                            "0",
                            "Resource with the given credentials not found.",
                            sessionSummary
                    ),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new SessionSummaryResponse
                (
                        "1",
                        "Session Summary Successfully returned.",
                        sessionSummary
                ),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MentorSessionRatingResponse> rateMentorSession(MentorSessionRatingRequest mentorSessionRatingRequest) {
        if(mentorSessionRatingRequest == null ||
                mentorSessionRatingRequest.getSessionId() == null ||
                mentorSessionRatingRequest.getMentorId() == null ||
                mentorSessionRatingRequest.getMenteeId() == null ||
                mentorSessionRatingRequest.getServiceId() == null ||
                mentorSessionRatingRequest.getSessionRating() == null ){
            return new ResponseEntity<>(new MentorSessionRatingResponse
                    (
                            "0",
                            "Invalid request : Null Object received."
                    ),HttpStatus.BAD_REQUEST);
        }

        Optional<Mentor> optionalMentor = mentorRepository.findById(mentorSessionRatingRequest.getMentorId());
        Optional<Mentee> optionalMentee = menteeRepository.findById(mentorSessionRatingRequest.getMenteeId());
        Optional<ServicesByMentors> optionalServicesByMentors = servicesByMentorsRepository.findById(mentorSessionRatingRequest.getServiceId());
        Optional<Session> optionalSession = sessionRepository.findById(mentorSessionRatingRequest.getSessionId());
        if (optionalSession.isPresent() && optionalMentor.isPresent() && optionalMentee.isPresent() && optionalServicesByMentors.isPresent()){
            Session existingSession = optionalSession.get();
            existingSession.setSessionRating(mentorSessionRatingRequest.getSessionRating());
            if (mentorSessionRatingRequest.getFeedback() != null) {
                existingSession.setFeedback(mentorSessionRatingRequest.getFeedback());
            }
            sessionRepository.save(existingSession);

            //Update the overall rating of the Mentor who took this particular session.
            Mentor existingMentor = optionalMentor.get();
            int noOfSessionsByMentor = sessionRepository.getSessionCountOfMentor(mentorSessionRatingRequest.getMentorId());
            Double overAllRating = (existingMentor.getOverAllRating() + mentorSessionRatingRequest.getSessionRating())/noOfSessionsByMentor;
            existingMentor.setOverAllRating(overAllRating);
            mentorRepository.save(existingMentor);
        }else {
            return new ResponseEntity<>(new MentorSessionRatingResponse
                    (
                            "0",
                            "Invalid request : Resource with the provided credentials not found."
                    ),HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(new MentorSessionRatingResponse
                (
                        "1",
                        "Successfully Rated Mentor "+mentorSessionRatingRequest.getMentorId()+
                                " with "+mentorSessionRatingRequest.getSessionRating()+" Stars."
                ), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SessionDetailsResponse> getSessionDetails(SessionDetailsRequest sessionDetailsRequest) {
        if (sessionDetailsRequest == null || sessionDetailsRequest.getSessionId() == null){
            return new ResponseEntity<>(new SessionDetailsResponse
                    (
                            "0",
                            "Invalid Request - Null Object Received.",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        Optional<Session> optionalSession = sessionRepository.findById(sessionDetailsRequest.getSessionId());
        SessionDetailsDto sessionDetailsDto = new SessionDetailsDto();
        if (optionalSession.isPresent()){
            Session existingSession = optionalSession.get();
            //Fetch the start and endTime of the slots.
            LocalTime startTime = availabilityRepository.getStartTimeOfSlot(existingSession.getAvailabilityId());
            LocalTime endTime = availabilityRepository.getEndTimeOfSlot(existingSession.getAvailabilityId());


            sessionDetailsDto.setMentorName(mentorRepository.getFirstName(existingSession.getMentorId())
                    +" "+mentorRepository.getLastName(existingSession.getMentorId()));
            sessionDetailsDto.setMenteeName(menteeRepository.getFirstName(existingSession.getMenteeId())
                    +" "+menteeRepository.getLastName(existingSession.getMenteeId()));
            sessionDetailsDto.setServiceOffered(servicesOfferedRepository.getServiceOffered(existingSession.getServiceId()));
            sessionDetailsDto.setStartTime(startTime);
            sessionDetailsDto.setEndTime(endTime);
            sessionDetailsDto.setDate(availabilityRepository.getSlotDate(existingSession.getAvailabilityId()));
            sessionDetailsDto.setDayOfTheWeek(availabilityRepository.getDayOfTheWeek(existingSession.getAvailabilityId()));

            //Compare with the current local time, whether it is ahead or behind with the slot end time.
            LocalTime currentTime = LocalTime.now();
            int comparisonResult = currentTime.compareTo(endTime);
            if (comparisonResult > 0) {
                // currentTime is ahead of endTime
                sessionDetailsDto.setSessionExpectations(existingSession.getSessionExpectations());
                sessionDetailsDto.setSessionSummary(existingSession.getSessionSummary());
                sessionDetailsDto.setReasonForSession(existingSession.getReasonForSession());
                sessionDetailsDto.setSessionRating(existingSession.getSessionRating());
                sessionDetailsDto.setFeedback(existingSession.getFeedback());
            }
        }else {
            return new ResponseEntity<>(new SessionDetailsResponse
                    (
                            "0",
                            "Invalid Request - Resource not found with the provided credentials.",
                            null
                    ),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new SessionDetailsResponse
                (
                        "1",
                        "Session Details sent successfully.",
                        sessionDetailsDto
                ),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SessionsListResponse> getUpcomingSessions(SessionsListRequest sessionsListRequest) {
        if (sessionsListRequest == null ||
                sessionsListRequest.getMenteeId() == null){
            return new ResponseEntity<>(new SessionsListResponse
                    (
                            "0",
                            "Invalid Request - Null object received on the given request object.",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        Optional<Mentee> optionalMentee = menteeRepository.findById(sessionsListRequest.getMenteeId());
        List<SessionsDto> sessionsDtos = new ArrayList<>();
        LocalTime currentTime = LocalTime.now();
        if (optionalMentee.isPresent()){
            List<Session> allSessions = sessionRepository.findByMentee(sessionsListRequest.getMenteeId());
            for (Session session : allSessions){
                int comparisonResult = currentTime.compareTo(availabilityRepository.getStartTimeOfSlot(session.getAvailabilityId()));
                if (comparisonResult < 0) {
                    // currentTime is behind of startTime of each session fetched from the repository
                    SessionsDto sessionsDto = new SessionsDto();
                    sessionsDto.setSessionId(session.getSessionId());
                    sessionsDto.setDate( availabilityRepository.getSlotDate(session.getAvailabilityId()));
                    sessionsDto.setStartTime(availabilityRepository.getStartTimeOfSlot(session.getAvailabilityId()));
                    sessionsDto.setEndTime(availabilityRepository.getEndTimeOfSlot(session.getAvailabilityId()));
                    sessionsDto.setMentorName(mentorRepository.getFirstName(session.getMentorId())
                            +" "+mentorRepository.getLastName(session.getMentorId()));
                    sessionsDtos.add(sessionsDto);
                }
            }
        }else {
            return new ResponseEntity<>(new SessionsListResponse
                    (
                            "0",
                            "Resource with the provided Id is not available.",
                            null
                    ),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new SessionsListResponse
                (
                        "1",
                        "Upcoming Session list of mentee successfully sent.",
                        sessionsDtos
                ),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SessionsListResponse> getFinishedSessions(SessionsListRequest sessionsListRequest) {
        if (sessionsListRequest == null ||
                sessionsListRequest.getMenteeId() == null){
            return new ResponseEntity<>(new SessionsListResponse
                    (
                            "0",
                            "Invalid Request - Null object received on the given request object.",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        Optional<Mentee> optionalMentee = menteeRepository.findById(sessionsListRequest.getMenteeId());
        List<SessionsDto> sessionsDtos = null;
        LocalTime currentTime = LocalTime.now();
        if (optionalMentee.isPresent()){
            List<Session> allSessions = sessionRepository.findByMentee(sessionsListRequest.getMenteeId());
            for (Session session : allSessions){
                int comparisonResult = currentTime.compareTo(availabilityRepository.getStartTimeOfSlot(session.getAvailabilityId()));
                if (comparisonResult >= 0) {
                    // currentTime is ahead of startTime of each session fetched from the repository
                    SessionsDto sessionsDto = new SessionsDto();
                    sessionsDto.setSessionId(session.getSessionId());
                    sessionsDto.setDate( availabilityRepository.getSlotDate(session.getAvailabilityId()));
                    sessionsDto.setStartTime(availabilityRepository.getStartTimeOfSlot(session.getAvailabilityId()));
                    sessionsDto.setEndTime(availabilityRepository.getEndTimeOfSlot(session.getAvailabilityId()));
                    sessionsDto.setMentorName(mentorRepository.getFirstName(session.getMentorId())
                            +" "+mentorRepository.getLastName(session.getMentorId()));
                    sessionsDtos.add(sessionsDto);
                }
            }
        }else {
            return new ResponseEntity<>(new SessionsListResponse
                    (
                            "0",
                            "Resource with the provided Id is not available.",
                            null
                    ),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new SessionsListResponse
                (
                        "1",
                        "Finished Session list of mentee successfully sent.",
                        sessionsDtos
                ),HttpStatus.OK);
    }
}
