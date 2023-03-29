package com.airbnb.hhairbnbclone.room.dto;

import com.airbnb.hhairbnbclone.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class MainRoomsResponseDto {
    private Long id;
    private String address;
    private List<String> imageList;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int price;
    private int maxGuest;
    // Done : 즐겨찾기 되있는지 확인하고 true or false 반환
    private boolean bookmark;

    public MainRoomsResponseDto(Room room, LocalDate checkInDate, LocalDate checkOutDate, boolean bookmark) {
        this.id = room.getId();
        this.address = room.getAddress();
        this.imageList = room.getImageList();
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.price = room.getPrice();
        this.maxGuest = room.getMaxGuest();
        this.bookmark = bookmark;
    }
}
