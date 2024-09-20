package lshh.securityexample.infrastructure;

import lshh.securityexample.domain.component.UserRepository;
import lshh.securityexample.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImplement implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return jpaRepository.findByLoginId(loginId);
    }

    @Override
    public User save(User user) {
        return jpaRepository.save(user);
    }
}
