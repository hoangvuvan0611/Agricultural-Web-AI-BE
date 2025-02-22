package com.ttcn.vnuaexam.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] PUBLIC_URL_POST = {
             "/auth/login"
            , "/auth/introspect"

    };

    @Value("${jwt.signer.key}")
    protected String SIGNER_KEY ;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, PUBLIC_URL_POST).permitAll()
                        .requestMatchers("/api/user/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers("/api/questions/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers("/api/answers/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers("/api/exam/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/exam-rooms/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers("/api/semesters/**").hasAnyAuthority("ADMIN", "TEACHER", "PROCTOR")
                        .requestMatchers("/api/import-excel/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers("/api/export/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers("/api/class/**").hasAuthority("TEACHER")
                        .requestMatchers("/api/subject/**").hasAnyAuthority("ADMIN", "TEACHER")
                        .requestMatchers("/api/exam/**").hasAuthority("TEACHER")
                        .requestMatchers("/api/proctor/exam-session").hasAuthority("PROCTOR")
                        .requestMatchers("/api/student/exam-session").hasAuthority("STUDENT")
                        .requestMatchers("/api/submit/**").hasAuthority("STUDENT")
                        .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(oauth2
                -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));

        httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> scopes = jwt.getClaimAsStringList("Scope");
            return scopes.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });
        return jwtAuthenticationConverter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}