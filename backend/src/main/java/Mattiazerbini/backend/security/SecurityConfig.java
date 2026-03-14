package Mattiazerbini.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JWTCheckedFilter jWTCheckedFilter) throws Exception {

        httpSecurity.cors(Customizer.withDefaults());
        httpSecurity.formLogin(formLogin -> formLogin.disable());
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.sessionManagement(sessions
                -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/utenti/reset-password").permitAll()


                .requestMatchers(HttpMethod.GET, "/campi/**").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/campi/*/immagine").hasAuthority("ADMIN")

                .requestMatchers("/campi/**").hasAuthority("ADMIN")

                .requestMatchers(HttpMethod.GET, "/servizi/**").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/servizi/*/immagine").hasAuthority("ADMIN")
                .requestMatchers("/servizi/**").hasAuthority("ADMIN")

                .requestMatchers(HttpMethod.POST, "/recensioni/**").hasAuthority("USER")
                .requestMatchers(HttpMethod.GET, "/recensioni/**").permitAll()


                .requestMatchers(HttpMethod.POST, "/prenotazioni/**").hasAuthority("USER")
                .requestMatchers(HttpMethod.GET, "/prenotazioni/utente/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/prenotazioni/**").hasAnyAuthority("USER", "ADMIN")

                .requestMatchers(HttpMethod.PATCH, "/utenti/*/avatar").authenticated()

                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
        );
        httpSecurity.addFilterBefore(jWTCheckedFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:5174"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
