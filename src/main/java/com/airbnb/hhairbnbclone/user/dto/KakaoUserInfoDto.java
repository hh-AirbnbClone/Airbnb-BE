package com.airbnb.hhairbnbclone.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private Long id;
    private String email;
    private String nickname;
    private String profile;

    public KakaoUserInfoDto(Long id, String nickname, String email, String profile) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.profile = profile;
    }
}
