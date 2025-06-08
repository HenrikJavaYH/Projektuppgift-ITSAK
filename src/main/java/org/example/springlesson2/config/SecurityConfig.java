package org.example.springlesson2.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.formLogin(Customizer.withDefaults());

        //http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());


        return http.build();
    }

    /* @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails user = User.builder().username("Henrik").
                password(passwordEncoder.encode("password")).
                roles("ADMIN").build();

        manager.createUser(user);
        return manager;
    } */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
