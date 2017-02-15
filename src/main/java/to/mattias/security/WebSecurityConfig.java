package to.mattias.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import to.mattias.security.jwt.JWTAuthenticationFilter;
import to.mattias.security.jwt.JWTLoginFilter;
import to.mattias.services.UserDetailService;
import to.mattias.services.UserService;

/**
 * <h1>Created by Mattias on 2017-02-06.</h1>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DriverManagerDataSource dataSource;
    @Autowired
    UserDetailService userDetailService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                    .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }
}

