package com.mavaze.checkout.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mavaze.checkout.domain.Actor;

@Configuration
@EnableJpaAuditing
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/products", "/products/**").hasAnyRole("ADMIN", "CASHIER")
		.antMatchers("/categories", "/categories/**").hasAnyRole("ADMIN", "SALES_MANAGER")
		.antMatchers("/invoices", "/invoices/**").hasAnyRole("ADMIN", "CASHIER")
		.and().httpBasic()
		.and().csrf().disable()
		.headers().frameOptions().disable();
	}
	
	@Bean
	public AuditorAware<Actor> auditorProvider() {

		return new AuditorAware<Actor>() {
			@Override
			public Actor getCurrentAuditor() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null || !authentication.isAuthenticated()) {
					return null;
				}
				return ((ActorDetails) authentication.getPrincipal()).getUser();
			}
		};
	}
}
