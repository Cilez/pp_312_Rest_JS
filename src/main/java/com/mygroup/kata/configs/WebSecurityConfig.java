package com.mygroup.kata.configs;


import com.mygroup.kata.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserService userService;
    // Должно быть отдельным бином, иначе зацикливание
    private  final BCryptPasswordEncoder passwordEncoder;



    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService,BCryptPasswordEncoder passwordEncoder) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                //Доступ разрешен всем пользователей
                .antMatchers("/").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/assets/**").permitAll()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/login").not().fullyAuthenticated()
                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/admin/**", "/add", "/edit").hasRole("ADMIN")
                //Доступ для юзера
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().permitAll()

                //Все остальные страницы требуют аутентификации
//                .anyRequest().authenticated()
                .and()
                //Настройка для входа в систему
                .formLogin().successHandler(successUserHandler)
                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/")
                .and()
                //без этого почему-то не работает
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/templates/static/**", "/css/**", "/js/**", "/img/**", "/icon/**");
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}