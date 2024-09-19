package keyhub.securityexample.common.lib;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface UserAuthenticationTokenManager {

    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    long getExpirationTime();

    boolean isTokenValid(String token, UserDetails userDetails);
}
