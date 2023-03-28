package com.airbnb.hhairbnbclone.room.dto;//https://velog.io/@tonyk0901/TIL31-React-react-dates-%EC%BB%A4%EC%8A%A4%ED%85%80-%ED%95%package com.airbnb.hhairbnbclone.room.dto;

import com.airbnb.hhairbnbclone.entity.Review;
import com.airbnb.hhairbnbclone.entity.Room;
import com.airbnb.hhairbnbclone.repository.RoomRepository;
import com.airbnb.hhairbnbclone.review.dto.ReviewRequestDto;
import com.airbnb.hhairbnbclone.review.dto.ReviewResponseDto;
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
    private List<ReviewResponseDto> reviewList;    // <Review>가 아니라 <String>
    private int reviewCount;





    public RoomDetailListResponseDto(Room room, List<ReviewResponseDto> reviewResponseDtoList, int reviewCount) {
        this.RoomId = room.getId();
        this.title = room.getTitle();
        this.description = room.getDescription();
        this.price = room.getPrice();
        this.address = room.getAddress();
        this.imageList = room.getImageList();
        this.maxGuest = room.getMaxGuest();
        this.host = room.getHost();
        this.reviewCount = reviewCount;
        this.reviewList = reviewResponseDtoList;
    }

}







//            ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
//            new ReviewResponseDto(review.getReview(), review.getUser(),review.getCreatedAt());
//            ReviewResponseDto reviewOne = review.getReview();98%EA%B8%B0