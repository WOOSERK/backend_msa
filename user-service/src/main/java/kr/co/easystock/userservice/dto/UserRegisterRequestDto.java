package kr.co.easystock.userservice.dto;

import kr.co.easystock.userservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-04-15
 * Time: 오전 1:54
 */

@Getter
@Builder
@AllArgsConstructor
public class UserRegisterRequestDto
{
    private String userId;
    private String userPwd;
    private String userName;
    private String userBusinessNo;
    private String userEmail;
    private String userAddress;
    private String userPhoneNo;

    public User toEntity() {
        return User.builder()
                .id(userId)
                .name(userName)
                .password(userPwd)
                .businessNo(userBusinessNo)
                .email(userEmail)
                .address(userAddress)
                .phone(userPhoneNo)
                .build();
    }
}
