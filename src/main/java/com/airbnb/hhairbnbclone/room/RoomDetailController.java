package com.airbnb.hhairbnbclone.room;

import com.airbnb.hhairbnbclone.exception.ResponseMessage;
import com.airbnb.hhairbnbclone.room.dto.ReservateRequestDto;
import com.airbnb.hhairbnbclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms/details")
@RequiredArgsConstructor
public class RoomDetailController {

    private final RoomService roomService;

//    @GetMapping("/{Id}")
//    public RoomDetailListResponseDto getDetailRoom(@PathVariable Long Id)throws Exception{
//        return roomdetailService.getDetailRoom(Id);
//    }

    @GetMapping("/{Id}")
    public ResponseEntity getDetailRoom(@PathVariable Long Id)throws Exception{
        return ResponseMessage.SuccessResponse("숙소 상세조회 성공", roomService.getDetailRoom(Id));
    }


//    @PostMapping("/reservation/{id}")
//    public ResponseEntity reservateRoom(@PathVariable Long Id, @RequestBody ReservateRequestDto reservateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return ResponseMessage.SuccessResponse("숙소 예약 성공", roomService.reservateRoom(Id,reservateRequestDto, userDetails.getUser()));
//    }
}
