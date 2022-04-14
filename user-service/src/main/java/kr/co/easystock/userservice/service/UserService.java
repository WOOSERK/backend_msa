package kr.co.easystock.userservice.service;

import kr.co.easystock.userservice.domain.User;
import kr.co.easystock.userservice.dto.UserLoginRequestDto;
import kr.co.easystock.userservice.dto.UserRegisterRequestDto;
import kr.co.easystock.userservice.dto.UserUpdateRequestDto;
import kr.co.easystock.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-04-15
 * Time: 오전 1:50
 */

@RequiredArgsConstructor
@Transactional
@Service
public class UserService
{
    private final UserRepository userRepository;

    /**
     * 회원 가입
     * @param requestDto
     * @return User
     */
    public User register(UserRegisterRequestDto requestDto)
    {
        // 유저 생성
        User user = requestDto.toEntity();
        if(validateDuplicateUser(user) == null)
            return null;

        return userRepository.save(user);
    }

    /**
     * 중복 회원 체크
     * @param user
     * @return User
     */
    private User validateDuplicateUser(User user)
    {
        return userRepository.findById(user.getId()).orElse(null);
    }

    /**
     * 로그인
     * @param requestDto
     * @return User
     */
    @Transactional(readOnly = true)
    public User login(UserLoginRequestDto requestDto)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(requestDto.getUserId()).orElse(null);

        return user;
    }

    /**
     * 내 정보 조회
     * @param id
     * @return User
     */
    @Transactional(readOnly = true)
    public User getMyInfo(String id)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(id).get();

        return user;
    }

    /**
     * 내 정보 변경
     * @param requestDto
     * @return boolean
     */
    public boolean changeMyInfo(@RequestBody UserUpdateRequestDto requestDto)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(requestDto.getUserId()).orElse(null);
        // 유저를 찾지 못했으면 false
        if(user == null)
            return false;

        user.update(requestDto.toEntity());
        return true;
    }

    /**
     * 비밀번호 변경
     * @param requestDto
     * @return boolean
     */
    public boolean changeMyPwd(UserLoginRequestDto requestDto)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(requestDto.getUserId()).orElse(null);
        if(user == null)
            return false;

        user.updatePassword(requestDto.getPassword());
        return true;
    }

    /**
     * 회원 탈퇴
     * @param id
     * @return boolean
     */
    public boolean withdraw(String id)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(id).orElse(null);
        if(user == null)
            return false;

        user.delete();
        return true;
    }
}
