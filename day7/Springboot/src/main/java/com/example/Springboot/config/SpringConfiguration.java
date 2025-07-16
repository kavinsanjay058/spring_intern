package com.example.Springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.
                csrf((CsrfConfigurer<HttpSecurity> csrf)-> csrf.disable())
                .authorizeHttpRequests(auth->{
//                    auth.requestMatchers(HttpMethod.GET,"/").hasRole("ADMIN");
////                    → Only allow GET requests to / if the user has ADMIN role.
//                    auth.requestMatchers(HttpMethod.POST,"/").hasAnyRole("ADMIN","USER");
////                    → Allow POST requests to / if the user has either ADMIN or USER role.
//                    auth.requestMatchers(HttpMethod.PUT,"/{id}").hasAnyRole("ADMIN","USER");
//                    auth.requestMatchers(HttpMethod.DELETE,"/").hasRole("ADMIN");
//
//                    auth.requestMatchers(HttpMethod.GET,"/**").hasAnyRole("ADMIN","USER");
////                    "/**": Matches all URLs in the application (any path and subpath).

                    auth.anyRequest().authenticated();
//                    All other requests must be authenticated (logged in), but can be of any role.
                })
                .httpBasic(Customizer.withDefaults());
//        .httpBasic(Customizer.withDefaults())
//          -      - is what hides the default HTML login form and shows the browser pop-up login window instead.

//        .formLogin(Customizer.withDefaults()); -- > for form
        return http.build();
    }


    ////    storing the credentials inside the springboot code (in memory)
//    @Bean
//    InMemoryUserDetailsManager userDetails() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

//    both are same (same as the above)
    @Bean
    UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

}