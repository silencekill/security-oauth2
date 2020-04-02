package org.javaboy.auth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    // 配置可登录的用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("javaboy").password("$2a$10$yPgmPbhbQgYOOoD31fCRyeV0pmzxspp2AYkz1FKemcaHAvpMcEbFy").roles("admin")
                .and()
                .withUser("zhang").password("$2a$10$yPgmPbhbQgYOOoD31fCRyeV0pmzxspp2AYkz1FKemcaHAvpMcEbFy").roles("user");
    }

    // oauth类型的请求不需要拦截
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth/**")
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
                .and()
                .csrf().disable();
    }
}
