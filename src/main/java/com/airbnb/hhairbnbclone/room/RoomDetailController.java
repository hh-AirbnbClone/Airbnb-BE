package com.airbnb.hhairbnbclone.room;

import com.airbnb.hhairbnbclone.exception.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms/details")
@RequiredArgsConstructor
public class RoomDetailController {

    private final DetailRoomService detailRoomService;

    @GetMapping("/{Id}")
    public ResponseEntity getDetailRoom(@PathVariable Long Id){
        return ResponseMessage.SuccessResponse("숙소 상세조회 성공", detailRoomService.getDetailRoom(Id));
    }

}
