package com.leanplatform.MentorshipPlatform.services.implementation.User;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import com.leanplatform.MentorshipPlatform.dto.GenericResponseObject;
import com.leanplatform.MentorshipPlatform.dto.NE.NERequestObj;
import com.leanplatform.MentorshipPlatform.dto.Redis.RedisDto;
import com.leanplatform.MentorshipPlatform.dto.UserRegistration.*;
import com.leanplatform.MentorshipPlatform.entities.User.Creator;
import com.leanplatform.MentorshipPlatform.entities.User.Role;
import com.leanplatform.MentorshipPlatform.enums.UserAccountStatus;
import com.leanplatform.MentorshipPlatform.repositories.User.RoleRepository;
import com.leanplatform.MentorshipPlatform.repositories.User.CreatorRepository;
import com.leanplatform.MentorshipPlatform.services.JwtService;
import com.leanplatform.MentorshipPlatform.services.User.CreatorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class CreatorServiceImpl implements CreatorService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;


//    @Autowired
//    ExpertRepository expertRepository;


    @Value("${notificationengine.url}")
    private String neUrl;

    @Value("${NE.header.secret}")
    private String apiKey;

    @Value("${verify.email.secret}")
    private String emailSecret;

    @Value("${creatorx.baseurl}")
    private String baseUrl;


    @Override
    @Transactional
    public ResponseEntity<ResponseObj> registerUser(CreatorDto creatorDto)  {

        try{

//            log.info("Connecting to firebase");
//            Firestore dbFirestore= FirestoreClient.getFirestore();
//            DocumentReference documentReference=dbFirestore.collection("TPPBackend").document("BackendData");
//            ApiFuture<DocumentSnapshot> future=documentReference.get();
//            DocumentSnapshot document= future.get();
//            log.info("Connection to firebase successful");

//            log.info("Getting data from firebase");
//            Long maxOtp= (Long) document.get("MaxOtpGenerate");
//            int maxOtpGenerate=maxOtp.intValue();
//            log.info("Data from firebase fetched successfully");

            //checking if the user is already registered or not
//            Creator deactivatedCreator = creatorRepository.getDeactivatedAccount(creatorDto.getPhoneNo());
//            if(deactivatedCreator !=null){
//                return new ResponseEntity<>(ResponseObj.builder().responseCode(3).message("User account is deactivated and already exists").build(),HttpStatus.OK);
//            }

            log.info("Checking the db if the user is already registered or not");
            Creator creatorExisted = creatorRepository.getByEmailId(creatorDto.getEmailId());
            if(creatorExisted !=null && creatorExisted.getStatus().equals(UserAccountStatus.Active)){
                return new ResponseEntity<>(ResponseObj.builder().message("Email already Exists").responseCode(3).build(),HttpStatus.OK);
            }
            log.info("User details from the db fetched successfully and verified");


//            log.info("Generating the otp");
//            //generating a 6 digit random otp
//            String otp;
//            RedisDto dto=null;
//
//            log.info("Getting the otp details from the redis db");
//            String json= (String) redisTemplate.opsForValue().get(creatorDto.getPhoneNo());
//            log.info("Details from redis db fetched successfully");
//            if(json!=null){
//                log.info("Converting the json obj to Object");
//                dto=convertJsonToObject(json);
//                log.info("Converting the json obj to Object Successful");
//                otp=dto.getOtp();
//                log.info("Otp got from the redis db");
//            }
//            else{
//                otp=generateOtp();
//                log.info("New random otp generated");
//            }


//            if(dto!=null){
//
//               LocalDateTime dateTime=dto.getLastOtpSendAt();
//               LocalDateTime currDateTime=LocalDateTime.now();
//
//                if(dateTime.plusMinutes(10).isAfter(currDateTime)){
//
//                    int requestedOtpNumber=dto.getRequestedOtpNumber();
//                    if(requestedOtpNumber>=maxOtpGenerate){
//                        return new ResponseEntity<>(ResponseObj.builder().responseCode(4).message("Too many tries, try again after some time").build(),HttpStatus.OK);
//                    }
//
//                    sendOtp(creatorDto.getPhoneNo(),otp);
//                    requestedOtpNumber+=1;
//                    dto.setRequestedOtpNumber(requestedOtpNumber);
//                    String jsonObj=convertObjectToJson(dto);
//
//                    redisTemplate.opsForValue().set(creatorDto.getPhoneNo(),jsonObj,Duration.ofMinutes(60));
//                }
//                else{
//                    sendOtp(creatorDto.getPhoneNo(),otp);
//                    dto.setRequestedOtpNumber(1);
//                    LocalDateTime localDateTime=LocalDateTime.now();
//                    dto.setLastOtpSendAt(localDateTime);
//                    String jsonObj=convertObjectToJson(dto);
//                    redisTemplate.opsForValue().set(creatorDto.getPhoneNo(),jsonObj,Duration.ofMinutes(60));
//
//                }
//            }
            //sending the otp to the user
//            sendOtp(userDto.getPhoneNo(), otp);

            //storing the otp in the redis cache so that it can be used later for verifying otp
//            if(dto==null){
//
//                sendOtp(creatorDto.getPhoneNo(),otp);
//                LocalDateTime localDateTime=LocalDateTime.now();
//                RedisDto redisDto=RedisDto.builder().otp(otp).verifyOtpNumber(0).requestedOtpNumber(1).lastOtpSendAt(localDateTime).build();
//                String jsonValue=convertObjectToJson(redisDto);
//
//                redisTemplate.opsForValue().set(creatorDto.getPhoneNo(),jsonValue,Duration.ofMinutes(60));
//            }

            sendAccountActivationMail(creatorDto.getEmailId());


            Creator creator = creatorRepository.getPendingCreator(creatorDto.getPhoneNo());
            if(creator ==null) {
                creator = new Creator();
                creator.setEmail(creatorDto.getEmailId());
                creator.setStatus(UserAccountStatus.Pending);
                //user.setIsCampusUser(userDto.getIsCampusUser());
                creatorRepository.save(creator);
            }



            return new ResponseEntity<>(ResponseObj.builder().message("OTP sent Successfully").responseCode(1).build(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(ResponseObj.builder().message("Error :"+e.getLocalizedMessage()).build(),HttpStatus.OK);
        }



    }

    private void sendAccountActivationMail(String emailId) {

        //send the mail to the user
        String Data=emailId+":"+emailSecret;
        String encodedData=Base64.getEncoder().encodeToString(Data.getBytes());

        String verificationUrl=baseUrl+"/"+encodedData;

        System.out.println(verificationUrl);

    }

    private RedisDto convertJsonToObject(String json) throws JsonProcessingException {

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(json,RedisDto.class);
    }

    private String convertObjectToJson(RedisDto redisDto) throws JsonProcessingException {

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(redisDto);
    }

    private String generateOtp(){

        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        //System.out.println(number);
        return String.format("%06d", number);

    }

    private void sendOtp(String phoneNo,String otp) throws IOException {



        SmsPayload payload=new SmsPayload();
        payload.setNumber(new String[] {phoneNo});
        //payload.setMessage("Your Otp is "+otp);
        payload.setMessage("Your TheProductPlatform OTP for verification is: "+otp+". OTP is confidential, refrain from sharing it with anyone. By Edumarc Technologies");
        payload.setSenderId("EDUMRC");
        payload.setTemplateId("1707168926925165526");

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(payload);

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,json);

        Request request = new Request.Builder()
                .url("https://smsapi.edumarcsms.com/api/v1/sendsms")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("apikey", "clko5rj8v0001yvqxdoia06el")
                .build();


//        Response response = client.newCall(request).execute();

    }

    @Override
    @Transactional
    public ResponseEntity<ResponseObj> verifyOtp(CreatorDto creatorDto) throws JsonProcessingException, ExecutionException, InterruptedException {

        log.info("Connecting to firebase");
        Firestore dbFirestore= FirestoreClient.getFirestore();
        DocumentReference documentReference=dbFirestore.collection("TPPBackend").document("BackendData");
        ApiFuture<DocumentSnapshot> future=documentReference.get();
        DocumentSnapshot document= future.get();
        log.info("Connection to firebase successful");

        log.info("Getting data from firebase");
        Long maxVerifyAttempt= (Long) document.get("MaxVerifyAttempt");
        int maxVerifyNumber=maxVerifyAttempt.intValue();
        log.info("Data from firebase fetched successfully");

        String phoneNo= creatorDto.getPhoneNo();
        String otp= creatorDto.getOtp();

        RedisDto dto=null;

        Creator creator = creatorRepository.getPendingCreator(phoneNo);
        if(creator ==null){
            return new ResponseEntity<>(ResponseObj.builder().message("Invalid phone no.Try generating otp for the phone number again").responseCode(0).build(),HttpStatus.OK);
        }

        //getting the otp from the redis db
        String json= (String) redisTemplate.opsForValue().get(creatorDto.getPhoneNo());

        if(json==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("OTP expired,try generating new otp").build(),HttpStatus.OK);
        }

        dto=convertJsonToObject(json);

        LocalDateTime curr=LocalDateTime.now();
        LocalDateTime lastVerifyAttempt=dto.getLastOtpVerifiedAt();
        if(lastVerifyAttempt==null){
            lastVerifyAttempt=curr;
        }

        if(lastVerifyAttempt.plusMinutes(5).isAfter(curr)){

            if(dto.getVerifyOtpNumber()>=maxVerifyNumber){
                return new ResponseEntity<>(ResponseObj.builder().responseCode(3).message("Too many attempts,try again after some time").build(),HttpStatus.OK);
            }
        }
        else{
            dto.setVerifyOtpNumber(0);
            String jsonObj=convertObjectToJson(dto);
            redisTemplate.opsForValue().set(phoneNo,jsonObj);
        }

        if(otp.equals(dto.getOtp())){
//            userRepository.save(user);
            redisTemplate.opsForValue().getAndDelete(phoneNo);
            return new ResponseEntity<>(ResponseObj.builder().message("OTP Verified successfully").responseCode(1).isCampusUser(creator.getIsCampusUser()).build(),HttpStatus.OK);
        }
        else {

            int verifyOtpAttempt=dto.getVerifyOtpNumber();
            if(verifyOtpAttempt==0){
                LocalDateTime currDateTime=LocalDateTime.now();
                dto.setLastOtpVerifiedAt(currDateTime);
            }
            verifyOtpAttempt+=1;
            dto.setVerifyOtpNumber(verifyOtpAttempt);
            String jsonObject=convertObjectToJson(dto);
            redisTemplate.opsForValue().set(phoneNo,jsonObject);
            return new ResponseEntity<>(ResponseObj.builder().message("Invalid OTP").responseCode(0).build(),HttpStatus.OK);
        }
    }

    //after user has successfully verified the otp it will be redirected to set pin page and after entering the pin this api will get called
    @Override
    @Transactional
    public ResponseEntity<ResponseObj> setPin(CreatorDto creatorDto, String data){

        if(creatorDto ==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Null request object").build(),HttpStatus.OK);
        }
        if(creatorDto.getFullName()==null || creatorDto.getPhoneNo()==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Null values in required fields").build(),HttpStatus.OK);
        }

        byte[] decoded=Base64.getDecoder().decode(data);
        String original= new String(decoded);

        if(!original.contains(":")){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid Username password format").build(),HttpStatus.OK);
        }

        String userEmail=original.substring(0,original.indexOf(":"));
        String pin=original.substring(original.indexOf(":")+1);

//        byte[] decodedEmail=Base64.getDecoder().decode(username);
//        String decodedData=new String(decodedEmail);
//        String userEmail=decodedData.substring(0,original.indexOf(":"));

//        if(pin.length()!=4){
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid pin length, Pin can be only 4 digit").build(),HttpStatus.OK);
//        }

        boolean emailVerificationFlag=false;

        Creator creator=null;
        if(emailVerificationFlag) {
            creator = creatorRepository.getVerifiedCreator(userEmail);
        }
        else{
            creator=creatorRepository.getPendingCreator(userEmail);
            if(creator==null){
                creator=creatorRepository.getVerifiedCreator(userEmail);
            }
        }

        if(creator ==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Verify the email first.No creator with given email found").build(),HttpStatus.OK);
        }
//        User user= UserConverter.convertDtoToEntity(userDto);

//        String email=userDto.getEmailId();


//        User userByEmail=userRepository.findByEmail(email);
//        if(user!=null){
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(3).message("Email already exists, enter another email").build(),HttpStatus.OK);
//        }

        creator.setFullName(creatorDto.getFullName());
        creator.setStatus(UserAccountStatus.Active);
        creator.setAvatarId(creatorDto.getAvatarId());
        creator.setPhoneNo(creatorDto.getPhoneNo());
        //creator.setEmail(creatorDto.getEmailId());

        String[] name= creatorDto.getFullName().split(" ");
        String userUniqueId= creator.getId().toString().substring(0,6);
        String publicUrl="";
        for(String word:name){
            publicUrl+=word+"-";
        }
        publicUrl+=userUniqueId;
        creator.setPublicUrl(publicUrl);

