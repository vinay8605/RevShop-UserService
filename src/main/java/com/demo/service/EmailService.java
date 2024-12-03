package com.demo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.demo.entity.Buyer;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    public void sendOtpEmail(String toEmail, int otp, Buyer buyer) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Password Reset OTP - Action Required");

        String emailContent = "Hello " + buyer.getName() + ",\n\n"  // Added a space after "Hello"
            + "We received a request to reset your password. Please use the One-Time Password (OTP) below to complete your password reset process.\n\n"
            + "Your OTP is: " + otp + "\n\n"
            + "This OTP is valid for the next 10 minutes. If you did not request a password reset, please ignore this email.\n\n"
            + "Best regards,\n"
            + "Monagallu ✊🏻";  // Added space before emoji

        message.setText(emailContent);
        mailSender.send(message);
    }



    public void sendRegistrationEmail(Buyer buyer) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(buyer.getEmail());
        helper.setSubject("Registration Confirmation");
        helper.setText("<html><body>" +
                       "<h2>Dear " + buyer.getName() + ",</h2>" +
                       "<p>Thank you for registering! Here are your registration details:</p>" +
                       "<p><b>Name:</b> " + buyer.getName() + "<br>" +
                       "<b>Email:</b> " + buyer.getEmail() + "<br>" +
                       "<b>Password:</b> " + buyer.getPassword() + "<br>" +
                       "<b>Phone:</b> " + buyer.getPhoneNumber() + "</p>" +
                       "<p>Best regards,<br>Monagallu✊🏻</p>" +
                       "</body></html>", true);

        mailSender.send(message);
    }
}