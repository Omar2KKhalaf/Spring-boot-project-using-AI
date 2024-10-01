package com.hackathon.hackathonbackend.security;

import com.hackathon.hackathonbackend.security.utils.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //no need of csrf we will generate token and use instead
        http.csrf(csrf -> csrf.disable());
        // to authorize all requests
        http.authorizeHttpRequests(request -> request
                .requestMatchers("api/auth/register", "api/auth/login")
                .permitAll()
                .anyRequest().authenticated());
        // to show formlogin on browser of spring security
//        http.formLogin(Customizer.withDefaults());
        // allow rest access for postman
        http.httpBasic(Customizer.withDefaults());
        // to make session management stateless as we are disabling csrf and are going to use generated token instead
        // important note!!!! when making session management stateless every request new session id is created
        // so in the browser it will not work it works only in postman if you want the browser to work remove loginform
        // so that the browser is treated the same as postman
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //add filter to be able to use token in authentication instead of userpasswordauthentication
        //the flow will be check token if valid then pass the other filter i have verified the user
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * this bean is used to supply the username and password for form login of spring boot
     * but it is still static like adding 2 fields in application.properties
     *
     * @return
     */
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.withDefaultPasswordEncoder()
//                .username("omar")
//                .password("omar")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1);
//    }

    /**
     * this method to get user by email send (in authorization tab in postman) not always
     * and check if he present in the database or not
     * we also take password from authorization encrypt it check against the saved one
     * we need 2 more classes as userDetailsService and userDetails are interfaces so we created 2 more classes
     * MyUserDetailsService which implements userDetailsService only one function present and it return userDetails and because
     * its an interface so UserPrincipal class is created implementing userDetails.
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /**
     * this is going to talk to authenticationProvider then this is the normal flow
     * this to control the normal flow of security flow
     * @param configuration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
