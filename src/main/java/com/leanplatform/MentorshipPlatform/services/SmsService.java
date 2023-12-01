package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.config.TwilioConfig;
import com.leanplatform.MentorshipPlatform.dto.OtpResponseDto;
import com.leanplatform.MentorshipPlatform.enums.OtpStatus;
import com.leanplatform.MentorshipPlatform.dto.OtpValidationRequest;
import com.leanplatform.MentorshipPlatform.dto.PhoneNum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

import java.text.DecimalFormat;
import java.util.*;

@Service
@Slf4j
public class SmsService {

    @Autowired
    private TwilioConfig twilioConfig;
    HashSet<String> hs = new HashSet<>();


    public OtpResponseDto sendSMS(PhoneNum phoneNum) {
        OtpResponseDto otpResponseDto = null;
        try {
            PhoneNumber to = new PhoneNumber(phoneNum.getPhoneNumber());//to
            PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber()); // from
            String otp = generateOTP();
            String otpMessage = "Dear User , Your OTP is  " + otp ;
            Message.creator(to, from, otpMessage).create();
            hs.add(otp);
            otpResponseDto = new OtpResponseDto(OtpStatus.DELIVERED, otpMessage);
        } catch (Exception e) {
            e.printStackTrace();
            otpResponseDto = new OtpResponseDto(OtpStatus.FAILED, e.getMessage());
        }
        return otpResponseDto;
    }

    public String validateOtp(OtpValidationRequest otpValidationRequest) {

        if (hs.contains(otpValidationRequest.getOtpNumber())) {
            hs.clear();
            return "OTP is valid!";
        } else {
            return "OTP is invalid!";
        }
    }

    private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }

}
