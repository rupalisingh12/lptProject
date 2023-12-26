package com.leanplatform.MentorshipPlatform;

import com.leanplatform.MentorshipPlatform.config.TwilioConfig;
import com.leanplatform.MentorshipPlatform.repositories.SlotRepository;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.leanplatform.MentorshipPlatform.entities.Slot;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalTime;
import java.util.stream.IntStream;


@SpringBootApplication
@EntityScan(basePackages = "com.leanplatform.MentorshipPlatform.entities")
@EnableJpaRepositories(basePackages = "com.leanplatform.MentorshipPlatform.repositories")
@EnableConfigurationProperties
public class MentorshipPlatformApplication {

	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}
	public static void main(String[] args) {
		SpringApplication.run(MentorshipPlatformApplication.class, args);
	}






}
