package com.airbnb.hhairbnbclone.room;

import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.repository.ReviewRepository;
import com.airbnb.hhairbnbclone.repository.RoomRepository;
import com.airbnb.hhairbnbclone.review.dto.ReviewResponseDto;
import com.airbnb.hhairbnbclone.room.dto.RoomDetailListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailRoomService {
    private final ReviewRepository reviewRepository;
    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)   // 숙소 상세정보 Page
    public RoomDetailListResponseDto getDetailRoom(Long roomId){
        Room room = getRoom(roomId);
        List<Review> reviewList = reviewRepository.findByRoom(room);
        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();

        for (Review review : reviewList) {      //reviewList의 배열 수 만큼 반복!           Review가
            reviewResponseDtoList.add(new ReviewResponseDto(review));
        }
        return new RoomDetailListResponseDto(room, reviewResponseDtoList, reviewList.size());
    }
 
    // 원하는 숙소 조회해주는 메서드
    public Room getRoom(Long roomId){
        return roomRepository.findById(roomId).orElseThrow(
                () -> new CustomException(CustomErrorCode.ROOM_NOT_FOUND)
        );
    }

}
