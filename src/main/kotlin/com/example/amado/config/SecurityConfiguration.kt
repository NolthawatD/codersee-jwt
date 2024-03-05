package com.example.amado.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authenticationProvider: AuthenticationProvider
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter
    ): DefaultSecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
//                    .requestMatchers("/api/v1/auth", "/api/v1/auth/**", "/error")
//                    .permitAll()
//                    .requestMatchers(HttpMethod.POST, "/api/v1/user/create")
//                    .permitAll()
//                    .requestMatchers(HttpMethod.PATCH, "/api/v1/user/update/**")
//                    .permitAll()
                    .requestMatchers("/api/v1/user/delete/**")
                    .hasRole("ADMIN")
                    .anyRequest()
                    .fullyAuthenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer =
        WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring() // Spring Security should completely ignore URLs starting with /resources/
                .requestMatchers(
                    "/api/v1/auth**",
                    "/api/v1/task**",
                    "/api/v1/student/**",
                    "/api/v1/course/**",
                    "/api/v1/product/**",
                    "/api/v1/movies/**",
                    "/api/v1/actors/**",
                )
        }

}