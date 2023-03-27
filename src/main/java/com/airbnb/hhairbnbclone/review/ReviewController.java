package com.airbnb.hhairbnbclone.review;

import com.airbnb.hhairbnbclone.exception.ResponseMessage;
import com.airbnb.hhairbnbclone.review.dto.ReviewRequestDto;
import com.airbnb.hhairbnbclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms/details")
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("/review/{id}")
    public ResponseEntity createReview(@PathVariable Long id, @RequestBody ReviewRequestDto reviewRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseMessage.SuccessResponse("리뷰 작성 성공", reviewService.createReview(id, reviewRequestDto, userDetails.getUser()));
    }
}
