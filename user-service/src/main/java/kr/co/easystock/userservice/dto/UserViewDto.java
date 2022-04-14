package kr.co.easystock.userservice.dto;

import kr.co.easystock.userservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-04-15
 * Time: 오전 2:40
 */

@Getter
public class UserViewDto
{
    private String userId;
    private String userPwd;
    private String userBusinessNo;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userPhoneNo;

    public UserViewDto(User user)
    {
        this.userId = user.getId();
        this.userPwd = user.getPassword();
        this.userBusinessNo = user.getBusinessNo();
        this.userName = user.getName();
        this.userEmail = user.getEmail();
        this.userAddress = user.getAddress();
        this.userPhoneNo = user.getPhone();
    }
}
