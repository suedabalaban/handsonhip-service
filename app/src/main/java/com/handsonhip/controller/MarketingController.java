package com.handsonhip.controller;

import com.handsonhip.model.Marketing;
import com.handsonhip.model.Product;
import com.handsonhip.service.MarketingService;
import com.handsonhip.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marketing")
public class MarketingController {

    @Autowired
    private MarketingService marketingService;

    @Autowired
    private ProductService productService;

    @PostMapping("/generate/{productId}")
    public ResponseEntity<Marketing> generateMarketingDetails(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        Marketing marketing = marketingService.generateMarketingDetails(product);
        return ResponseEntity.ok(marketing);
    }

    @GetMapping("/result/{productId}")
    public ResponseEntity<List<Marketing>> getMarketingResult(@PathVariable Long productId) {
        List<Marketing> marketingResults = marketingService.getMarketingResult(productId);
        return ResponseEntity.ok(marketingResults);
    }
}
