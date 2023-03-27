package com.airbnb.hhairbnbclone.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Bookmark(Long roomId, User user) {
        this.roomId = roomId;
        this.user = user;
    }
}
