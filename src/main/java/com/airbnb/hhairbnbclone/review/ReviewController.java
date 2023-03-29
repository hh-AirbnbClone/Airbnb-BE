package com.airbnb.hhairbnbclone.review;

import com.airbnb.hhairbnbclone.exception.ResponseMessage;
import com.airbnb.hhairbnbclone.review.dto.ReviewRequestDto;
import com.airbnb.hhairbnbclone.security.UserDetailsImpl;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;


//import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

//@Tag(name = "Review", description = "후기 API")
@Tag(name = "Review", description = "후기 관련 API")

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rooms/details")
public class ReviewController {
    private final ReviewService reviewService;



    @Pattern(regexp = "[0-2]")
    @Schema(description = "유형", defaultValue = "0", allowableValues = {"0", "1", "2"})
    private String type;

    @Email
    @Schema(description = "이메일", nullable = false, example = "sanha0630@naver.com")
    private String email;

    @Schema(description = "이름")
    private String name;


        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
                @ApiResponse(responseCode = "204", description = "member not exists"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND"),
                @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
        })

        @Parameters({
                @Parameter(name = "review", description = "후기", example = "별로였어요"),
                @Parameter(name = "city", description = "도", example = "고양시")
        })

//    @Operation(summary = "get reviews", description = "후기 작성에 대한 기능이 있다")
//    // 메서드 정보
//
//    // response 정보
//    @ApiResponses({     //에러나면 여기 import 다시 해보자
//            @ApiResponse(responseCode = "200", description = "success", content=@Content(schema = @Schema(implementation = ResponseEntity.class))),
//            @ApiResponse(responseCode = "204", description = "member not exists"),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    }

    @Operation(summary = "get reviews", description = "후기 작성에 대한 기능이 있다")
    @ResponseBody
    @PostMapping("/review/{id}")
    public ResponseEntity createReview(@PathVariable Long id, @RequestBody ReviewRequestDto reviewRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseMessage.SuccessResponse("리뷰 작성 성공", reviewService.createReview(id, reviewRequestDto, userDetails.getUser()));
    }
}
