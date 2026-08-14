package br.com.passage.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, Environment environment) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        //.requestMatchers(HttpMethod.POST, "/api/v1/companies").hasAuthority("company:create")

                        .requestMatchers(HttpMethod.DELETE, "/api/v1/companies/**").hasRole("ADMIN")

                        .anyRequest().permitAll() //Lembrar de altera para .authenticated para producao.
                )
                .build();
    }
}
