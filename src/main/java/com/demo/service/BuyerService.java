package com.demo.service;

import java.util.Optional;

//import java.util.Arrays;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;

import com.demo.entity.Buyer;
import com.demo.repository.BuyerRepository;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;
   

    public Buyer registerBuyer(Buyer buyer) {
        buyer.setApprovalStatus(false); // Default approval status is false (requires admin approval)
        return buyerRepository.save(buyer);
    }
    public boolean authenticateBuyer(String email, String password) {
        Optional<Buyer> buyerOpt = buyerRepository.findByEmailAndPassword(email, password);
        
        if (buyerOpt.isPresent()) {
            Buyer buyer = buyerOpt.get();
            // Check if the buyer's approval status is true (1)
            return buyer.isApprovalStatus(); // Assuming approvalStatus is a boolean field
        }
        
        return false; // If no matching buyer or approval status is false
    }
  public Buyer getBuyerByEmail(String email) {
        return buyerRepository.findByEmail(email);
    }
    
    public void updatePassword(String email, String password) {
        Buyer buyer = getBuyerByEmail(email);
        if (buyer != null) {
            buyer.setPassword(password); // Optionally, hash the password
            buyerRepository.save(buyer);
        }
    }
    
    public Buyer getBuyerById(Long buyerId) {
        return buyerRepository.findById(buyerId).orElse(null);
    }
    public void updateBuyerAddress(Long buyerId, String address, String city, String state, String pincode, String phoneNumber) throws Exception {
        Buyer buyer = buyerRepository.findById(buyerId)
                                     .orElseThrow(() -> new Exception("Buyer not found"));

        buyer.setAddress(address);
        buyer.setCity(city);
        buyer.setState(state);
        buyer.setPincode(pincode);
        buyer.setPhoneNumber(phoneNumber);

        buyerRepository.save(buyer);  // Save the updated buyer details
    }
}

