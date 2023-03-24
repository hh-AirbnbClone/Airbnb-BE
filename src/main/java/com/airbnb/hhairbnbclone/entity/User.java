package com.airbnb.hhairbnbclone.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long kakaoId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String profile;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(Long kakaoId, String nickname, String email, String profile, UserRoleEnum role) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.email = email;
        this.profile = profile;
        this.role = role;
    }
}
