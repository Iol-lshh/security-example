package keyhub.securityexample.common.config;

import keyhub.securityexample.common.lib.UserAuthenticationJwtManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenManagerConfig {

    @Bean
    public UserAuthenticationJwtManager tokenManager(
            @Value("${security.jwt.secret-key}") String secret,
            @Value("${security.jwt.expiration-time}") long expiration
    ) {
        return new UserAuthenticationJwtManager(secret, expiration);
    }
}
