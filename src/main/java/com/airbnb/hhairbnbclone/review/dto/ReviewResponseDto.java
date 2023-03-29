package com.airbnb.hhairbnbclone.review.dto;

import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private User user;
    private String review;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern="yyyyMMdd")
    private LocalDateTime createdAt;


    public ReviewResponseDto(Review review) {    //create Review
        this.user= review.getUser();
        this.review = review.getReview();
        this.createdAt = review.getCreatedAt();
    }
}
