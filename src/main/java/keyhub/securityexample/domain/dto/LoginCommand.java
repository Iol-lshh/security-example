package keyhub.securityexample.domain.dto;

public record LoginCommand (
        String loginId,
        String password
){
}
