package com.handsonhip.service;

import com.handsonhip.model.Marketing;
import com.handsonhip.model.Product;
import com.handsonhip.repository.MarketingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketingService {
    @Autowired
    private MarketingRepository marketingRepository;

     //This method generates marketing details using AI services
    public Marketing generateMarketingDetails(Product product) {
        //Code to call AI functionalities for generating title, description, platforms, and price will be added here
        String title = "AI Generated Title"; // Example data
        String description = "AI Generated Description";
        String platforms = "Platform1, Platform2";
        Double suggestedPrice = 99.99;

        Marketing marketing = new Marketing(product, title, description, platforms, suggestedPrice);
        return marketingRepository.save(marketing);
    }

    public List<Marketing> getMarketingResult(Long productID) {
        return marketingRepository.findByProductProductID(productID);
    }
}
