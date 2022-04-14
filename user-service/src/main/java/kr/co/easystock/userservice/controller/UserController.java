package kr.co.easystock.userservice.controller;

import kr.co.easystock.userservice.domain.User;
import kr.co.easystock.userservice.dto.UserLoginRequestDto;
import kr.co.easystock.userservice.dto.UserRegisterRequestDto;
import kr.co.easystock.userservice.dto.UserUpdateRequestDto;
import kr.co.easystock.userservice.dto.UserViewDto;
import kr.co.easystock.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-04-15
 * Time: 오전 1:51
 */

@RequiredArgsConstructor
@RestController
public class UserController
{
    private final UserService userService;

    /**
     * 회원가입
     * @param requestDto
     * @return id
     */
    // 암호화해서 저장해야 함
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDto requestDto)
    {
        User user = userService.register(requestDto);
        if(user == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
    }

    /**
     * 로그인
     * @param requestDto
     * @return boolean
     */
    // 세션 or 토큰 방식으로 설정해야 함
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserLoginRequestDto requestDto)
    {
        User user = userService.login(requestDto);
        if(user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    /**
     * 내 정보 조회
     * @param param
     * @return UserViewDto
     */
    @PostMapping("/myinfo")
    public ResponseEntity<UserViewDto> getMyInfo(@RequestBody Map<String, String> param)
    {
        User user = userService.getMyInfo(param.get("userId"));
        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        UserViewDto userViewDto = new UserViewDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(userViewDto);
    }

    /**
     * 내 정보 변경
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/myinfo/change")
    public ResponseEntity<Boolean> changeMyInfo(@RequestBody UserUpdateRequestDto requestDto)
    {
        boolean result = userService.changeMyInfo(requestDto);
        if(result)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    /**
     * 비밀번호 변경
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/myinfo/changepwd")
    public ResponseEntity<Boolean> changeMyPwd(@RequestBody UserLoginRequestDto requestDto)
    {
        boolean result = userService.changeMyPwd(requestDto);
        if(result)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    /**
     * 회원 탈퇴
     * @param param
     * @return boolean
     */
    @PutMapping("/withdraw")
    public ResponseEntity<Boolean> withdraw(@RequestBody Map<String, String> param)
    {
        String userId = param.get("userId");
        boolean result = userService.withdraw(userId);
        if(result)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}
