package com.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@PropertySource(value = {"classpath:application.properties"})
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic().and().authorizeRequests().antMatchers("/v1/users/**").permitAll()
        .antMatchers("/v1/login").permitAll().antMatchers("/**").permitAll().and().csrf().disable();
    }


    @Bean(name = "securityPasswordEncoder")
    public PasswordEncoder passwordencoder()
    {
        return new BCryptPasswordEncoder();
    }

        public static void main(String[] args)
        {
            System.out.println(new BCryptPasswordEncoder().encode("mohammed"));
        }

}
