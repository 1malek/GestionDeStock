package com.SpringBootProject.GestiondeStock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration  {
	
	  private final UserDetailsService userDetailsService;
	 
	  private final JwtFilter jwtFilter;
	
	@Bean
	  public SecurityFilterChain  filterChaine(HttpSecurity http) throws Exception {
		 http
         .csrf().disable()
         .authorizeHttpRequests((request ) ->{try {
                 request.requestMatchers("/**/register")
                         .permitAll()
                         .anyRequest()
                         .authenticated()
         .and() 
         .sessionManagement()
        
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);}
         catch(Exception e) {e.printStackTrace();}})
         .authenticationProvider(authenticationProvider())
        . addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
         ;
 return http.build();
}
	 @Bean
	  public AuthenticationProvider authenticationProvider() throws Exception {
		 DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		 authenticationProvider.setUserDetailsService(userDetailsService);
		 authenticationProvider.setPasswordEncoder(passwordEncoder());
		 return authenticationProvider;
	  }       
	 @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	return config.getAuthenticationManager();
}}