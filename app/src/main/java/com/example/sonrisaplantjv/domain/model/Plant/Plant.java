package com.example.sonrisaplantjv.domain.model.Plant;

import com.example.sonrisaplantjv.domain.model.Order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Plant {
    public UUID Id;
    public String Name;
    public BigDecimal Price;
    public String Description;
    public String Image;
    public BigDecimal RatingPoint;
    public UUID CategoryId;

    public Plant() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public BigDecimal getRatingPoint() {
        return RatingPoint;
    }

    public void setRatingPoint(BigDecimal ratingPoint) {
        RatingPoint = ratingPoint;
    }

    public UUID getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(UUID categoryId) {
        CategoryId = categoryId;
    }
}
