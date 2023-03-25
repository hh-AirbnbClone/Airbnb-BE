package com.airbnb.hhairbnbclone.entity;

//import com.airbnb.hhairbnbclone.mainRooms.dto.RoomRequestDto;
import com.airbnb.hhairbnbclone.mainRooms.dto.RoomRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String address;

    @ElementCollection
    @Column(nullable = false)
    private List<String> imageList;

    @Column(nullable = false)
    private int maxGuest;

    @Column(nullable = false)
    private String host;

    // 더미데이터 삽입
    public Room(RoomRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.price = requestDto.getPrice();
        this.address = requestDto.getAddress();
        this.imageList = requestDto.getImageList();
        this.maxGuest = requestDto.getMaxGuest();
        this.host = requestDto.getHost();
    }
}
