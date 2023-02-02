package springsecurity.config;

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import springsecurity.service.UserDetailsServiceImpl;

//public class MySecurityConfig extends WebSecurityConfigurerAdapter { //depricated
//@Configuration alread present in @EnableWebSecurity
//@EnableWebSecurity(debug = true) //to display security filter chain
@EnableWebSecurity() //to display security filter chain
public class MySecurityConfig extends WebSecurityConfigurerAdapter{	
//this class is going to help you create spring security chain
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		//load the info from table
		auth.jdbcAuthentication().dataSource(dataSource)
//		.passwordEncoder(NoOpPasswordEncoder.getInstance());
		.passwordEncoder(passwordEncoder);
		
	}	*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception	
	{
//		Different ways of creating users
		
//		1. InMemory Authentication
//		auth.inMemoryAuthentication()
//		.withUser("rahul").password("{noop}rahul123").roles("ADMIN");
		
//		2. Creating object of User class (which implements UserDetails interface)
//		ArrayList<GrantedAuthority> roles = new ArrayList<>();
//		GrantedAuthority role1 = new SimpleGrantedAuthority("USER");
//		roles.add(new SimpleGrantedAuthority("ADMIN"));
//		roles.add(role1);		
//		User user = new User("rahul", "rahul123", roles);
		
//		3.using static inner class UserDetails (using this user for inmemory authentication)
		/*
		UserDetails rahul = User.withUsername("rahul").roles("ADMIN","USER").password("rahul123").build();
		UserDetails anjali = User.withUsername("anjali").password("anjali123").roles("USER").build();		
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		userDetailsManager.createUser(rahul);
		userDetailsManager.createUser(anjali);		
		auth.userDetailsService(userDetailsManager);
		*/
		
//		UserDetailsManager has three implementations 1.InMemoryUserDetailsManager, 2. JdbcUserDetailsManager, 3. FileSystemUserDetailsManager
//		JdbcUserDetailsManager userDetailsManager2 = new JdbcUserDetailsManager(dataSource);
//		auth.userDetailsService(userDetailsManager2);
		
//		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
		
//		using custom schemas
//		auth.jdbcAuthentication()
//		.dataSource(dataSource)
//		.usersByUsernameQuery("select username,password,enabled from customers where username = ? ")
//		.authoritiesByUsernameQuery("select username,authority from customers where username = ? ")
//		.passwordEncoder(passwordEncoder);
		
		auth.userDetailsService(userDetailsServiceImpl)
		.passwordEncoder(passwordEncoder);
		
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.authorizeRequests()
//		.anyRequest()					
//		.antMatchers("/customlogin").permitAll()		
//		.antMatchers("/bye").permitAll()
		.antMatchers("/signup").permitAll()			
		.antMatchers("/coder").hasAuthority("Coder")
		.antMatchers("/trainer").hasAuthority("Trainer")
		.anyRequest().authenticated()
		//.permitAll()  -> use to permit all requests (no login required)
		//.denyAll() -> use instead of authenticated() to deny all login request		
		.and()
		.formLogin().loginPage("/customlogin").loginProcessingUrl("/process-login").permitAll()
		.and()
		.httpBasic()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/accessDenied");
		
		
	}
}
