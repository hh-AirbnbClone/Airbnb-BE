package com.airbnb.hhairbnbclone.entity;

import com.airbnb.hhairbnbclone.roomDetail.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comment;

    @JoinColumn(name = "USER_ID", nullable = false)
    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;


    public Comment(CommentRequestDto commentRequestDto, User user, Room room) {
        this.comment = commentRequestDto.getComment();
        this.user = user;
        this.room = room;

    }
}
