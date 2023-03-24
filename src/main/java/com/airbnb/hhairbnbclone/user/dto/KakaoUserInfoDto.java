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

//https://kauth.kakao.com/oauth/authorize?client_id=658cf70d3e0e9690b7343f3d1f06ff3a&redirect_uri=http://localhost:8080/auth/login&response_type=code
