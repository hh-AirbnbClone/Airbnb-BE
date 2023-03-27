package com.airbnb.hhairbnbclone.reservation;

import com.airbnb.hhairbnbclone.entity.Reservation;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.entity.User;
import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.repository.ReservationRepository;
import com.airbnb.hhairbnbclone.reservation.dto.ReservationRequestDto;
import com.airbnb.hhairbnbclone.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final RoomService roomService;
    private final ReservationRepository reservationRepository;

    @Transactional
    public String reserve(Long id, ReservationRequestDto requestDto, User user) {
//        roomService.getReservableRooms(getNotReservableRoom(requestDto.getCheckInDate(), requestDto.getCheckOutDate()));
        //숙소가 있는지 체크, 있으면 가져 오고 없으면 예외 처리
        Room room = roomService.getRoom(id);

        //예약하려는 인원이 숙소 수용 가능 인원보다 많으면 예외처리
        if (requestDto.getGuestNum() > room.getMaxGuest()) {
            throw new CustomException(CustomErrorCode.OVER_GUEST_COUNT);
        }

        //예약하려는 날짜가 다른 예약과 겹치지 않는지 체크
        List<Reservation> reservationList = reservationRepository.findAllByRoom(room);

        if (reservationList.size() > 0) {
            for (Reservation reservation : reservationList) {
                if (!reservation.checkReservationDate(requestDto.getCheckInDate(), requestDto.getCheckOutDate())) {
                    throw new CustomException(CustomErrorCode.DUPLICATE_RESERVATION_DATE);
                }
            }
        }

        reservationRepository.saveAndFlush(new Reservation(requestDto, user, room));

        return "숙소예약 성공";
    }


    public List<Long> getNotReservableRoom(LocalDate checkIn, LocalDate checkOut) {
        List<Long> roomList = new ArrayList<>();
        List<Reservation> reservationList = reservationRepository.findAll();
        for (Reservation reservation : reservationList) {
            if (!reservation.checkReservationDate(checkIn, checkOut) && !roomList.contains(reservation.getRoom().getId())) {
                roomList.add(reservation.getRoom().getId());
            }
        }
        System.out.println(roomList.toString());
        return roomList;
    }
}
