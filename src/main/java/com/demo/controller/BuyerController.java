package com.demo.controller;

import java.io.IOException;
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

@Controller
@RequestMapping("/registercr")
public class BuyerController {
    
    @Autowired
    private BuyerService buyerService;
    
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
            return "login";
        } catch (IOException e) {
            model.addAttribute("errorMessage", "Error processing image file");
            return "register";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred during registration: " + e.getMessage());
            return "register";
        }
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                          @RequestParam("password") String password,
                          Model model) {
        boolean isAuthenticated = buyerService.authenticateBuyer(email, password);
        System.out.print(isAuthenticated);
        if (isAuthenticated) {
            model.addAttribute("welcomeMessage", "Login successful. Welcome!");
            return "redirect:http://localhost:8099/products";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }
}