//        if(userDto.getEmailId()!=null){
//            String email=userDto.getEmailId();
//
//
//            List<EmailDomains> emailDomains =emailNameRepository.findAll();
//
//            boolean flag=true;
//            if(!emailDomains.isEmpty()){
//                for(EmailDomains emailDomain : emailDomains){
//                    String name= emailDomain.getName();
//                    if(email.contains(name)){
//                        user.setIsEmailVerified(0);
//                        flag=false;
//                        break;
//                    }
//                }
//            }
//
//            if(flag){
//                user.setIsEmailVerified(1);
//            }
//
//
//        }


        String encodedPin=passwordEncoder.encode(pin);
        creator.setPin(encodedPin);

        Role role=roleRepository.getByName("ROLE_CREATOR");
        if(role==null){
            role=new Role();
            role.setName("ROLE_CREATOR");
            roleRepository.save(role);
        }

        List<Role> roles=new ArrayList<>();
        roles.add(role);
        creator.setRoles(roles);

        creator = creatorRepository.save(creator);
//        UserRequest userRequest=UserRequest.builder().username(userDto.getPhoneNo()).password(userDto.getPin()).build();

        String dataForToken=userEmail+":"+encodedPin;
        String encodedDataForToken=Base64.getEncoder().encodeToString(dataForToken.getBytes());
        ResponseEntity<JwtResponse> res=authenticateAndGetToken(encodedDataForToken);

        String token=res.getBody().getToken();


