package com.airbnb.hhairbnbclone.room;

import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.repository.ReviewRepository;
import com.airbnb.hhairbnbclone.repository.RoomRepository;
import com.airbnb.hhairbnbclone.room.dto.RoomDetailListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        int reviewCount = reviewList.size();
        return new RoomDetailListResponseDto(room, reviewList, reviewCount);
    }

    // 원하는 숙소 조회해주는 메서드
    public Room getRoom(Long roomId){
        return roomRepository.findById(roomId).orElseThrow(
                () -> new CustomException(CustomErrorCode.ROOM_NOT_FOUND)
        );
    }

}
