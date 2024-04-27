package com.example.sonrisaplantjv.domain.model.Review;

import com.example.sonrisaplantjv.domain.model.Order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Review {
    public UUID Id;
    public String Content;
    public String Images;
    public LocalDateTime Time;
    public ReviewRating ReviewRating;
    public UUID IdPlant;
    public UUID IdUser;

    public Review() {
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public LocalDateTime getTime() {
        return Time;
    }

    public void setTime(LocalDateTime time) {
        Time = time;
    }

    public com.example.sonrisaplantjv.domain.model.Review.ReviewRating getReviewRating() {
        return ReviewRating;
    }

    public void setReviewRating(com.example.sonrisaplantjv.domain.model.Review.ReviewRating reviewRating) {
        ReviewRating = reviewRating;
    }

    public UUID getIdPlant() {
        return IdPlant;
    }

    public void setIdPlant(UUID idPlant) {
        IdPlant = idPlant;
    }

    public UUID getIdUser() {
        return IdUser;
    }

    public void setIdUser(UUID idUser) {
        IdUser = idUser;
    }
}
