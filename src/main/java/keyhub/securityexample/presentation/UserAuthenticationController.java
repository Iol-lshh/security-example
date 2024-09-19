package keyhub.securityexample.presentation;

import keyhub.securityexample.domain.UserAuthenticationService;
import keyhub.securityexample.domain.dto.LoginCommand;
import keyhub.securityexample.domain.dto.LoginResult;
import keyhub.securityexample.domain.dto.SiginUpResult;
import keyhub.securityexample.domain.dto.SignUpCommand;
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
    public ResponseEntity<SiginUpResult> signup(@RequestBody SignUpCommand command) {
       SiginUpResult result = userAuthenticationService.signup(command);
       return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResult> authenticate(@RequestBody LoginCommand command) {
        LoginResult result = userAuthenticationService.authenticate(command);
        return ResponseEntity.ok(result);
    }
}
