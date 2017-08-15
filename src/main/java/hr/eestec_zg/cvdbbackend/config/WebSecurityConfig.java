package hr.eestec_zg.cvdbbackend.config;

import hr.eestec_zg.cvdbbackend.config.security.DatabaseAuthenticationProvider;
import hr.eestec_zg.cvdbbackend.config.security.JsonFailureAuthenticationHandler;
import hr.eestec_zg.cvdbbackend.config.security.JsonLogoutSuccessHandler;
import hr.eestec_zg.cvdbbackend.config.security.JsonSuccessAuthenticationHandler;
import hr.eestec_zg.cvdbbackend.config.security.UnauthorizedEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JsonSuccessAuthenticationHandler authSuccessHandler;
    private final JsonFailureAuthenticationHandler authFailureHandler;
    private final JsonLogoutSuccessHandler logoutSuccessHandler;

    public WebSecurityConfig(
            JsonSuccessAuthenticationHandler authSuccessHandler,
            JsonFailureAuthenticationHandler authFailureHandler,
            JsonLogoutSuccessHandler logoutSuccessHandler) {

        this.authSuccessHandler = authSuccessHandler;
        this.authFailureHandler = authFailureHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // change response code to 401 when unauthorized
        http.exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint());

        http.sessionManagement().maximumSessions(100).sessionRegistry(sessionRegistry());


        http
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .permitAll()
                .and()

                .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .anyRequest().authenticated()
                .and()

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(6);
    }

    @Bean
    public AuthenticationManager authenticationManager(DatabaseAuthenticationProvider databaseAuthenticationProvider) {
        List<AuthenticationProvider> providers = new LinkedList<>();
        providers.add(databaseAuthenticationProvider);

        return new ProviderManager(providers);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

}
