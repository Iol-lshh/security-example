package keyhub.securityexample.domain.component;

import keyhub.securityexample.common.lib.UserAuthenticationException;
import keyhub.securityexample.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserAuthenticationValidator {
    private final PasswordEncoder passwordEncoder;

    public void validatePasssword(User user, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserAuthenticationException("Password is incorrect");
        }
    }
}
