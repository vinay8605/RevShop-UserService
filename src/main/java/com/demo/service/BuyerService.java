package com.demo.service;

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
        // Find buyer by email and password
        return buyerRepository.findByEmailAndPassword(email, password).isPresent();
    }
}

