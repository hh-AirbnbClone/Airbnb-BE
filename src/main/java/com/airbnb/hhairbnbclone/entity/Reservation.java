package com.airbnb.hhairbnbclone.entity;


import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.reservation.dto.ReservationRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate checkin;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate checkout;

    @Column(nullable = false)
    private int guestNum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Reservation(ReservationRequestDto requestDto, User user, Room room) {
        this.checkin = requestDto.getCheckInDate();
        this.checkout = requestDto.getCheckOutDate();
        this.guestNum = requestDto.getGuestNum();
        this.user = user;
        this.room = room;
    }

    //날짜 예약 가능하면 true, 예약 불가능한 날짜면 false
    public boolean checkReservationDate(LocalDate checkin, LocalDate checkout) {
        return !this.checkin.isBefore(checkout) || !this.checkout.isAfter(checkin);
    }

//    @Column(nullable = false)
//    private Date checkIn;
//    @Column(nullable = false)
//    private Date checkOut;
//
//    @Column(nullable = false)
//    private int guestNum;
//
//    @Column(nullable = false)
//    private int betweenDate;
//
//    @JoinColumn(name = "USER_ID",nullable = false)
//    @ManyToOne
//    private User user;
//
//    @JoinColumn(name = "ROOM_ID",nullable = false)
//    @ManyToOne
//    private Room room;
//
//    public Reservation(ReservateRequestDto reservateRequestDto, Room room, User user, int betweenDate) {
//        this.checkIn = reservateRequestDto.getCheckIn();
//        this.checkOut = reservateRequestDto.getCheckOut();
//        this.guestNum = reservateRequestDto.getGuestNum();
//        this.betweenDate = getBetweenDate();
//        this.user = user;
//        this.room = room;
//    }
}
