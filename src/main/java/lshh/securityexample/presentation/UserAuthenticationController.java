package lshh.securityexample.presentation;

import lshh.securityexample.common.dto.LoginCommand;
import lshh.securityexample.common.dto.LoginResult;
import lshh.securityexample.common.dto.SignupCommand;
import lshh.securityexample.common.dto.SignupResult;
import lshh.securityexample.domain.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {
    private final UserAuthenticationService userAuthenticationService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResult> signup(@RequestBody SignupCommand command) {
        SignupResult result = userAuthenticationService.signup(command);
       return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResult> login(@RequestBody LoginCommand command) {
        LoginResult result = userAuthenticationService.login(command);
        return ResponseEntity.ok(result);
    }
}
