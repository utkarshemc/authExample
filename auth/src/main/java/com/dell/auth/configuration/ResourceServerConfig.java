/**
 * 
 */
package com.dell.auth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author bhardu
 * @Since May 8, 2020
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable().and().authorizeRequests()
				.antMatchers("/", "/home", "/register", "/login").permitAll().antMatchers("/private/**")
				.authenticated();
	}
}
