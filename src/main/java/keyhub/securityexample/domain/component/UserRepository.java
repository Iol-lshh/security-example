package keyhub.securityexample.domain.component;

import keyhub.securityexample.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByLoginId(String loginId);

    void save(User user);
}
