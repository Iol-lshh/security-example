package keyhub.securityexample.infrastructure;

import keyhub.securityexample.domain.component.UserRepository;
import keyhub.securityexample.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImplement implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return jpaRepository.findByLoginId(loginId);
    }

    @Override
    public void save(User user) {
        jpaRepository.save(user);
    }
}
