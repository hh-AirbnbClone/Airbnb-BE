package com.airbnb.hhairbnbclone.room;

import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.repository.RoomRepository;
import com.airbnb.hhairbnbclone.review.ReviewService;
import com.airbnb.hhairbnbclone.review.dto.ReviewResponseDto;
import com.airbnb.hhairbnbclone.room.dto.RoomDetailListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailRoomService {
    private final ReviewService reviewService;
    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)   // 숙소 상세정보 Page
    public RoomDetailListResponseDto getDetailRoom(Long roomId){ // 숙소 상세정보 Page
        Room room = getRoom(roomId);
        List<ReviewResponseDto> reviewResponseDtoList = reviewService.getReviewResponseDtoList(room);
        return new RoomDetailListResponseDto(room, reviewResponseDtoList, reviewResponseDtoList.size());
    }
 
    // 원하는 숙소 조회해주는 메서드
    @Transactional(readOnly = true)
    public Room getRoom(Long roomId){
        return roomRepository.findById(roomId).orElseThrow(
                () -> new CustomException(CustomErrorCode.ROOM_NOT_FOUND)
        );
    }

}
