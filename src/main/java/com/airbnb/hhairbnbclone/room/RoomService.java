package com.airbnb.hhairbnbclone.room;

import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.repository.ReviewRepository;
import com.airbnb.hhairbnbclone.repository.ReservationRepository;
import com.airbnb.hhairbnbclone.repository.RoomRepository;
import com.airbnb.hhairbnbclone.room.dto.MainRoomsResponseDto;
import com.airbnb.hhairbnbclone.room.dto.RoomDetailListResponseDto;
import com.airbnb.hhairbnbclone.room.dto.RoomRequestDto;
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
public class RoomService {
    private final RoomRepository roomRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;


    @Transactional
    public void createRoom(RoomRequestDto requestDto) {
        roomRepository.saveAndFlush(new Room(requestDto));
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
        List<Room> roomList = roomRepository.findAll();
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


    @Transactional(readOnly = true)
    public RoomDetailListResponseDto getDetailRoom(Long roomId){
        Room room = getRoom(roomId);
        List<Review> commentList = reviewRepository.findByRoom(room);
        if(commentList.size()==0){
            new CustomException(CustomErrorCode.COMMENT_NOT_FOUND);
        }
        return new RoomDetailListResponseDto(room, commentList);
    }




//    @Transactional
//    public String reservateRoom(Long roomId, ReservateRequestDto requestDto, User user){
//
//        //어느 숙소에 예약을 할지 조회
//        Room room =getRoomId(roomId);
//        Reservation reservation = new Reservation(requestDto,room,user);
//        // 해당 예약 기간에 이미 다른 예약이 있는지 검사
//        List<Reservation> ReservationList = reservationRepository.findAllByCheckInBetweenOrCheckOutBetween
//                (requestDto.getCheckIn(), requestDto.getCheckOut());
//        if (!ReservationList.isEmpty()) {
//            new CustomException(CustomErrorCode.CAN_NOT_RESERVATE_ROOM);
//        }
//        if(requestDto.getGuestNum()>room.getMaxGuest()){
//            new CustomException(CustomErrorCode.OVER_GUEST_COUNT);
//        }
//
//        // 예약이 가능한 경우 save
//        reservationRepository.save(reservation);
//
//        return "d";
//    }

//    @Transactional
//    public Reservation reservateRoom(Long roomId, ReservateRequestDto requestDto, User user){
//        Room room = getRoomId(roomId); //원하는 숙소 찾았고
//
//        int oneDayMs = 1000 * 60 * 60 * 24; // 1일의 밀리초 수(getTime()을 다시 날짜로 바꿔주기위해서 만들었음!)
//        Long checkInDate = requestDto.getCheckIn().getTime();
//        Long checkOutDate = requestDto.getCheckOut().getTime();
//        Long betweenDateMs = checkOutDate - checkInDate;
//        int betweenDate = Math.round(betweenDateMs / oneDayMs);    //두 날짜 사이의 일수를 구했음
//
//        for(long i = checkInDate; i < betweenDate; i++ ){
//
//        }
//
//        if(!(requestDto==null)){
//            Optional<Reservation> reservationList = reservationRepository.findById(roomId); // 숙소에 어느 날짜로 예약들이 되어있는지 조회
//            if(reservationList.isEmpty()){
//                new CustomException(CustomErrorCode.RESERVATION_NOT_FOUND);
//            }
//            requestDto.
//
//            }
//        }
//
//        Reservation reservation = new Reservation(requestDto, room, user, betweenDate); // 사용자의 예약 정보(어느 숙소, 날짜, 내가 누군지)
//
//        return reservation;
//    }





    // 원하는 숙소 조회해주는 메서드
    public Room getRoom(Long roomId){
        return roomRepository.findById(roomId).orElseThrow(
                () -> new CustomException(CustomErrorCode.ROOM_NOT_FOUND)
        );
    }
}
