package net.ebookPrasad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
                .authorizeRequests()
                	
                    .antMatchers("/resources/**", "/WEB-INF/jsp/*").permitAll()
                    .antMatchers(HttpMethod.GET,"/register").permitAll()
                    .antMatchers(HttpMethod.GET,"/").permitAll()
                    .antMatchers(HttpMethod.GET,"/courses").permitAll()
                    .antMatchers(HttpMethod.GET,"/search/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/category/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/admin/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/dashboard/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/forgot").permitAll()
                    .antMatchers(HttpMethod.GET,"/download/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/course/view/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/book/view/**").permitAll()
                    .antMatchers(HttpMethod.POST).permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                    .permitAll()
                    .and()
                    
                .logout()
                	.logoutUrl("/logout")
                	.logoutSuccessUrl("/login?logout")
                    .permitAll();
        		
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}