//        redisTemplate.opsForValue().getAndDelete(username);



            try{
                sendWelcomeMail(userEmail,"Welcome to TheProductPlatform");
            }
            catch (Exception e){
                log.info("Error in sending mail "+e.getLocalizedMessage());
            }



        CreatorDetails creatorDetails = CreatorDetails.builder().email(creator.getEmail()).phoneNo(creator.getPhoneNo()).username(creator.getFullName()).avatarId(creator.getAvatarId()).publicUrl(creator.getPublicUrl()).build();

        return new ResponseEntity<>(ResponseObj.builder().token(token).message("User created successfully").creatorDetails(creatorDetails).responseCode(1).build(),HttpStatus.CREATED);
    }

    private void sendOtpMail(String emailId, String subject, String body) {

        String genericMailUrl=neUrl+"/api/send-generic-mail";
        NERequestObj requestObj=NERequestObj.builder().to(emailId).subject(subject).data(body).build();

        WebClient webClient=WebClient.create();
        String response=webClient.post()
                .uri(genericMailUrl)
                .headers(headers->{
                    headers.add("ApiKey",apiKey);
                })
                .bodyValue(requestObj)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private void sendWelcomeMail(String emailId,String subject) throws ExecutionException, InterruptedException {

        log.info("Connecting to firebase");
        Firestore dbFirestore= FirestoreClient.getFirestore();
        DocumentReference documentReference=dbFirestore.collection("TPPBackend").document("BackendData");
        ApiFuture<DocumentSnapshot> future=documentReference.get();
        DocumentSnapshot document= future.get();
        log.info("Connection to firebase successful");

//        String verifyEmailUrl= (String) document.get("verifyEmailUrl");
        String verifyEmailUrl="https://backend.theproductplatform.in";
        String endpoint=emailId+":"+emailSecret;
        byte[] encoded=Base64.getEncoder().encode(endpoint.getBytes());
        String encodedEndpoint=new String(encoded);
        String url=verifyEmailUrl+"/user/verifyEmail"+"/"+encodedEndpoint;
        List<String> placeholders=new ArrayList<>();
        placeholders.add(url);

        String placeholderMailUrl=neUrl+"/api/send-placeholders-mail";
        NERequestObj requestObj=NERequestObj.builder().to(emailId).subject(subject).template("welcome").placeholders(placeholders).build();


        WebClient webClient=WebClient.create();
        String response=webClient.post()
                .uri(placeholderMailUrl)
                .headers(headers->{
                    headers.add("ApiKey",apiKey);
                })
                .bodyValue(requestObj)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseObj> deleteUser(String username) {

        Creator creator = creatorRepository.findByPhoneNo(username);
        if(creator ==null){
            return new ResponseEntity<>(ResponseObj.builder().message("No user found with given phone number").build(),HttpStatus.OK);
        }
        creatorRepository.delete(creator);
        return new ResponseEntity<>(ResponseObj.builder().message("User deleted successfully").build(),HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<JwtResponse> authenticateAndGetToken(String data) {

        byte[] decoded=Base64.getDecoder().decode(data);
        String original= new String(decoded);

        if(!original.contains(":")){
            return new ResponseEntity<>(JwtResponse.builder().responseCode(0).message("Invalid Username password format").build(),HttpStatus.OK);
        }

        String email=original.substring(0,original.indexOf(":"));
        String password=original.substring(original.indexOf(":")+1);

//        if(username.charAt(0)=='0' || username.charAt(0)=='1' || username.charAt(0)=='2' || username.charAt(0)=='3' || username.charAt(0)=='4'){
////
//            return new ResponseEntity<>(JwtResponse.builder().responseCode(0).message("Invalid phone no, Can't start with 0,1,2,3,4").build(),HttpStatus.OK);
//        }
//        if (username.matches("^" + username.charAt(0) + "+$")) {
//
//            return new ResponseEntity<>(JwtResponse.builder().responseCode(0).message("Invalid phone number, all digits can't be same").build(),HttpStatus.OK);
//        }

//        Creator existingCreator = creatorRepository.getDeactivatedAccount(username);
//        if(existingCreator !=null){
//
//            //String token=jwtService.generateToken(username);
//            return new ResponseEntity<>(JwtResponse.builder().responseCode(4).message("User account is deactivated").build(),HttpStatus.OK);
//        }

        log.info("Getting user from the db using phoneNo");
        Creator creator = creatorRepository.findByEmail(email);
        log.info("User fetched from db successfully");
        if(creator ==null){
            return new ResponseEntity<>(JwtResponse.builder().responseCode(2).message("User not registered").build(),HttpStatus.OK);
        }




        LocalDateTime currDateTime=LocalDateTime.now();
//        System.out.println(currDateTime);
        LocalDateTime lastAttemptedAt= creator.getLastLoginAttemptedAt();
//        System.out.println(lastAttemptedAt);
        if(lastAttemptedAt==null){
            lastAttemptedAt=currDateTime;
        }
        if(lastAttemptedAt.plusMinutes(5).isAfter(currDateTime)){

            if(creator.getFailedAttempt()>=7){
                return new ResponseEntity<>(JwtResponse.builder().responseCode(3).message("Too many wrong attempts, try again after some time").build(),HttpStatus.OK);
            }
        }
        else{
            creator.setFailedAttempt(0);
            creatorRepository.save(creator);
        }


        try{
            log.info("Authenticating the user phone no and pin");
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));

            if(authentication.isAuthenticated()){

                log.info("user phone no and pin authenticated successfully");
//            User loggedInUser=userRepository.findByPhoneNo(userRequest.getUsername());
                LocalDateTime dateTime=LocalDateTime.now();
                creator.setLastLoggedIn(dateTime);
                if(creator.getPublicUrl()==null){
                    String[] name= creator.getFullName().split(" ");
                    String userUniqueId= creator.getId().toString().substring(0,6);
                    String publicUrl="";
                    for(String word:name){
                        publicUrl+=word+"-";
                    }
                    publicUrl+=userUniqueId;
                    creator.setPublicUrl(publicUrl);
                }
                creatorRepository.save(creator);

                CreatorDetails creatorDetails = CreatorDetails.builder().email(creator.getEmail()).username(creator.getFullName()).phoneNo(creator.getPhoneNo()).avatarId(creator.getAvatarId()).id(creator.getId()).publicUrl(creator.getPublicUrl()).build();

                return new ResponseEntity<>(JwtResponse.builder().responseCode(1).token(jwtService.generateToken(email)).creatorDetails(creatorDetails).message("Username and password authenticated successfully").build(),HttpStatus.OK);
                //jwtService.generateToken(userRequest.getUsername());
            }
            else{
                log.info("User phone no and pin authentication failed");
                return new ResponseEntity<>(JwtResponse.builder().responseCode(0).message("Username and password Authentication failed").build(),HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception e){

            int failedAttempt= creator.getFailedAttempt();
            failedAttempt+=1;
            creator.setFailedAttempt(failedAttempt);
            LocalDateTime curr=LocalDateTime.now();
            creator.setLastLoginAttemptedAt(curr);
            creatorRepository.save(creator);

            return new ResponseEntity<>(JwtResponse.builder().responseCode(0).message("Invalid credentials.Enter correct details.").build(),HttpStatus.OK);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<ResponseObj> changePin(PinDto dto) {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Creator creator = creatorRepository.findByPhoneNo(username);

        String oldPin=dto.getOldPin();
        String newPin=dto.getNewPin();

        if(passwordEncoder.matches(oldPin, creator.getPin())){
            String newEncodedPin=passwordEncoder.encode(newPin);
            creator.setPin(newEncodedPin);

            creatorRepository.save(creator);
            return new ResponseEntity<>(ResponseObj.builder().responseCode(1).message("Pin reset successful").build(),HttpStatus.OK);
        }

        return new ResponseEntity<>(ResponseObj.builder().responseCode(2).message("Incorrect old pin").build(),HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseObj> sendOtpReset(PinDto dto) throws IOException, ExecutionException, InterruptedException {

        log.info("Connecting to firebase");
        Firestore dbFirestore= FirestoreClient.getFirestore();
        DocumentReference documentReference=dbFirestore.collection("TPPBackend").document("BackendData");
        ApiFuture<DocumentSnapshot> future=documentReference.get();
        DocumentSnapshot document= future.get();
        log.info("Connection to firebase successful");

        log.info("Getting values from firebase");
        Long maxOtp= (Long) document.get("MaxOtpGenerate");
        int maxOtpGenerate=maxOtp.intValue();
        log.info("Values from firebase fetched successfully");

        String phoneNo=dto.getPhoneNo();
        Creator creator = creatorRepository.findByPhoneNo(phoneNo);
        if(creator ==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(3).message("User not found with the given phone number.").build(),HttpStatus.OK);
        }

        String userEmail= creator.getEmail();

        String otp;
        RedisDto redisDto=null;

        String json= (String) redisTemplate.opsForValue().get(dto.getPhoneNo());

        if(json!=null){
            redisDto=convertJsonToObject(json);
            otp=redisDto.getOtp();
        }
        else{
            otp=generateOtp();
        }



        if(redisDto!=null){

            LocalDateTime currDateTime=LocalDateTime.now();
            if(redisDto.getLastOtpSendAt()==null){
                redisDto.setLastOtpSendAt(currDateTime);
            }
            LocalDateTime dateTime=redisDto.getLastOtpSendAt();


            if(currDateTime.minusMinutes(10).isAfter(dateTime)){
                sendOtp(dto.getPhoneNo(),otp);
                LocalDateTime localDateTime=LocalDateTime.now();
                redisDto.setLastOtpSendAt(localDateTime);
                redisDto.setRequestedOtpNumber(1);
                String jsonObj=convertObjectToJson(redisDto);
                redisTemplate.opsForValue().set(dto.getPhoneNo(),jsonObj,Duration.ofMinutes(60));
            }
            else{
                int requestedOtpNumber=redisDto.getRequestedOtpNumber();
                if(requestedOtpNumber>=maxOtpGenerate){
                    return new ResponseEntity<>(ResponseObj.builder().responseCode(4).message("Too many tries, try again after some time").build(),HttpStatus.OK);
                }

                sendOtp(dto.getPhoneNo(),otp);
                requestedOtpNumber+=1;
                redisDto.setRequestedOtpNumber(requestedOtpNumber);
                String jsonObj=convertObjectToJson(redisDto);

                redisTemplate.opsForValue().set(dto.getPhoneNo(),jsonObj,Duration.ofMinutes(60));
            }
        }

        //sending the otp to the user
        //sendOtp(dto.getPhoneNo(), otp);

        if(redisDto==null){

            sendOtp(dto.getPhoneNo(),otp);
            LocalDateTime dateTime=LocalDateTime.now();
            RedisDto newDto=RedisDto.builder().otp(otp).verifyOtpNumber(0).requestedOtpNumber(1).lastOtpSendAt(dateTime).build();
            String jsonValue=convertObjectToJson(newDto);

            redisTemplate.opsForValue().set(dto.getPhoneNo(),jsonValue,Duration.ofMinutes(60));
        }

        if(userEmail!=null && creator.getIsEmailVerified()==1){

            try{
                sendOtpMail(userEmail,"Reset Pin", otp);
            }
            catch (Exception e){
                log.info("Error in sending mail");
            }
        }





        return new ResponseEntity<>(ResponseObj.builder().message("OTP sent Successfully").responseCode(1).build(),HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseObj> resetPin(PinDto dto) {

        if(dto.getNewPin().length()!=4){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid pin length. Length must be 4").build(),HttpStatus.OK);
        }

        Creator creator = creatorRepository.findByPhoneNo(dto.getPhoneNo());

        String encodedPin=passwordEncoder.encode(dto.getNewPin());

        creator.setPin(encodedPin);
        creatorRepository.save(creator);

        return new ResponseEntity<>(ResponseObj.builder().responseCode(1).message("Pin reset successful").build(),HttpStatus.OK);
    }

//    @Override
//    @Transactional
//    public void processOAuthPostLogin(String email, String name, String phoneNo) {
//        User user=userRepository.findByEmail(email);
//        if(user==null){
//            User newUser=User.builder().email(email).fullName(name).phoneNo(phoneNo).build();
//            Role role=roleRepository.getByName("ROLE_USER");
//            //System.out.println("Role= "+ role.getName()+ role.getId());
//            if(role==null){
//                role=new Role();
//                role.setName("ROLE_USER");
//                roleRepository.save(role);
//            }
//
//            List<Role> roles=new ArrayList<>();
//            roles.add(role);
//
//            newUser.setRoles(roles);
//            userRepository.save(newUser);
//            Expert expert=new Expert();
//            expert.setName(name);
//            expert.setEmail(email);
//            expert.setStatus("Pending");
//            expertRepository.save(expert);
//
//        }
//    }

    @Override
    public ResponseEntity<ResponseObj> checkUser(CreatorDto creatorDto) {

        String email= creatorDto.getEmailId();

//        Creator creatorDeactivated = creatorRepository.getDeactivatedAccount(phoneNo);
//        if(creatorDeactivated !=null){
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(1).message("User account is deactivated").build(),HttpStatus.OK);
//        }


        Creator creator = creatorRepository.findByEmail(email);
        if(creator ==null ){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(2).message("User not found").build(),HttpStatus.OK);
        }

        if(creator.getStatus().equals(UserAccountStatus.Pending)){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(3).message("User Account is Pending").build(),HttpStatus.OK);
        }

        return new ResponseEntity<>(ResponseObj.builder().responseCode(1).build(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseObj> getDetails() {

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Creator creator = creatorRepository.findByEmail(username);

        LocalDateTime dateTime=LocalDateTime.now();
        creator.setLastLoggedIn(dateTime);
        creatorRepository.save(creator);

        CreatorDetails creatorDetails = CreatorDetails.builder()
                .username(creator.getFullName()).id(creator.getId()).avatarId(creator.getAvatarId()).email(creator.getEmail())
                .createdAt(creator.getCreatedAt()).phoneNo(creator.getPhoneNo()).build();

        return new ResponseEntity<>(ResponseObj.builder().responseCode(1).creatorDetails(creatorDetails).message("User details fetched successfully").build(),HttpStatus.OK);
    }

    @Override
    public void verifyEmail(HttpServletResponse response, String data) throws IOException {

        byte[] decoded= Base64.getDecoder().decode(data);
        String decodedData=new String(decoded);
        if(!decodedData.contains(":")){
            return;
        }
        String email=decodedData.substring(0,decodedData.indexOf(":"));
        String secret=decodedData.substring(decodedData.indexOf(":")+1);
        if(!secret.equals(emailSecret)){
            return;
        }

        String redirectUrl="https://theproductplatform.in";

        Creator creator = creatorRepository.getPendingCreator(email);
        if(creator ==null){
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", redirectUrl);
            response.sendRedirect(redirectUrl);
        }

        if(creator.getStatus().equals(UserAccountStatus.Pending)){

            redirectUrl="https://theproductplatform.in/setPin";
            creator.setIsEmailVerified(1);
            creator.setStatus(UserAccountStatus.Verified);
            creatorRepository.save(creator);
        }



        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", redirectUrl);
        response.sendRedirect(redirectUrl);
    }

    @Override
    public ResponseObj checkEmail(String email) {

        Creator creator = creatorRepository.findByEmail(email);
        if(creator !=null && creator.getIsEmailVerified()==1){
            return ResponseObj.builder().responseCode(2).message("Email already exists").build();
        }

        return ResponseObj.builder().responseCode(1).message("Email doesn't exist").build();
    }

    @Override
    public GenericResponseObject addExpertRole(String phoneNo, String linkedInUrl) {


        Creator creator = creatorRepository.findByPhoneNo(phoneNo);

        if(creator ==null){
            return GenericResponseObject.builder().responseCode(0).message("Invalid phone number or number doesn't exists").build();
        }

        Role role=roleRepository.getByName("ROLE_EXPERT");
        if(role==null){

            role=new Role();
            role.setName("ROLE_EXPERT");
            roleRepository.save(role);
        }

        List<Role> roles= creator.getRoles();
        roles.add(role);
        creator.setRoles(roles);
        creator.setUrl(linkedInUrl);
        creatorRepository.save(creator);


        return GenericResponseObject.builder().responseCode(1).message("Expert role added successfully").build();
    }


}
