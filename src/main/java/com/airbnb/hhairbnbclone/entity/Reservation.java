package com.airbnb.hhairbnbclone.entity;

import com.airbnb.hhairbnbclone.roomDetail.dto.ReservateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date checkIn;
    @Column(nullable = false)
    private Date checkOut;

    @Column(nullable = false)
    private int guestNum;

    @Column(nullable = false)
    private int betweenDate;

    @JoinColumn(name = "USER_ID",nullable = false)
    @ManyToOne
    private User user;

    @JoinColumn(name = "ROOM_ID",nullable = false)
    @ManyToOne
    private Room room;

    public Reservation(ReservateRequestDto reservateRequestDto, Room room, User user, int betweenDate) {
        this.checkIn = reservateRequestDto.getCheckIn();
        this.checkOut = reservateRequestDto.getCheckOut();
        this.guestNum = reservateRequestDto.getGuestNum();
        this.betweenDate = getBetweenDate();
        this.user = user;
        this.room = room;
    }
}
