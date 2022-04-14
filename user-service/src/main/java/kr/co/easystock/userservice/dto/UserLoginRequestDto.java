package kr.co.easystock.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-04-15
 * Time: 오전 1:58
 */

@Getter
@AllArgsConstructor
public class UserLoginRequestDto
{
    private String userId;
    private String password;
}
