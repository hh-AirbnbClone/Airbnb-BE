package com.airbnb.hhairbnbclone.mainRooms.dto;

import com.airbnb.hhairbnbclone.entity.Room;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class MainRoomsResponseDto {
    private final Long id;
    private final String address;
    private final List<String> imageList;
//    private Date checkInDate;
//    private Date checkOutDate;
    private final Long price;
    private final int maxGuest;
    public MainRoomsResponseDto(Room room) {
        this.id = room.getId();
        this.address = room.getAddress();
        this.imageList = room.getImageList();
//        this.checkInDate = checkInDate;
//        this.checkOutDate = checkOutDate;
        this.price = room.getPrice();
        this.maxGuest = room.getMaxGuest();
    }
}
