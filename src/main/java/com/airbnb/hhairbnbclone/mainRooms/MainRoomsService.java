package com.airbnb.hhairbnbclone.mainRooms;

import com.airbnb.hhairbnbclone.entity.Reservation;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.mainRooms.dto.MainRoomsResponseDto;
//import com.airbnb.hhairbnbclone.mainRooms.dto.RoomRequestDto;
import com.airbnb.hhairbnbclone.mainRooms.dto.RoomRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainRoomsService {
    private final MainRoomsRepository mainListRepository;

    // 더미 데이터 삽입
    @Transactional
    public void createRoom(RoomRequestDto requestDto) {
        mainListRepository.saveAndFlush(new Room(requestDto));
    }

    @Transactional
    public List<MainRoomsResponseDto> getMainRooms(
            String address,
            Date checkInDate,
            Date checkOutDate,
            Integer guestNum
    ){
        /* XXX; 모든 데이터 받아온 후 검색 결과에 따라 if문으로 필터링을 진행해 주었는데
            서비스가 커지고, 데이터가 많아지면 성능 저하가 이뤄지기 쉽다
            repository에서 처리하자니 받아오는 검색값이 null일 수도 있어서 동적 쿼리를 작성해줘야함
            QueryDSL, Specifications API, Criteria API 중 하나 사용해서 구현해보기
         * */
        // TODO : 예약 구현 완료되면 체크인, 체크아웃시간 구현하기
        // 모든 데이터 받아온 후
        List<Room> roomList = mainListRepository.findAll();
        List<MainRoomsResponseDto> roomsResponseDtoList = new ArrayList<>();

        // 검색 결과에 따라 필터링
        for(Room room: roomList){
            if (address != null && !room.getAddress().contains(address)) {
                continue;
            }
//            if (checkInDate != null && room.getCheckInDate().after(checkInDate)) {
//                continue;
//            }
//
//            if (checkOutDate != null && room.getCheckOutDate().before(checkOutDate)) {
//                continue;
//            }

            if (guestNum != null && room.getMaxGuest() < guestNum) {
                continue;
            }
            roomsResponseDtoList.add(new MainRoomsResponseDto(room));
        }
        return roomsResponseDtoList;
    }

}
