package com.leanplatform.MentorshipPlatform;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import com.leanplatform.MentorshipPlatform.dto.BookingController.AccessTokenResponse;
import com.leanplatform.MentorshipPlatform.entities.AccessToken;
import com.leanplatform.MentorshipPlatform.entities.BookingFeature.Booking;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Attendee;
import com.leanplatform.MentorshipPlatform.repositories.BookingFeatureRepository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.List;
import java.time.ZoneId;
import java.util.Date;

@Service

public class CalendarQuickstart {
    @Autowired
     AccessTokenRepository accessTokenRepository;



    static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /**
     * Global instance of the JSON factory.
     */
    static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    String clientId = "418895730508-igvn95potkq4b0626s3hsise209i5rbr.apps.googleusercontent.com";
    String clientSecret = "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";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
//    private static final List<String> SCOPES =
//            Collections.singletonList(CalendarScopes.CALENDAR);
    private static final List<String> SCOPES = Arrays.asList(
            CalendarScopes.CALENDAR,
            CalendarScopes.CALENDAR_EVENTS,
            CalendarScopes.CALENDAR_EVENTS_READONLY,
            CalendarScopes.CALENDAR_READONLY
    );
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
   // private static final String REDIRECT_URI = "http://localhost:8080/oauth2callback";

   // static Logger logger = Logger.getLogger(CalendarQuickstart.class.getName());

    /**
     * Creates an authorized Credential object.
     * <p>
     * // * @param httpTransport The network HTTP Transport.
     *
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */

    static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.

        return credential;


    }

//    static Credential getCredentials(String accessToken) throws IOException {
//        // Create GoogleCredential object using the access token.
//        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
//
//        return credential;
//    }
       private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, String accessToken) {
        return new GoogleCredential().setAccessToken(accessToken);
    }



    public static void returnGetCredentials(){
        final NetHttpTransport HTTP_TRANSPORT;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            getCredentials(HTTP_TRANSPORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





    public void createCalendarEvent(UUID userId , List<Attendee> attendee , Booking booking) throws GeneralSecurityException, IOException {

        String calendarId = "primary";
        AccessToken refreshToken= accessTokenRepository.findRefreshTokenByUserId(userId);
     // String ans ="1//0gSltQOTpKpR0CgYIARAAGBASNwF-L9IrVQUxjzw4aAwvAnRBjpJX0x4c_cVDmkF-PJ6sqxeNcmZCgfXrdWB_YgcSud4NR5-J7zw";
      // String refreshToken=null;
        AccessTokenResponse accessTokenResponse= createAcessTokenFromRefreshToken(refreshToken.getRefreshToken());

        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT,accessTokenResponse.getAccessToken()))
                .setApplicationName(APPLICATION_NAME)
                .build();

        Event event = new Event()
                .setSummary("Testing Event Creation 1")
                .setLocation("India")
                .setDescription("Testing Event Creation");
        ////////////////////////////////////////////////////////////////////////////// startTime
        LocalDateTime localDateTime = booking.getStartTime(); // Get LocalDateTime from request body

// Convert LocalDateTime to Date
        Date startDate = Date.from(localDateTime.atZone(ZoneId.of("Asia/Kolkata")).toInstant());

// Create a DateTime object for the Google Calendar API
        DateTime startDateTime = new DateTime(startDate);

// Set the start time for the event
        EventDateTime start = new EventDateTime().setDateTime(startDateTime);
        event.setStart(start);


        //////////////////////////////////////////////////////////////////////////////

//        DateTime startDateTime = new DateTime("2024-11-13T09:00:00+05:30");
//        EventDateTime start = new EventDateTime()
//                .setDateTime(startDateTime)
//                .setTimeZone("Asia/Kolkata");
//        event.setStart(start);
        ///////////////////////////////////////////////////////////// endTime

        LocalDateTime endLocalDateTime = booking.getEndTime(); // Get LocalDateTime from request body

// Convert LocalDateTime to Date
        Date endDate = Date.from(endLocalDateTime.atZone(ZoneId.of("Asia/Kolkata")).toInstant());

// Create a DateTime object for the Google Calendar API
        DateTime endDateTime = new DateTime(endDate);

// Set the end time for the event
        EventDateTime end = new EventDateTime().setDateTime(endDateTime);
        event.setEnd(end);



        ////////////////////////////////////////////////////////////////

//        DateTime endDateTime = new DateTime("2024-11-13T10:00:00+05:30");
//        EventDateTime end = new EventDateTime()
//                .setDateTime(endDateTime)
//                .setTimeZone("Asia/Kolkata");
//        event.setEnd(end);
        //adding attendees
        //List<Attendee> attendees = getAttendeesFromSomeSource(); // Get attendees from some source

// Create a list to hold EventAttendee objects
        List<EventAttendee> eventAttendees = new ArrayList<>();

// Convert Attendee objects to EventAttendee objects and add them to the list
        for (int i = 0; i < attendee.size(); i++) {
            Attendee attendee1 = attendee.get(i);
            EventAttendee eventAttendee = new EventAttendee()
                    .setEmail(attendee1.getEmail())
                    .setDisplayName(attendee1.getName());
            eventAttendees.add(eventAttendee);
        }

// Set the attendees for the event
        event.setAttendees(eventAttendees);


        ConferenceSolutionKey conferenceSKey = new ConferenceSolutionKey();
        conferenceSKey.setType("hangoutsMeet"); // Non-G suite user
        CreateConferenceRequest createConferenceReq = new CreateConferenceRequest();
        createConferenceReq.setRequestId("primary"); // ID generated by you
        createConferenceReq.setConferenceSolutionKey(conferenceSKey);
        ConferenceData conferenceData = new ConferenceData();
        conferenceData.setCreateRequest(createConferenceReq);
        event.setConferenceData(conferenceData);

        // String calendarId = "primary";

        try {
            event = service.events().insert(calendarId, event).setConferenceDataVersion(1).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public  AccessTokenResponse createAcessTokenFromRefreshToken(String refreshToken){
        try {
            // Google OAuth 2.0 token endpoint
            URL url = new URL("https://oauth2.googleapis.com/token");

            // Construct the request payload
            String payload = "client_id=" + clientId
                    + "&client_secret="+clientSecret
                    + "&refresh_token=" + refreshToken
                    + "&grant_type=refresh_token";

            // Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            // Write payload to request body
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }

            // Parse response JSON
           AccessTokenResponse accessTokenResponse = parseAccessTokenResponse(response.toString());

            // Close connection
            conn.disconnect();

            return accessTokenResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static AccessTokenResponse parseAccessTokenResponse(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response, AccessTokenResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}



//








//



