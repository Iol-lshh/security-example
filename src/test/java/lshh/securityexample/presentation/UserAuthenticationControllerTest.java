package lshh.securityexample.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lshh.securityexample.common.dto.LoginCommand;
import lshh.securityexample.common.dto.SignupCommand;
import lshh.securityexample.domain.UserAuthenticationService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserAuthenticationService service;

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
            SignupCommand command = new SignupCommand("signuptest", "password");

            var result = mockMvc.perform(post("/auth/signup")
                            .content(asJsonString(command))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            System.out.print("result: ");
            System.out.println(result.getContentAsString());
        }
    }

    @Nested
    class LoginTest{
        @Test
        public void givenValidLoginRequest_whenLogin_thenReturns200() throws Exception {
            SignupCommand command = new SignupCommand("logintest", "password");
            service.signup(command);

            var result = mockMvc.perform(post("/auth/login")
                            .content(asJsonString(command))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            System.out.print("result: ");
            System.out.println(result.getContentAsString());
        }
    }

    @Nested
    class HomeTest{
        @Test
        public void givenValidRequest_whenHome_thenReturns403() throws Exception {
            var result = mockMvc.perform(get("/home/test"))
                    .andExpect(status().isForbidden())
                    .andReturn()
                    .getResponse();

            System.out.print("result: ");
            System.out.println(result.getContentAsString());
        }

        @Test
        public void givenValidRequest_whenHome_thenReturns200() throws Exception {
            SignupCommand signupCommand = new SignupCommand("loginHomeTest", "password");
            service.signup(signupCommand);
            LoginCommand loginCommand = new LoginCommand("loginHomeTest", "password");
            String jwt = service.login(loginCommand).token();

            var result = mockMvc.perform(get("/home/test").header("Authorization", "Bearer " + jwt))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();

            System.out.print("result: ");
            System.out.println(result.getContentAsString());
        }
    }
}