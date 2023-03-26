package com.airbnb.hhairbnbclone.roomDetail;

import com.airbnb.hhairbnbclone.entity.Comment;
import com.airbnb.hhairbnbclone.entity.Reservation;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.entity.User;
import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.mainRooms.dto.MainRoomsResponseDto;
import com.airbnb.hhairbnbclone.reservation.ReservationRepository;
import com.airbnb.hhairbnbclone.roomDetail.dto.CommentRequestDto;
import com.airbnb.hhairbnbclone.roomDetail.dto.CommentResponseDto;
import com.airbnb.hhairbnbclone.roomDetail.dto.ReservateRequestDto;
import com.airbnb.hhairbnbclone.roomDetail.dto.RoomDetailListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class RoomdetailService {
    private final RoomDetailRepository roomDetailRepository;
    private final CommentRepository commentRepository;
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public RoomDetailListResponseDto getDetailRoom(Long roomId){
        Room room = getRoomId(roomId);
        List<Comment> commentList = commentRepository.findByRoom(room);
        if(commentList.size()==0){
            new CustomException(CustomErrorCode.COMMENT_NOT_FOUND);
        }
        return new RoomDetailListResponseDto(room, commentList);
    }

    @Transactional(readOnly = true)
    public CommentResponseDto createComment(Long roomId, CommentRequestDto commentRequestDto, User user){
        Room room = getRoomId(roomId);
        Comment comment = new Comment(commentRequestDto, user, room); //username이랑 comment 있음
        commentRepository.saveAndFlush(comment);
        return new CommentResponseDto(comment);
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
    public Room getRoomId(Long roomId){
        return roomDetailRepository.findById(roomId).orElseThrow(
                () -> new CustomException(CustomErrorCode.ROOM_NOT_FOUND)
        );
    }
}
