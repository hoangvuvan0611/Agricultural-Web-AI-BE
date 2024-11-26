package com.ttcn.vnuaexam.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class SecurityConfig {

    private final String[] PUBLIC_URL_POST = {
//            "/api/user/add",
             "/auth/token"
            , "/auth/introspect"
    };

    private final String[] URL_ADMIN ={
            "/api/user/**"
            ,"/api/questions/**"
            ,"/api/class/**"
            ,"/api/subject/**"
            ,"api/exam/**"
    };

    private final String[] URL_TEACHER ={
            "/api/class/**"
            ,"/api/subject/**"
            ,"api/exam/**"
    };



    @Value("${jwt.signer.key}")
    protected String SIGNER_KEY ;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, PUBLIC_URL_POST).permitAll()
                        .requestMatchers(URL_ADMIN).hasAuthority("SCOPE_ADMIN")
                        .requestMatchers(URL_TEACHER).hasAuthority("SCOPE_TEACHER")
                        .requestMatchers("/api/proctor/exam-session").hasAuthority("SCOPE_PROCTOR")
                        .requestMatchers("/api/student/exam-session").hasAuthority("SCOPE_STUDENT")
                        .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(oauth2
                -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));

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
}