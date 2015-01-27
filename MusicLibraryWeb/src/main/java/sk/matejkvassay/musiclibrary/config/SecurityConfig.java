
package sk.matejkvassay.musiclibrary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/user/**").access("hasRole('ADMIN') or hasRole('USER')")
            .antMatchers("/album/new/**").access("hasRole('ADMIN') or hasRole('USER')")
            .antMatchers("/album/update/**").access("hasRole('ADMIN') or hasRole('USER')")
            .antMatchers("/genre/new/**").access("hasRole('ADMIN') or hasRole('USER')")
            .antMatchers("/genre/update/**").access("hasRole('ADMIN') or hasRole('USER')")
            .antMatchers("/musician/new/**").access("hasRole('ADMIN') or hasRole('USER')")
            .antMatchers("/musician/update/**").access("hasRole('ADMIN') or hasRole('USER')")
            .antMatchers("/song/new/**").access("hasRole('ADMIN') or hasRole('USER')")
            .antMatchers("/song/update/**").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers("/rest/**").access("hasRole('ADMIN') or hasRole('USER')")
            .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
            .and()
                .logout().logoutSuccessUrl("/login?logout")
            .and().csrf().disable().httpBasic();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
