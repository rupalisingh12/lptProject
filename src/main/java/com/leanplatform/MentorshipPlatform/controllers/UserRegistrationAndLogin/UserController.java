package com.leanplatform.MentorshipPlatform.controllers.UserRegistrationAndLogin;


import com.leanplatform.MentorshipPlatform.dto.UserRegistration.JwtResponse;
import com.leanplatform.MentorshipPlatform.dto.UserRegistration.PinDto;
import com.leanplatform.MentorshipPlatform.dto.UserRegistration.ResponseObj;
import com.leanplatform.MentorshipPlatform.dto.UserRegistration.CreatorDto;
import com.leanplatform.MentorshipPlatform.repositories.User.CreatorRepository;
import com.leanplatform.MentorshipPlatform.services.implementation.User.CreatorServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
public class UserController {

    @Autowired
    CreatorServiceImpl userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final String secret="$2a$10$zaK8sK/MnGYVeSLBwDmKjeP6HWTVm.8TIO5TTif3XKl1/40J8kTIG";

    @Autowired
    CreatorRepository creatorRepository;

    @Value("${verify.email.secret}")
    private String emailSecret;

//    @Value("${header.secret}")
//    private String secret;

    @GetMapping("/")
    public String home(){
        return "Server is up and running";
    }

    @PostMapping("/registerUser")
    public ResponseEntity<ResponseObj> registerUser(@RequestBody CreatorDto creatorDto, @RequestHeader("Secret-Key")String secretKey) {

        if(creatorDto ==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Null request object").build(),HttpStatus.BAD_REQUEST);
        }
        String phoneNo= creatorDto.getPhoneNo();

        if(!passwordEncoder.matches(secretKey,secret)){
            return new ResponseEntity<>(ResponseObj.builder().message("Unauthorized Request").responseCode(0).build(),HttpStatus.OK);
        }
//        if(phoneNo==null || phoneNo.length()!=10){
//            return new ResponseEntity<>(ResponseObj.builder().message("Enter your phone Number").responseCode(0).build(), HttpStatus.BAD_REQUEST);
//        }
//        if(phoneNo.charAt(0)=='0' || phoneNo.charAt(0)=='1' || phoneNo.charAt(0)=='2' || phoneNo.charAt(0)=='3' || phoneNo.charAt(0)=='4'){
//
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone no").build(),HttpStatus.OK);
//        }
//        if (phoneNo.matches("^" + phoneNo.charAt(0) + "+$")) {
//
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone number, all digits can't be same").build(),HttpStatus.OK);
//        }
        try{
            return userService.registerUser(creatorDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(ResponseObj.builder().message("Error "+e).build(),HttpStatus.OK);
        }

    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<ResponseObj> verifyOtp(@RequestBody CreatorDto creatorDto, @RequestHeader("Secret-Key")String secretKey){

        if(!passwordEncoder.matches(secretKey,secret)){
            return new ResponseEntity<>(ResponseObj.builder().message("Unauthorized Request").responseCode(0).build(),HttpStatus.OK);
        }
        if(creatorDto ==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Null request object").build(),HttpStatus.OK);
        }
        if(creatorDto.getPhoneNo()==null || creatorDto.getOtp()==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Error empty field").build(),HttpStatus.OK);
        }
        if(creatorDto.getPhoneNo().charAt(0)=='0' || creatorDto.getPhoneNo().charAt(0)=='1' || creatorDto.getPhoneNo().charAt(0)=='2' || creatorDto.getPhoneNo().charAt(0)=='3' || creatorDto.getPhoneNo().charAt(0)=='4'){

            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone no").build(),HttpStatus.OK);
        }
        if (creatorDto.getPhoneNo().matches("^" + creatorDto.getPhoneNo().charAt(0) + "+$")) {

            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone number, all digits can't be same").build(),HttpStatus.OK);
        }
        if(creatorDto.getPhoneNo().length()!=10){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone number length").build(),HttpStatus.OK);
        }
        try {
            return userService.verifyOtp(creatorDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(ResponseObj.builder().message("Error "+e).responseCode(0).build(),HttpStatus.OK);
        }

    }

    @PostMapping("/setPin")
    public ResponseEntity<ResponseObj> setPin(@RequestBody CreatorDto creatorDto, @RequestHeader("Secret-Key")String secretKey, @RequestHeader("Bt")String bt){

        if(!passwordEncoder.matches(secretKey,secret)){
            return new ResponseEntity<>(ResponseObj.builder().message("Unauthorized Request").responseCode(0).build(),HttpStatus.OK);
        }
        if(bt==null){
            return new ResponseEntity<>(ResponseObj.builder().message("Error empty field,username and password empty").responseCode(0).build(),HttpStatus.OK);
        }
        if(creatorDto ==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Null request object").build(),HttpStatus.OK);
        }
        if(creatorDto.getFullName()==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Null values in required fields").build(),HttpStatus.OK);
        }
//        if(userDto.getPhoneNo().charAt(0)=='0' || userDto.getPhoneNo().charAt(0)=='1' || userDto.getPhoneNo().charAt(0)=='2' || userDto.getPhoneNo().charAt(0)=='3' || userDto.getPhoneNo().charAt(0)=='4'){
//
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone no").build(),HttpStatus.OK);
//        }
//        if (userDto.getPhoneNo().matches("^" + userDto.getPhoneNo().charAt(0) + "+$")) {
//
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone number, all digits can't be same").build(),HttpStatus.OK);
//        }
//        if(userDto.getPhoneNo().length()!=10){
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone number length").build(),HttpStatus.OK);
//        }
        try{
            return userService.setPin(creatorDto,bt);
        }
        catch (Exception e){
            return new ResponseEntity<>(ResponseObj.builder().message("Error "+e).responseCode(0).build(),HttpStatus.OK);
        }
    }

    @PostMapping("/sendOtpReset")
    public ResponseEntity<ResponseObj> sendOtp(@RequestBody PinDto dto, @RequestHeader("Secret-Key")String secretKey){

        if(!passwordEncoder.matches(secretKey,secret)){
            return new ResponseEntity<>(ResponseObj.builder().message("Unauthorized Request").responseCode(0).build(),HttpStatus.OK);
        }
        if(dto.getPhoneNo()==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Enter a valid phone number").build(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getPhoneNo().charAt(0)=='0' || dto.getPhoneNo().charAt(0)=='1' || dto.getPhoneNo().charAt(0)=='2' || dto.getPhoneNo().charAt(0)=='3' || dto.getPhoneNo().charAt(0)=='4'){

            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone no").build(),HttpStatus.OK);
        }
        if (dto.getPhoneNo().matches("^" + dto.getPhoneNo().charAt(0) + "+$")) {

            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone number, all digits can't be same").build(),HttpStatus.OK);
        }
        if(dto.getPhoneNo().length()!=10){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone number length").build(),HttpStatus.OK);
        }
        try {
            return userService.sendOtpReset(dto);
        }
        catch (Exception e){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Error: "+e).build(),HttpStatus.OK);
        }
    }

    @PostMapping("/resetPin")
    public ResponseEntity<ResponseObj> resetPin(@RequestBody PinDto dto,@RequestHeader("Secret-Key")String secretKey){

        if(!passwordEncoder.matches(secretKey,secret)){
            return new ResponseEntity<>(ResponseObj.builder().message("Unauthorized Request").responseCode(0).build(),HttpStatus.OK);
        }
        if(dto.getNewPin()==null || dto.getPhoneNo()==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Phone number or pin can't be null.").build(),HttpStatus.BAD_REQUEST);
        }
        if(dto.getPhoneNo().length()!=10){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone number length").build(),HttpStatus.OK);
        }
        if(dto.getPhoneNo().charAt(0)=='0' || dto.getPhoneNo().charAt(0)=='1' || dto.getPhoneNo().charAt(0)=='2' || dto.getPhoneNo().charAt(0)=='3' || dto.getPhoneNo().charAt(0)=='4'){

            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone no").build(),HttpStatus.OK);
        }
        if (dto.getPhoneNo().matches("^" + dto.getPhoneNo().charAt(0) + "+$")) {

            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone number, all digits can't be same").build(),HttpStatus.OK);
        }
        try {
            return userService.resetPin(dto);
        }
        catch (Exception e){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Error: "+e).build(),HttpStatus.OK);
        }

    }

    @PostMapping("/changePin")
    public ResponseEntity<ResponseObj> changePin(@RequestBody PinDto dto){

        if(dto==null || dto.getOldPin()==null || dto.getNewPin()==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Required fields can't be null in request object").build(),HttpStatus.OK);
        }
        if(dto.getNewPin().length()!=4){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid pin length.Pin lenght must be 4").build(),HttpStatus.BAD_REQUEST);
        }
        try {
            return userService.changePin(dto);
        }
        catch (Exception e){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Error: "+e).build(),HttpStatus.OK);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateAndGetToken(@RequestHeader("Secret-Key")String secretKey, @RequestHeader("Bt")String bt){

        if(!passwordEncoder.matches(secretKey,secret)){
            return new ResponseEntity<>(JwtResponse.builder().message("Unauthorized Request").responseCode(0).build(),HttpStatus.OK);
        }
        if(bt==null){
            return new ResponseEntity<>(JwtResponse.builder().responseCode(0).message("Invalid username and password").build(),HttpStatus.BAD_REQUEST);
        }
        try {
            return userService.authenticateAndGetToken(bt);
        }
        catch (Exception e){
            return new ResponseEntity<>(JwtResponse.builder().responseCode(0).message("Invalid credentials.Enter correct details.").build(),HttpStatus.OK);
        }

    }

    @GetMapping("/oAuthSuccess")
    public ResponseEntity<JwtResponse> login(@RequestParam("token")String token){

        return new ResponseEntity<>(JwtResponse.builder().responseCode(1).message("User signup/login successful").token(token).build(),HttpStatus.OK);
    }


    @PostMapping("/checkUser")
    public ResponseEntity<ResponseObj> checkUser(@RequestBody CreatorDto creatorDto){

        if(creatorDto ==null){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Null request object").build(),HttpStatus.OK);
        }
//        if (creatorDto.getPhoneNo()==null || creatorDto.getPhoneNo().length()!=10){
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Phone number can't be null or invalid length").build(),HttpStatus.OK);
//        }
//        if(creatorDto.getPhoneNo().charAt(0)=='0' || creatorDto.getPhoneNo().charAt(0)=='1' || creatorDto.getPhoneNo().charAt(0)=='2' || creatorDto.getPhoneNo().charAt(0)=='3' || creatorDto.getPhoneNo().charAt(0)=='4'){
//
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone no").build(),HttpStatus.OK);
//        }
//        if (creatorDto.getPhoneNo().matches("^" + creatorDto.getPhoneNo().charAt(0) + "+$")) {
//
//            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Invalid phone number, all digits can't be same").build(),HttpStatus.OK);
//        }

        try {
            return userService.checkUser(creatorDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Error "+e).build(),HttpStatus.OK);
        }
    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<ResponseObj> getUserDetails(){
        try {
            return userService.getDetails();
        }
        catch (Exception e){
            return new ResponseEntity<>(ResponseObj.builder().responseCode(0).message("Error: "+e.getLocalizedMessage()).build(),HttpStatus.OK);
        }
    }

    @GetMapping("/user/checkEmail")
    public ResponseObj checkEmail(@RequestParam("email") String email){

        try{
            return userService.checkEmail(email);
        }
        catch (Exception e){
            return ResponseObj.builder().responseCode(0).message("Error: "+e.getLocalizedMessage()).build();
        }
    }

//    @GetMapping("/user/checkEmail")
//    public ResponseObj checkEmail(@RequestParam("email") String email){
//
//        try{
//            return userService.checkEmail(email);
//        }
//        catch (Exception e){
//            return ResponseObj.builder().responseCode(0).message("Error: "+e.getLocalizedMessage()).build();
//        }
//    }


//    @GetMapping("/user/deleteMyRecord")
//    public String deleteUser(@RequestParam("phoneNo")String phoneNo) {
//
//        User user = userRepository.findByPhoneNo(phoneNo);
//        if (user == null) {
//            return "Invalid phoneNo";
//        }
//
//        user.getRoles().clear();
//        user=userRepository.save(user);
//        userRepository.delete(user);
//        return "User deleted successfully";
//    }

    @GetMapping("/user/verifyEmail/{data}")
    public void verifyEmail(HttpServletResponse response,@PathVariable("data")String data) throws IOException {

        try{
            userService.verifyEmail(response,data);
        }
        catch (Exception e){
            log.error("Error in verifying email "+e.getLocalizedMessage());
        }


    }

//    @GetMapping("/testing")
//    public String getUrl() throws ExecutionException, InterruptedException {
//
//
//        Firestore dbFirestore= FirestoreClient.getFirestore();
//        DocumentReference documentReference=dbFirestore.collection("TPPBackend").document("BackendData");
//        ApiFuture<DocumentSnapshot> future=documentReference.get();
//        DocumentSnapshot document= future.get();
//
//        String verifyEmailUrl= (String) document.get("verifyEmailUrl");
//
//        String email="vineetsingh1718@gmail.com";
//        String secret="EpeITuJfqIgBEmfXYBkr";
//        String added=email+":"+secret;
//
//        byte[] bytes=Base64.getEncoder().encode(added.getBytes());
//        String encoded=new String(bytes);
//
//        return encoded;
//    }

}
