package com.example.sonrisaplantjv.domain.dto.Review;

import androidx.annotation.Nullable;

import com.example.sonrisaplantjv.common.ActionResponse;
import com.example.sonrisaplantjv.domain.model.Review.ReviewRating;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewDto extends ActionResponse<ReviewDto> {
    public UUID Id;
    public String Content;
    @Nullable
    public String Images;
    public LocalDateTime Time;
    public ReviewRating ReviewRating;
    public UUID IdUser;
}
