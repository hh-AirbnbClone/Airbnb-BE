package com.airbnb.hhairbnbclone.review.dto;

import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private User user;
    private String review;
    private String createdAt;


    public ReviewResponseDto(Review review) {    //create Review
        this.user= review.getUser();
        this.review = review.getReview();
        this.createdAt = createdAt.toString().substring(0,10);
    }
}
