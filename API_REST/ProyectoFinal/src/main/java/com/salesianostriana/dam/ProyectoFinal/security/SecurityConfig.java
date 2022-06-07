package com.salesianostriana.dam.ProyectoFinal.security;

import com.salesianostriana.dam.ProyectoFinal.security.jwt.JwtAccessDeniedHandler;
import com.salesianostriana.dam.ProyectoFinal.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().configurationSource(corsConfigurationSource());
        http
                .csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login").anonymous()
                .antMatchers(HttpMethod.GET, "/auth/me").authenticated()
                .antMatchers(HttpMethod.POST, "/especialidad/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/especialidad/{id}").hasAnyRole("ADMIN","MEDICO","PACIENTE")
                .antMatchers(HttpMethod.GET, "/especialidad/").hasAnyRole("ADMIN","MEDICO","PACIENTE")
                .antMatchers(HttpMethod.PUT, "/especialidad/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/especialidad/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/medicamento/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/medicamento/{id}").hasAnyRole("ADMIN","MEDICO","PACIENTE")
                .antMatchers(HttpMethod.GET, "/medicamento/").hasAnyRole("ADMIN","MEDICO","PACIENTE")
                .antMatchers(HttpMethod.PUT, "/medicamento/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/medicamento/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/user/medico").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/medico/{id}").hasAnyRole("ADMIN","MEDICO","PACIENTE")
                .antMatchers(HttpMethod.GET,"/medico/").hasAnyRole("ADMIN","MEDICO","PACIENTE")
                .antMatchers(HttpMethod.PUT,"/medico/{id}").hasAnyRole("ADMIN","MEDICO")
                .antMatchers(HttpMethod.DELETE,"/medico/{id}").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/user/paciente").hasAnyRole("ADMIN","MEDICO")
                .antMatchers(HttpMethod.GET, "paciente/{id}").hasAnyRole("ADMIN","MEDICO")
                .antMatchers(HttpMethod.GET, "paciente/").hasAnyRole("ADMIN","MEDICO")
                .antMatchers(HttpMethod.PUT, "paciente/{id}").hasAnyRole("ADMIN","MEDICO")
                .antMatchers(HttpMethod.DELETE, "paciente/{id}").hasAnyRole("ADMIN","MEDICO")
                .antMatchers(HttpMethod.POST, "/receta/").hasRole("MEDICO")
                .antMatchers(HttpMethod.GET, "/receta/{id}").hasAnyRole("ADMIN","MEDICO","PACIENTE")
                .antMatchers(HttpMethod.GET, "/receta/").hasAnyRole("ADMIN","MEDICO","PACIENTE")
                .antMatchers(HttpMethod.PUT, "/receta/{id}").hasRole("MEDICO")
                .antMatchers(HttpMethod.DELETE, "/receta/{id}").hasAnyRole("ADMIN","MEDICO")
                .anyRequest().permitAll();

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }




    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
