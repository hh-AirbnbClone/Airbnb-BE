package com.airbnb.hhairbnbclone.room.dto;

import com.airbnb.hhairbnbclone.entity.Review;
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
    private int maxGuest;
    private String host;
    private List<String> reviewList = new ArrayList<>();    // <Review>가 아니라 <String>
    private int reviewCount;



    public RoomDetailListResponseDto(Room room, List<Review> reviewResponseDtoList, int reviewCount) {
        this.RoomId = room.getId();
        this.title = room.getTitle();
        this.description = room.getDescription();
        this.price = room.getPrice();
        this.address = room.getAddress();
        this.imageList = room.getImageList();
        this.maxGuest = room.getMaxGuest();
        this.host = room.getHost();
        for (int i=0; i < reviewResponseDtoList.size(); i++) {   // ************* 복습
            Review review = reviewResponseDtoList.get(i);
            String reviewOne = review.getReview();
            reviewList.add(reviewOne);
        }
        this.reviewCount = reviewCount;
    }
}