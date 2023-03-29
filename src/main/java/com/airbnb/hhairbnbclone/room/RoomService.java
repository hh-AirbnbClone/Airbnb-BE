package com.airbnb.hhairbnbclone.room;

import com.airbnb.hhairbnbclone.entity.Bookmark;
import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.entity.User;
import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.repository.BookmarkRepository;
import com.airbnb.hhairbnbclone.repository.ReviewRepository;
import com.airbnb.hhairbnbclone.repository.ReservationRepository;
import com.airbnb.hhairbnbclone.repository.RoomRepository;
import com.airbnb.hhairbnbclone.reservation.ReservationService;
import com.airbnb.hhairbnbclone.review.dto.ReviewResponseDto;
import com.airbnb.hhairbnbclone.room.dto.MainRoomsResponseDto;
import com.airbnb.hhairbnbclone.room.dto.RoomDetailListResponseDto;
import com.airbnb.hhairbnbclone.room.dto.RoomRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final ReservationService reservationService;
    private final ReviewRepository reviewRepository;



    @Transactional
    public void createRoom(RoomRequestDto requestDto) {
        roomRepository.saveAndFlush(new Room(requestDto));
}

    @Transactional
    public List<MainRoomsResponseDto> getMainRooms(
            String address,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            Integer guestNum
    ){
        List<MainRoomsResponseDto> roomsResponseDtoList = new ArrayList<>();

        // param 값이 들어 왔을 경우
        // 검색 조회 - 해당 파람값에 맞춰서 출력
        if (address != null && checkInDate != null && checkOutDate != null && guestNum != null){
            List<Room> reservableRooms = getReservableRooms(reservationService.getNotReservableRoom(checkInDate, checkOutDate));
            // 검색 결과에 따라 필터링
            for(Room room: reservableRooms){
                if (!room.getAddress().contains(address)) {
                    continue;
                }
                if (room.getMaxGuest() < guestNum) {
                    continue;
                }
                roomsResponseDtoList.add(new MainRoomsResponseDto(room, checkInDate, checkOutDate));
            }
        } else{
            // param 값이 들어 오지 않았을 경우
            // 메인 조회 - 어디든지/ 언제든 일주일(숙소가 가능한 가장 빠른 일주일) 조회
            List<Room> roomList = roomRepository.findAll();
            for(Room room: roomList){
                LocalDate earliestDate = reservationService.getEarliestAvailableDate(room);
                LocalDate oneWeekLater = earliestDate.plusWeeks(1);
                roomsResponseDtoList.add(new MainRoomsResponseDto(room, earliestDate, oneWeekLater));
            }
        }
        return roomsResponseDtoList;
    }



//    @Transactional(readOnly = true)   // 숙소 상세정보 Page
//    public RoomDetailListResponseDto getDetailRoom(Long roomId){
//        Room room = getRoom(roomId);
//        List<Review> reviewList = reviewRepository.findByRoom(room);
//        int reviewCount = reviewList.size();
//        return new RoomDetailListResponseDto(room, reviewList, reviewCount);
//    }


    public RoomDetailListResponseDto getDetailRoom(Long roomId){ // 숙소 상세정보 Page
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

    public List<Room> getReservableRooms(List<Long> roomList) {
        // System.out.println(roomRepository.findAllByIdNotIn(roomList).stream().map(Room::getId).toList().toString());
        return roomRepository.findAllByIdNotIn(roomList);
    }
}
