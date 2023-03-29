package com.airbnb.hhairbnbclone.review;

import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.entity.User;
import com.airbnb.hhairbnbclone.repository.ReviewRepository;
import com.airbnb.hhairbnbclone.review.dto.ReviewRequestDto;
import com.airbnb.hhairbnbclone.review.dto.ReviewResponseDto;
import com.airbnb.hhairbnbclone.room.DetailRoomService;
import com.airbnb.hhairbnbclone.room.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final DetailRoomService detailRoomService;

    @Transactional
    public ReviewResponseDto createReview(Long roomId, ReviewRequestDto reviewRequestDto, User user){
        Room room = detailRoomService.getRoom(roomId);
        Review review = new Review(reviewRequestDto, user, room); //username이랑 comment 있음
        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }

    public List<ReviewResponseDto> getReviewResponseDtoList(Room room){
        List<Review> reviewList = reviewRepository.findByRoom(room);
        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();

        for (Review review : reviewList) {      //reviewList의 배열 수 만큼 반복!           Review가
            reviewResponseDtoList.add(new ReviewResponseDto(review));
        }
        return reviewResponseDtoList;
    }




}
