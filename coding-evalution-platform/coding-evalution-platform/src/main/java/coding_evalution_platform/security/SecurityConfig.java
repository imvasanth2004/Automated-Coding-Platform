package coding_evalution_platform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("*"));
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                config.setAllowedHeaders(List.of("*"));
                return config;
            }))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/register", "/api/users/login", "/h2-console/**").permitAll() // Public pages
                .anyRequest().authenticated() // Everything else requires Login
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No sessions, use Tokens
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // Add our filter
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }
}