package lshh.securityexample.common.dto;

public record LoginCommand(
        String loginId,
        String password
) {
}
