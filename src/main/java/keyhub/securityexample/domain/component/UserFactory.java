package keyhub.securityexample.domain.component;

import keyhub.securityexample.domain.dto.SignUpCommand;
import keyhub.securityexample.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFactory {
    private final PasswordEncoder passwordEncoder;

    public User create(SignUpCommand command) {
        String password = passwordEncoder.encode(command.password());
        return User.builder()
                .loginId(command.loginId())
                .password(password)
                .build();
    }
}
