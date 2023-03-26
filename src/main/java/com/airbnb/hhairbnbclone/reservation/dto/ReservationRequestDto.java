package com.airbnb.hhairbnbclone.reservation.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReservationRequestDto {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int guestNum;
}
