package com.airbnb.hhairbnbclone.user;

import com.airbnb.hhairbnbclone.exception.ResponseMessage;
import com.airbnb.hhairbnbclone.jwt.JwtUtil;
import com.airbnb.hhairbnbclone.user.dto.LoginResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping("/login")
    public ResponseEntity kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        Map<String, String> kakaoUser = userService.kakaoLogin(code, response);
        String createToken =  jwtUtil.createToken(kakaoUser.get("kakaoId"), kakaoUser.get("nickname"), kakaoUser.get("profile"));

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, createToken);

        return ResponseMessage.SuccessResponse("로그인 성공!", new LoginResponseDto(kakaoUser.get("profile")));

    }
}
