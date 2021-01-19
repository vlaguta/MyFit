package com.diplom.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider customAuthProvider;

    //@Override
    //public void configure(AuthenticationManagerBuilder auth)
    //        throws Exception {
    //
    //    auth.authenticationProvider(customAuthProvider);
    //    auth.inMemoryAuthentication()
    //            .withUser("memuser")
    //            .password(/*encoder().encode(*/"pass")
    //            .roles("USER");
    //}

    //@Bean
    //@Override
    //public UserDetailsService userDetailsService() {
    //
    //
    //    return new InMemoryUserDetailsManager(userDetailService.loadUserByUsername());
    //}


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/profile").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/customers/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
