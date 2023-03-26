package com.airbnb.hhairbnbclone.reservation;

import com.airbnb.hhairbnbclone.entity.User;
import com.airbnb.hhairbnbclone.reservation.dto.ReservationRequestDto;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    public String reserve(Long id, ReservationRequestDto requestDto, User user) {




        return "숙소예약 성공";
    }
}
