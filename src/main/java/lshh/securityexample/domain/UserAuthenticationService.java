package lshh.securityexample.domain;

import lshh.securityexample.common.dto.LoginCommand;
import lshh.securityexample.common.dto.LoginResult;
import lshh.securityexample.common.dto.SignupCommand;
import lshh.securityexample.common.dto.SignupResult;
import lshh.securityexample.common.exception.UserAuthenticationException;
import lshh.securityexample.domain.component.UserAuthenticationJwtManager;
import lshh.securityexample.domain.component.UserFactory;
import lshh.securityexample.domain.component.UserRepository;
import lshh.securityexample.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserAuthenticationService {
    private final UserAuthenticationJwtManager jwtManager;
    private final UserRepository repository;
    private final UserFactory factory;

    public SignupResult signup(SignupCommand command) {
        repository.findByLoginId(command.loginId())
                .ifPresent(user -> {
                    throw new UserAuthenticationException("이미 존재하는 아이디");
                });
        User user = factory.create(command);
        Long id = repository.save(user).getId();
        return new SignupResult(id);
    }

    public LoginResult login(LoginCommand command) {
        User user = repository.findByLoginId(command.loginId())
                .orElseThrow(() -> new UserAuthenticationException("존재하지 않는 아이디"));
        if (!user.matchPassword(command.password())) {
            throw new UserAuthenticationException("비밀번호가 일치하지 않습니다");
        }
        String jwt = jwtManager.generateToken(user);
        return new LoginResult(jwt);
    }

    public String extractUsername(String jwt) {
        return jwtManager.extractUsername(jwt);
    }

    public UserDetails loadUserByUsername(String username) {
        return repository.findByLoginId(username)
                .orElseThrow(() -> new UserAuthenticationException("존재하지 않는 아이디"));
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
                && !jwtManager.isTokenExpired(token);
    }

    public Optional<User> findByLoginId(String loginId) {
        return repository.findByLoginId(loginId);
    }
}
