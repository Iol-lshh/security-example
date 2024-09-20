package lshh.securityexample.common.dto;

public record SignupCommand(
        String loginId,
        String password
) {
}
