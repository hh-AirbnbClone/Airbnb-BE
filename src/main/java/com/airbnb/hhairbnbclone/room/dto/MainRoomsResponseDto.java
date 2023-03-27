package com.airbnb.hhairbnbclone.room.dto;

import com.airbnb.hhairbnbclone.entity.Room;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
public class MainRoomsResponseDto {
    private final Long id;
    private final String address;
    private final List<String> imageList;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final int price;
    private final int maxGuest;
    public MainRoomsResponseDto(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.id = room.getId();
        this.address = room.getAddress();
        this.imageList = room.getImageList();
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.price = room.getPrice();
        this.maxGuest = room.getMaxGuest();
    }
}
