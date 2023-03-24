package com.airbnb.hhairbnbclone.roomDetail.dto;

import com.airbnb.hhairbnbclone.entity.Reservation;
import com.airbnb.hhairbnbclone.entity.Room;
import lombok.Getter;

import java.util.List;

@Getter
public class RoomDetailListResponseDto {
    private Long RoomId;
    private String title;
    private String description;
    private int price;
    private String address;
    private String image;

    public RoomDetailListResponseDto(Room room, List<Reservation> reservation) {
        RoomId = getRoomId();
        this.title = getTitle();
        this.description = getDescription();
        this.price = getPrice();
        this.address = getAddress();
        this.image = getImage();
    }
}
