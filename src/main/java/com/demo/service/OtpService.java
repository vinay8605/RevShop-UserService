package com.demo.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {
    private Map<String, Integer> otpData = new HashMap<>();

    public int generateOtp(String email) {
        int otp = new Random().nextInt(900000) + 100000;
        otpData.put(email, otp);
        return otp;
    }

    public boolean verifyOtp(String email, int otp) {
        Integer storedOtp = otpData.get(email);
        return storedOtp != null && storedOtp == otp;
    }
}