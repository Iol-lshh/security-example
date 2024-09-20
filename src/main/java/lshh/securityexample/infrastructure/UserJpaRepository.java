package lshh.securityexample.infrastructure;

import lshh.securityexample.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
}
