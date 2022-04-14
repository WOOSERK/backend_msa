package kr.co.easystock.userservice.dto;

import kr.co.easystock.userservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-04-15
 * Time: 오전 2:51
 */

@Getter
@Builder
@AllArgsConstructor
public class UserUpdateRequestDto
{
    private String userId;
    private String userPwd;
    private String userBusinessNo;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userPhoneNo;

    public User toEntity()
    {
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
