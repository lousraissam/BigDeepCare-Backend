package esi_cardio.com.auth_cardio.config;

import esi_cardio.com.auth_cardio.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailService userDetailsService;

    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests().antMatchers(
                "/users/update/{id}","/users","Admin/users/{id}","/users/delete/{id}")
                .hasRole("ADMIN")
                .antMatchers( "/helloadministration")
                .hasAnyRole("ADMINISTRATION")
                .antMatchers( "/users/{id}","users/patient/update/{id}","users/patient/{id}","users/patient/{id}/ECG","users/medecin/{id}/patients")
                .hasRole("MEDECIN")
                .antMatchers( "/registerPatient", "/users/{id}","users/patient/update/{id}","/aideS/update/{id}","aideS/medecins","aideS/patients","users/{id}/medecin","/helloaidesoignant")
                .hasRole("AIDE_SOIGNANT")
                .antMatchers("/hellopatient","users/patient/{id}/Gly")
                .hasRole("PATIENT")
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/register","/authenticate","users/get/{username}").permitAll().anyRequest().authenticated()
                //if any exception occurs call this
                .and().exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// 		Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(customJwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

}
