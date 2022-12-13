package com.mygroup.kata.configs;

import com.mygroup.kata.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/resources/**", "/templates/static/**", "/css/**", "/js/**", "/img/**", "/icon/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable().antMatcher("/**")
                .authorizeRequests()

                .antMatchers("/admin-panel").hasRole("ADMIN")
                .antMatchers("/viewUser").hasAnyRole("ADMIN", "USER")
                .antMatchers("/", "/login/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin().loginPage("/login").permitAll().successHandler(successUserHandler)

                .and()
                .logout()
                .logoutSuccessUrl("/login")
        ;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}