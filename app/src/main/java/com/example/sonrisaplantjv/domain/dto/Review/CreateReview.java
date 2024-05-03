package com.example.sonrisaplantjv.domain.dto.Review;

import androidx.annotation.Nullable;

import com.example.sonrisaplantjv.domain.model.Review.ReviewRating;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class CreateReview
{
    public String Content;
    @Nullable
    public List<File> Images;
    public ReviewRating ReviewRating;
    public UUID IdPlant;
}
