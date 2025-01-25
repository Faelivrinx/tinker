package com.dominikdev.tinker.security

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {

    @Bean
    fun userDetailsManager() : UserDetailsManager {
        return JdbcUserDetailsManager()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .logout{ it.disable() }
            .rememberMe{ it.disable() }
            .httpBasic{ it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth -> auth
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/h2-console   /**").permitAll()
                .requestMatchers("/token").permitAll()
                .anyRequest().authenticated()
            }
            .oauth2ResourceServer { it.jwt(Customizer.withDefaults())}
            .build()
    }

    @Bean
    fun jwtDecoder(rsaKey: RSAKey): JwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build()

    @Bean
    fun jwtEncoder(rsaKey: RSAKey) : JwtEncoder = NimbusJwtEncoder(ImmutableJWKSet(JWKSet(rsaKey)))

    @Bean
    fun rsaKey(rsaConfig: RSAConfig) : RSAKey = RSAKey.Builder(rsaConfig.getPublicKey()).privateKey(rsaConfig.getPrivateKey()).build()

    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()
}