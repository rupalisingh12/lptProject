package com.leanplatform.MentorshipPlatform;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.leanplatform.MentorshipPlatform.config.TwilioConfig;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import static com.leanplatform.MentorshipPlatform.CalendarQuickstart.*;


@SpringBootApplication
@EntityScan(basePackages = "com.leanplatform.MentorshipPlatform.entities")
@EnableJpaRepositories(basePackages = "com.leanplatform.MentorshipPlatform.repositories")
@EnableConfigurationProperties
@CrossOrigin("*")
public class MentorshipPlatformApplication {

	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void setup() {

		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}
	public static void main(String[] args) throws GeneralSecurityException, IOException {

		SpringApplication.run(MentorshipPlatformApplication.class, args);
		CalendarQuickstart.createCalendarEvent();

		// List the next 10 events from the primary calendar.
//		DateTime now = new DateTime(System.currentTimeMillis());
//		Events events = service.events().list("primary")
//				.setMaxResults(10)
//				.setTimeMin(now)
//				.setOrderBy("startTime")
//				.setSingleEvents(true)
//				.execute();
//		List<Event> items = events.getItems();
//		if (items.isEmpty()) {
//			System.out.println("No upcoming events found.");
//		} else {
//			System.out.println("Upcoming events");
//			for (Event event : items) {
//				DateTime start = event.getStart().getDateTime();
//				if (start == null) {
//					start = event.getStart().getDate();
//				}
//				System.out.printf("%s (%s)\n", event.getSummary(), start);
//			}
//		}
		//create event




	}






}
