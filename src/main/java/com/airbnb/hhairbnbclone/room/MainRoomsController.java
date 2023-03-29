package com.airbnb.hhairbnbclone.room;

import com.airbnb.hhairbnbclone.bookmark.BookmarkService;
import com.airbnb.hhairbnbclone.exception.ResponseMessage;
import com.airbnb.hhairbnbclone.room.dto.MainRoomsResponseDto;
import com.airbnb.hhairbnbclone.room.dto.RoomRequestDto;
import com.airbnb.hhairbnbclone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainRoomsController {

    private final RoomService roomService;
    private final BookmarkService bookmarkService;

    // 더미 데이터 넣어서 실험
    @PostMapping("/rooms")
    public ResponseEntity createRoom(@RequestBody RoomRequestDto requestDto){
        roomService.createRoom(requestDto);
        return ResponseMessage.SuccessResponse("room 데이터 삽입 성공", "");
    }


    // ResponseEntity로 수정
    @GetMapping("/rooms")
    public ResponseEntity getRooms(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOutDate,
            @RequestParam(required = false) Integer guestNum
            ,@AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return ResponseMessage.SuccessResponse("조회 성공", roomService.getMainRooms(address, checkInDate, checkOutDate, guestNum, userDetails));
    }

    @PostMapping("/rooms/bookmark/{roomId}")
    public ResponseEntity bookmark(@PathVariable Long roomId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseMessage.SuccessResponse(bookmarkService.bookmark(roomId, userDetails.getUser()), "");
    }
}