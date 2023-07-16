package com.patient.patient.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.patient.patient.security.CustomStaffDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private CustomStaffDetailsService  staffDetailsService;
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .csrf()
	            .disable() 
	            .authorizeHttpRequests().antMatchers("/api/v1/staff")     // authorize request with authentication
	             .authenticated()
	             .and()
	            .httpBasic();
	    }
	 
	 @Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		 auth.userDetailsService(this.staffDetailsService).passwordEncoder(passwordEncoder());
	 }
		
	 
	 //password encoder
	 @Bean
	    public PasswordEncoder passwordEncoder() {   
	        return new BCryptPasswordEncoder();
	    }
}


	 
