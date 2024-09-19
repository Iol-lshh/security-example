package keyhub.securityexample.domain.dto;

public record SignUpCommand(
        String loginId,
        String password
) {
}
