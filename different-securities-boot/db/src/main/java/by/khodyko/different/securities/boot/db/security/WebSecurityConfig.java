package by.khodyko.different.securities.boot.db.security;

import by.khodyko.different.securities.boot.db.handler.DefaultAccessDeniedHandler;
import by.khodyko.different.securities.boot.db.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private DefaultAccessDeniedHandler defaultAccessDeniedHandler;
    private UserService userService;

    public WebSecurityConfig(DefaultAccessDeniedHandler defaultAccessDeniedHandler, UserService userService) {
        this.defaultAccessDeniedHandler = defaultAccessDeniedHandler;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/actuator/health", "/login", "/logout",
                                        "/user/registration", "/registration", "/account-locked").permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
                                .anyRequest().denyAll()
                )
                .exceptionHandling((ex) -> ex.accessDeniedHandler(defaultAccessDeniedHandler))
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/user/hello")
                        .failureHandler(authenticationFailureHandler()))
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler(userService);
    }

}
