package com.handsonhip.model;

import jakarta.persistence.*;

@Entity
@Table(name = "marketing")
public class Marketing {
    //Table attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marketingID")
    private Long marketingID;

    @ManyToOne
    @JoinColumn(name = "productID", nullable = false)
    private Product product;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "platforms")
    private String platforms;

    @Column(name = "suggestedPrice")
    private Double suggestedPrice;

    // No-arg constructor
    public Marketing() {
    }

    // All-args constructor
    public Marketing(Product product, String title, String description, String platforms, Double suggestedPrice) {
        this.product = product;
        this.title = title;
        this.description = description;
        this.platforms = platforms;
        this.suggestedPrice = suggestedPrice;
    }

    // Getters and Setters
    public Long getMarketingID() {
        return marketingID;
    }

    public void setMarketingID(Long marketingID) {
        this.marketingID = marketingID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public Double getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(Double suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }
}
