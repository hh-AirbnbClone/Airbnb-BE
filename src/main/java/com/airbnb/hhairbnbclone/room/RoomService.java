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

    public List<Room> getReservableRooms(List<Long> roomList) {
        // System.out.println(roomRepository.findAllByIdNotIn(roomList).stream().map(Room::getId).toList().toString());
        return roomRepository.findAllByIdNotIn(roomList);
    }
}
