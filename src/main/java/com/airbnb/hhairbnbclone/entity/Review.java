package com.airbnb.hhairbnbclone.entity;

import com.airbnb.hhairbnbclone.review.dto.ReviewRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Review extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String review;

    @JoinColumn(name = "USER_ID", nullable = false)
    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;


    public Review(ReviewRequestDto reviewRequestDto, User user, Room room) {
        this.review = reviewRequestDto.getReview();
        this.user = user;
        this.room = room;

    }
}
