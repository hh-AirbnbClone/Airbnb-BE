package com.airbnb.hhairbnbclone.mainRooms.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomRequestDto {
    private String title;
    private String description;
    private int price;
    private String address;
    private List<String> imageList;
    private int maxGuest;
    private String host;
}
