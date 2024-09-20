package lshh.securityexample.common.exception;

public class UserAuthenticationException extends RuntimeException{
    public UserAuthenticationException(String message){
        super(message);
    }
}
