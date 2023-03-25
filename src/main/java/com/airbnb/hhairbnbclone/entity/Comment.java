package com.airbnb.hhairbnbclone.entity;

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
    User user;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID", nullable = false)
    Room room;
}
