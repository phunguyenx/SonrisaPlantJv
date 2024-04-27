package com.example.sonrisaplantjv.data.remote.dto.Review;

import androidx.annotation.Nullable;

import com.example.sonrisaplantjv.common.ActionResponse;
import com.example.sonrisaplantjv.domain.model.Review.ReviewRating;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
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
