package keyhub.securityexample.domain;

import keyhub.securityexample.common.lib.UserAuthenticationException;
import keyhub.securityexample.common.lib.UserAuthenticationJwtManager;
import keyhub.securityexample.domain.component.UserAuthenticationValidator;
import keyhub.securityexample.domain.component.UserFactory;
import keyhub.securityexample.domain.component.UserRepository;
import keyhub.securityexample.domain.dto.LoginCommand;
import keyhub.securityexample.domain.dto.LoginResult;
import keyhub.securityexample.domain.dto.SiginUpResult;
import keyhub.securityexample.domain.dto.SignUpCommand;
import keyhub.securityexample.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserAuthenticationService {
    private final UserRepository repository;
    private final UserFactory factory;
    private final UserAuthenticationValidator validator;
    private final UserAuthenticationJwtManager jwtManager;

    public Optional<User> findByLoginId(String loginId) {
        return repository.findByLoginId(loginId);
    }

    public SiginUpResult signup(SignUpCommand command) {
        repository.findByLoginId(command.loginId())
                .ifPresent( user -> {throw new UserAuthenticationException("User already exists");});
        User user = factory.create(command);
        repository.save(user);
        return new SiginUpResult();
    }

    public LoginResult authenticate(LoginCommand command) {
        User user = repository.findByLoginId(command.loginId())
                .orElseThrow(() -> new UserAuthenticationException("User not found"));
        validator.validatePasssword(user, command.password());
        String jwtToken = jwtManager.generateToken(user);

        return new LoginResult(
                jwtToken,
                jwtManager.getExpirationTime()
        );
    }
}
