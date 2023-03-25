package com.airbnb.hhairbnbclone.roomDetail;

import com.airbnb.hhairbnbclone.exception.ResponseMessage;
import com.airbnb.hhairbnbclone.roomDetail.dto.CommentRequestDto;
import com.airbnb.hhairbnbclone.roomDetail.dto.CommentResponseDto;
import com.airbnb.hhairbnbclone.roomDetail.dto.RoomDetailListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms/details")

@RequiredArgsConstructor
public class RoomDetailController {

    private final RoomdetailService roomdetailService;

//    @GetMapping("/{Id}")
//    public RoomDetailListResponseDto getDetailRoom(@PathVariable Long Id)throws Exception{
//        return roomdetailService.getDetailRoom(Id);
//    }

    @GetMapping("/{Id}")
    public ResponseEntity getDetailRoom(@PathVariable Long Id)throws Exception{
        return ResponseMessage.SuccessResponse("숙소 상세조회 성공", roomdetailService.getDetailRoom(Id));
    }


//    @PostMapping("/review/id")
//    public CommentResponseDto createComment(@PathVariable Long roomId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetails userDetails){
//        return roomdetailService.createComment(roomId, userDetails.getUser());
//    }
}
