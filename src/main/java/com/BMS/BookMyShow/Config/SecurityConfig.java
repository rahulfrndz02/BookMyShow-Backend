package com.BMS.BookMyShow.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity  //for method level authentication ex- preAuthorize()
public class SecurityConfig extends WebSecurityConfiguration {

    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();  //password will encrypt
    }

    //username and password can be stored in these way -> InMemory and database
    //saving username and password for different-different users with InMemoryMethod
    @Bean
    public UserDetailsService userDetailsService(){

        //saving username and password for user
        UserDetails user= User.withUsername("user")
                .password(getEncoder().encode("password1"))
                .roles("USER")
                .build();

        //saving username and password for admin
        UserDetails admin = User.withUsername("admin")
                .password(getEncoder().encode("password2"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
        //return new CustomUserDetailService(); //for storing username and password in db
    }

    @Bean(name = "mySecurityFilterChain")
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                //.requestMatchers("/movie/get_all_movie")
                .requestMatchers("/ticket/**", "/movie/**", "/show/**")
               // .hasAnyRole("ADMIN")
                 .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        return httpSecurity.build();
    }
}
