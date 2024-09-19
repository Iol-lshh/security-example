package keyhub.securityexample.common.config;

import keyhub.securityexample.common.lib.UserAuthenticationJwtFilter;
import keyhub.securityexample.common.lib.UserAuthenticationJwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final AuthenticationProvider authenticationProvider;
    private final UserDetailsService userDetailsService;
    private final UserAuthenticationJwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserAuthenticationJwtManager tokenManager) throws Exception {
        //Filter jwtFilter = new UserAuthenticationJwtFilter(handlerExceptionResolver, tokenManager, userDetailsService);
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/auth/**",
                                "/swagger-ui/**", "/v3/api-docs/**","/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(config -> config
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        configuration.setAllowedMethods(List.of("GET","POST"));
//        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
}
