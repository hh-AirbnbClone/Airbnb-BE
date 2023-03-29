package com.airbnb.hhairbnbclone.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    DUPLICATE_RESERVATION_DATE(BAD_REQUEST, "예약하실 수 없는 날짜입니다."),
    OVER_GUEST_COUNT(BAD_REQUEST, "수용 가능 인원보다 많습니다."),
    INVALID_RESERVATION_DATE(BAD_REQUEST, "체크아웃 날짜는 체크인 날짜 이후로 입력해 주세요."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    ROOM_NOT_FOUND(NOT_FOUND, "선택한 숙소를 찾을 수 없습니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
