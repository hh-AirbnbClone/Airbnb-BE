package com.airbnb.hhairbnbclone.roomDetail.dto;

import com.airbnb.hhairbnbclone.entity.Comment;
import com.airbnb.hhairbnbclone.entity.Reservation;
import com.airbnb.hhairbnbclone.entity.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class RoomDetailListResponseDto {
    private Long RoomId;
    private String title;
    private String description;
    private int price;
    private String address;
    private List<String> imageList;
    List<Comment> commentList;

    public RoomDetailListResponseDto(Room room, List<Comment> commentResponseDtoList) {
        this.RoomId = room.getId();
        this.title = room.getTitle();
        this.description = room.getDescription();
        this.price = room.getPrice();
        this.address = room.getAddress();
        this.imageList = room.getImageList();
        this.commentList = commentResponseDtoList;
        }
    }

