package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.controllers.DisplayMentorAvailability;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityController.*;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestDeletedResponse;
import com.leanplatform.MentorshipPlatform.entities.Availability;
import com.leanplatform.MentorshipPlatform.entities.Mentor;
import com.leanplatform.MentorshipPlatform.enums.DaysOfTheWeek;
import com.leanplatform.MentorshipPlatform.mappers.TimeSlotMapper;
import com.leanplatform.MentorshipPlatform.repositories.AvailabilityRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository;
import com.leanplatform.MentorshipPlatform.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    @Autowired
    private  AvailabilityRepository availabilityRepository;

    @Autowired
    private MentorRepository mentorRepository;
    @Override
    public ResponseEntity<AvailabilityByMentorResponse> createAvailability(AvailabilityByMentor availabilityByMentor) {

        if(availabilityByMentor == null){
            return new ResponseEntity<>(new AvailabilityByMentorResponse
                    (
                            "0",
                            "Invalid Request on the controller as null object is fetched",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        Optional<Mentor> optionalMentor = mentorRepository.findById(availabilityByMentor.getMentorId());
        if (optionalMentor.isPresent()){
            for (int i = 0; i < availabilityByMentor.getNoOfWeeks(); i++) {
                LocalDate currentDate = LocalDate.now().plusWeeks(i);

                if (availabilityByMentor.getMonday() != null) {
                    calculateAvailability(DaysOfTheWeek.Monday, availabilityByMentor.getMonday(), currentDate , availabilityByMentor);
                }
                if (availabilityByMentor.getTuesday() != null) {
                    calculateAvailability(DaysOfTheWeek.Tuesday, availabilityByMentor.getTuesday(), currentDate, availabilityByMentor);
                }
                if (availabilityByMentor.getWednesday() != null) {
                    calculateAvailability(DaysOfTheWeek.Wednesday, availabilityByMentor.getWednesday(), currentDate, availabilityByMentor);
                }
                if (availabilityByMentor.getThursday() != null) {
                    calculateAvailability(DaysOfTheWeek.Thursday, availabilityByMentor.getThursday(), currentDate, availabilityByMentor);
                }
                if (availabilityByMentor.getFriday() != null) {
                    calculateAvailability(DaysOfTheWeek.Friday, availabilityByMentor.getFriday(), currentDate, availabilityByMentor);
                }
                if (availabilityByMentor.getSaturday() != null) {
                    calculateAvailability(DaysOfTheWeek.Saturday, availabilityByMentor.getSaturday(), currentDate, availabilityByMentor);
                }
                if (availabilityByMentor.getSunday() != null) {
                    calculateAvailability(DaysOfTheWeek.Sunday, availabilityByMentor.getSunday(), currentDate, availabilityByMentor);
                }
            }
        }else {
            return new ResponseEntity<>(new AvailabilityByMentorResponse
                    (
                            "0",
                            "Mentor with provided mentor Id not found",
                            null
                    ), HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(new AvailabilityByMentorResponse
                (
                        "1",
                        "Mentor availability successfully added.",
                        null
                ), HttpStatus.CREATED);
    }

    private void calculateAvailability(DaysOfTheWeek day, List<ArrayList<LocalTime>> daySlots, LocalDate currentDate,AvailabilityByMentor availabilityByMentor) {
        for (ArrayList<LocalTime> slots : daySlots) {
            Availability availability = new Availability();
            availability.setDayOfTheWeek(day);
            availability.setMentorId(availabilityByMentor.getMentorId());
            availability.setStartTime(slots.get(0));
            availability.setEndTime(slots.get(1));

            int daysUntilDay = (day.getValue() - currentDate.getDayOfWeek().getValue() + 7) % 7;
            if (currentDate.getDayOfWeek().getValue() > day.getValue()) {
                currentDate = currentDate.plusDays(daysUntilDay);
            } else if (currentDate.getDayOfWeek().getValue() < day.getValue()) {
                currentDate = currentDate.minusDays(daysUntilDay);
            }
            availability.setDate(currentDate);
            availability.setIsBooked(false);
            availabilityRepository.save(availability);
        }
    }
    @Override
    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    @Override
    public ResponseEntity<AvailabilityListOfMentor> getDesiredMentorAvailability(DisplayMentorAvailability displayMentorAvailability) {
        if (displayMentorAvailability == null ||
                displayMentorAvailability.getMentorId() == null){
            return new ResponseEntity<>(new AvailabilityListOfMentor
                    (
                            "0",
                            "Invalid Request Object",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }

        List<Availability> availabilityList = new ArrayList<>();
        Optional<Mentor> optionalMentor = mentorRepository.findById(displayMentorAvailability.getMentorId());
        if (optionalMentor.isPresent()){
            availabilityList  = availabilityRepository.getDesiredMentorAvailability(displayMentorAvailability.getMentorId());
        }else {
            return new ResponseEntity<>(new AvailabilityListOfMentor
                    (
                            "0",
                            "Mentor with the provided Id doesn't exist.",
                            availabilityList
                    ),HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(new AvailabilityListOfMentor
                (
                        "1",
                        "Availability List successfully sent.",
                        availabilityList
                ),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MentorAvailabilityDeletedResponse> deleteMentorAvailability(DeleteMentorAvailability deleteMentorAvailability) {
        if (deleteMentorAvailability == null ||
                deleteMentorAvailability.getAvailabilityId() == null){

            return new ResponseEntity<>(new MentorAvailabilityDeletedResponse
                    (
                            "0",
                            "Invalid Object - Null Object received"
                    ),HttpStatus.BAD_REQUEST);
        }
        Optional<Availability> optionalAvailability = availabilityRepository.findById(deleteMentorAvailability.getAvailabilityId());
        if (optionalAvailability.isPresent()){
            Availability existingAvailability = optionalAvailability.get();
            availabilityRepository.delete(existingAvailability);
        }else {
            return new ResponseEntity<>(new MentorAvailabilityDeletedResponse
                    (
                            "0",
                            "Request Availability doesn't exist."
                    ),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MentorAvailabilityDeletedResponse
                (
                        "1",
                        "Mentor Availability Successfully Deleted."
                ),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AvailabilityByMentorResponse> updateMentorAvailability(UpdateMentorAvailability updateMentorAvailability) {
        if(updateMentorAvailability == null ||
                updateMentorAvailability.getAvailabilityId() == null){
            return new ResponseEntity<>(new AvailabilityByMentorResponse
                    (
                            "0",
                            "Invalid Request - Null request object received in service. ",
                            null
                    ),HttpStatus.BAD_REQUEST);
        }
        Optional<Availability> optionalAvailability = availabilityRepository.findById(updateMentorAvailability.getAvailabilityId());
        if (optionalAvailability.isPresent()){
            Availability existingAvailability = optionalAvailability.get();
            existingAvailability.setStartTime(updateMentorAvailability.getStartTime());
            existingAvailability.setEndTime(updateMentorAvailability.getEndTime());
            availabilityRepository.save(existingAvailability);
        }else {
            return new ResponseEntity<>(new AvailabilityByMentorResponse
                    (
                            "0",
                            "Mentor availability with the given ID does not exist.",
                            null
                    ),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new AvailabilityByMentorResponse
                (
                        "1",
                        "Mentor availability updated successfully.",
                        null
                ),HttpStatus.BAD_REQUEST);
    }
}
