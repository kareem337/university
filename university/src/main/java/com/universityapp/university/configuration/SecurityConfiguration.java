//package com.universityapp.university.configuration;
//
//import com.universityapp.university.configuration.ValidationReportFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .addFilterBefore(new ValidationReportFilter(), UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests((authorize) ->
//                        authorize
//                                .requestMatchers("/courses/{id}").permitAll()
//                                .anyRequest().authenticated()
//                ).httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("$2a$12$a/fJlwHLDhUgVfTLrpa3TODxx.5Klb6HDKucISn.LGaNdPKf9Gvme")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("$2a$12$a/fJlwHLDhUgVfTLrpa3TODxx.5Klb6HDKucISn.LGaNdPKf9Gvme")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
