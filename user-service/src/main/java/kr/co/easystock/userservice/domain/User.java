package kr.co.easystock.userservice.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-04-15
 * Time: 오전 1:51
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseTimeEntity
{
    @Id
    private String id;

    private String password;

    @Column(length = 10)
    private String businessNo;

    private String name;

    @Column(unique = true)
    private String email;

    private String address;
    private String phone;

    // id는 변경할 수 없음
    public void update(User user)
    {
        this.password = user.getPassword();
        this.name = user.getName();
        this.businessNo = user.getBusinessNo();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phone = user.getPhone();
    }

    // 비밀번호 변경 전용 메서드
    public void updatePassword(String password)
    {
        this.password = password;
    }

    // soft delete
    @Override
    public void delete()
    {
        super.delete();
    }
}
