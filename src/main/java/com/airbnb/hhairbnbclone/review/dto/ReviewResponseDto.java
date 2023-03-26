package com.airbnb.hhairbnbclone.review.dto;

import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private User user;
    private String review;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ReviewResponseDto(Review review) {
        this.user= review.getUser();
        this.review = review.getReview();
        this.createdAt = review.getCreatedAt();
        this.modifiedAt = review.getModifiedAt();
    }
}
