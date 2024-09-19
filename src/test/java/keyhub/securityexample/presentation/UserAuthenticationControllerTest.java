package keyhub.securityexample.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import keyhub.securityexample.domain.dto.SiginUpResult;
import keyhub.securityexample.domain.dto.SignUpCommand;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Nested
    class SignUpTest{
        @Test
        public void givenValidSignUpRequest_whenSignUp_thenReturns200() throws Exception {
            SignUpCommand command = new SignUpCommand("user", "password");
            SiginUpResult result = new SiginUpResult();

            mockMvc.perform(post("/auth/signup")
                            .content(asJsonString(command))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

}