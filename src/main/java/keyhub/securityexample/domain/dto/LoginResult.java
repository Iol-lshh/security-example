package keyhub.securityexample.domain.dto;

public record LoginResult(
        String jwtToken,
        long expirationTime
) {
}
