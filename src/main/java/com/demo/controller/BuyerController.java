package com.demo.controller;

import java.io.IOException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;
import com.demo.entity.Buyer;
import com.demo.service.BuyerService;
import com.demo.service.EmailService;
import com.demo.service.OtpService;


import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/registercr")
@SessionAttributes("buyerId")  // Storing buyerId in the session
public class BuyerController {
    
    @Autowired
    private BuyerService buyerService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private OtpService otpService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("buyer")) {
            model.addAttribute("buyer", new Buyer());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerBuyer(@Valid @ModelAttribute("buyer") Buyer buyer, 
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        try {
            // Handle the image file
            MultipartFile imageFile = buyer.getImageFile();
            if (imageFile != null && !imageFile.isEmpty()) {
                buyer.setImage(imageFile.getBytes());
            }
            
            buyerService.registerBuyer(buyer);
            
            try {
                emailService.sendRegistrationEmail(buyer);
            } catch (MessagingException e) {
                model.addAttribute("errorMessage", "Failed to send confirmation email: " + e.getMessage());
                // Log the error for debugging
                e.printStackTrace();
            }
            
            return "login";
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Error processing image file");
            return "register";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred during registration: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        if (!model.containsAttribute("buyer")) {
            model.addAttribute("buyer", new Buyer());
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model, HttpServletResponse response) {
        // Authenticate user based on email, password, and approval status
        boolean isAuthenticated = buyerService.authenticateBuyer(email, password);

        if (isAuthenticated) {
            Buyer buyer = buyerService.getBuyerByEmail(email);

            // Set the buyerId in the cookie
            Cookie buyerCookie = new Cookie("buyerId", String.valueOf(buyer.getId()));
            buyerCookie.setPath("/");  // Ensure the cookie is available across the whole domain
            buyerCookie.setMaxAge(3600);  // Set the cookie expiration time (1 hour)
            response.addCookie(buyerCookie);

            model.addAttribute("welcomeMessage", "Login successful. Welcome!");
            return "redirect:http://localhost:8099/products";  // Redirect to products page
        } else {
            model.addAttribute("errorMessage", "Invalid email or password, or account not approved.");
            return "login";  // Return to the login page with error message
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie buyerCookie = new Cookie("buyerId", "");
        buyerCookie.setMaxAge(0);  // Expire the cookie immediately
        buyerCookie.setPath("/");  // Ensure it's removed from the entire domain
        response.addCookie(buyerCookie);
        return "redirect:/login";
    }
    
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password";
    }
    
    @PostMapping("/forgot-password")
    public String sendOtp(@RequestParam("email") String email, Model model) {
        Buyer buyer = buyerService.getBuyerByEmail(email);
        if (buyer == null) {
            model.addAttribute("errorMessage", "Email not registered");
            return "forgot-password";
        }
        
        // Generate and send OTP
        int otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email, otp,buyer);
        model.addAttribute("email", email); // Pass email for next step
        return "verify-otp";
    }
    
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam("email") String email,
                            @RequestParam("otp") int otp,
                            Model model) {
        if (otpService.verifyOtp(email, otp)) {
            model.addAttribute("email", email);
            return "reset-password";
        } else {
            model.addAttribute("errorMessage", "Invalid OTP. Please try again.");
            return "verify-otp";
        }
    }
    
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                Model model) {
        buyerService.updatePassword(email, password);
        model.addAttribute("successMessage", "Password successfully updated. Please log in.");
        return "reset-success";
    }

}
