package lshh.securityexample.domain.component;

import lshh.securityexample.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByLoginId(String loginId);
    User save(User user);
}
