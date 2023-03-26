package com.airbnb.hhairbnbclone.room.dto;

import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ReservateRequestDto {


    private Date checkIn;
    private Date checkOut;
    public int guestNum;

    public ReservateRequestDto() {
        this.checkIn = getCheckIn();
        this.checkOut = getCheckOut();
        this.guestNum = getGuestNum();
    }
//    List<User> findByCreatedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
