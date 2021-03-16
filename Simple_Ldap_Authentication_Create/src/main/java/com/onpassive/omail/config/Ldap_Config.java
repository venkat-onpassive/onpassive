package com.onpassive.omail.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
@PropertySource("classpath:application.properties")
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class Ldap_Config extends WebSecurityConfigurerAdapter {

	@Value("${spring.ldap.urls}")
	private String ldapUrls;

	@Value("${spring.ldap.embedded.base-dn}")
	private String ldapBaseDn;

	@Value("${spring.ldap.embedded.credential.username}")
	private String ldapSecurityPrincipal;

	@Value("${spring.ldap.embedded.credential.password}")
	private String ldapPrincipalPassword;

	@Value("${spring.ldap.user.dn.pattern}")
	private String ldapUserDnPattern;

	@Value("${ldap.enabled}")
	private String ldapEnabled;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().exceptionHandling().and().authorizeRequests()
				.antMatchers("/api/auth/**", "/hai/**", "/retrieve-users", "/add-user", "/update-user", "/remove-user")
				.permitAll().anyRequest().authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		if (Boolean.parseBoolean(ldapEnabled)) {
			auth.ldapAuthentication().contextSource().url(ldapUrls + ldapBaseDn).managerDn(ldapSecurityPrincipal)
					.managerPassword(ldapPrincipalPassword).and().userDnPatterns(ldapUserDnPattern);
		} /*
			 * else { auth .inMemoryAuthentication()
			 * .withUser("user").password("password").roles("USER") .and()
			 * .withUser("admin").password("admin").roles("ADMIN"); }
			 */
		
		 else {
			  auth.ldapAuthentication().userDnPatterns(ldapUserDnPattern).groupSearchBase(
			  "ou=groups") .contextSource(contextSource1());
			  }
			 
	}

	public DefaultSpringSecurityContextSource contextSource1() {
		return new DefaultSpringSecurityContextSource(
				Collections.singletonList("ldap://mailserver-1.omailuat.onpassive.com"),
				"dc=omailuat,dc=onpassive,dc=com");
	}
}


