//package com.airbnb.hhairbnbclone.swagger;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.Parameters;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.lang.Nullable;
//import org.springframework.web.bind.annotation.*;
//
//@Tag(name = "Airbnb", description = "API")
//@RestController
//@RequestMapping("/rooms/details")
//@RequiredArgsConstructor
//public class PostsController {
//
//    @Operation(summary = "get roomDetail", description = "메인 페이지에서 특정 숙소를 찾아 들어갔을 때 나타나는 상세정보 페이지이다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PostsResponseDto.class))),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
//            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
//    })
//    @Parameters({
//            @Parameter(name = "title", description = "제목", example = "<소담소담>한옥 민박 힐링 스팟"),
//            @Parameter(name = "description", description = "도", example = "고양시"),
//            @Parameter(name = "price", description = "숙박 비용", example = "150,000"),
//            @Parameter(name = "address", description = "주소", example = "Ongryong-myeon, Gwangyang-si, 전라남도, 한국"),
//            @Parameter(name = "imageList", description = "사진 리스트", example = "['#https://a0.muscache.com/im/pictures/cdcad94d-8d6d-4072-9c03-aeeaf20cd764.jpg?im_w=1200', '#https://a0.muscache.com/im/pictures/888d6fad-010f-41ee-870f-095e8bf3a7ed.jpg?im_w=720']"),
//            @Parameter(name = "maxGuest", description = "최대 수용 인원", example = "5"),
//            @Parameter(name = "host", description = "호스트", example = "산하")
//    })
//
//    @ResponseBody
//    @GetMapping( "")
//    public PostsResponseDto getPosts(
//            @RequestParam(value = "province") String province,
//            @RequestParam(value = "city") String city,
//            @RequestParam(value = "hashtag", required = false) @Nullable String hashtag
//    ) {
//        return new PostsResponseDto(1);
//    }
//}
