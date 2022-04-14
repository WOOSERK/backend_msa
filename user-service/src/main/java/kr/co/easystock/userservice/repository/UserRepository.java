package kr.co.easystock.userservice.repository;

import kr.co.easystock.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-04-15
 * Time: 오전 1:51
 */

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    Optional<User> findByName(String name);
    Optional<User> findByIdAndDeletedDateIsNull(String id);
}
