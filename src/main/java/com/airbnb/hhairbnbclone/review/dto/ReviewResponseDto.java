package com.airbnb.hhairbnbclone.review.dto;

import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private final String nickname;
    private final String profile;
    private final String review;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern="yyyyMMdd")
    private LocalDateTime createdAt;


    public ReviewResponseDto(Review review) {    //create Review
        this.nickname= review.getUser().getNickname();
        this.profile = review.getUser().getProfile();
        this.review = review.getReview();
        this.createdAt = review.getCreatedAt();
    }
}
