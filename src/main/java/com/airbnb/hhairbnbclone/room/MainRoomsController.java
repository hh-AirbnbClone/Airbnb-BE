package com.airbnb.hhairbnbclone.room;

import com.airbnb.hhairbnbclone.exception.ResponseMessage;
import com.airbnb.hhairbnbclone.room.dto.MainRoomsResponseDto;
import com.airbnb.hhairbnbclone.room.dto.RoomRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainRoomsController {

    private final RoomService roomService;

    // 더미 데이터 넣어서 실험
    @PostMapping("/rooms")
    public ResponseEntity createRoom(@RequestBody RoomRequestDto requestDto){
        roomService.createRoom(requestDto);
        return ResponseMessage.SuccessResponse("room 데이터 삽입 성공", "");
    }


    @GetMapping("/rooms")
    public List<MainRoomsResponseDto> getRooms(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Date checkInDate,
            @RequestParam(required = false) Date checkOutDate,
            @RequestParam(required = false) Integer guestNum
    ){
        return roomService.getMainRooms(address, checkInDate, checkOutDate, guestNum);
    }

}