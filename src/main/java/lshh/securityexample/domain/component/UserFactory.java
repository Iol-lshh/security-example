package lshh.securityexample.domain.component;

import lshh.securityexample.common.dto.SignupCommand;
import lshh.securityexample.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public User create(SignupCommand command) {
        return User.builder()
                .loginId(command.loginId())
                .password(command.password())
                .build();
    }
}
