package com.airbnb.hhairbnbclone.reservation;

import com.airbnb.hhairbnbclone.exception.CustomErrorCode;
import com.airbnb.hhairbnbclone.exception.CustomException;
import com.airbnb.hhairbnbclone.exception.ResponseMessage;
import com.airbnb.hhairbnbclone.reservation.dto.ReservationRequestDto;
import com.airbnb.hhairbnbclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms/details")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservation/{id}")
    public ResponseEntity reserve(@PathVariable Long id, @RequestBody ReservationRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (!(requestDto.getCheckInDate().isBefore(requestDto.getCheckOutDate()))) {
            throw new CustomException(CustomErrorCode.INVALID_RESERVATION_DATE);
        }
        return ResponseMessage.SuccessResponse(reservationService.reserve(id, requestDto, userDetails.getUser()), "");
    }
}
