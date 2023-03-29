package com.airbnb.hhairbnbclone.review;

import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.entity.User;
import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.repository.ReviewRepository;
import com.airbnb.hhairbnbclone.repository.RoomRepository;
import com.airbnb.hhairbnbclone.review.dto.ReviewRequestDto;
import com.airbnb.hhairbnbclone.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;



@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public ReviewResponseDto createReview(Long roomId, ReviewRequestDto reviewRequestDto, User user){
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new CustomException(CustomErrorCode.ROOM_NOT_FOUND));
        Review review = new Review(reviewRequestDto, user, room); //username이랑 comment 있음
        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }

    @Transactional
    public List<ReviewResponseDto> getReviewResponseDtoList(Room room){
        return reviewRepository.findByRoom(room).stream().map(ReviewResponseDto::new).toList();//
    }

}
