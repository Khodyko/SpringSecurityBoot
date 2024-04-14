package by.khodyko.different.securities.boot.db;

import by.khodyko.different.securities.boot.db.handler.DefaultAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private DefaultAccessDeniedHandler defaultAccessDeniedHandler;

    @Autowired
    public WebSecurityConfig(DefaultAccessDeniedHandler defaultAccessDeniedHandler) {
        this.defaultAccessDeniedHandler = defaultAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/actuator/health", "/login", "/logout").permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
                                .anyRequest().denyAll()
                )
                .exceptionHandling((ex)->ex.accessDeniedHandler(defaultAccessDeniedHandler))
                .formLogin(login-> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/user/hello"))
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
