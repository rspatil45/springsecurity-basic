package springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//public class MySecurityConfig extends WebSecurityConfigurerAdapter { //depricated
//@Configuration alread present in @EnableWebSecurity
@EnableWebSecurity(debug = true) //to display security filter chain
public class MySecurityConfig extends WebSecurityConfigurerAdapter{	
//this class is going to help you create spring security chain
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
//		auth
//		.inMemoryAuthentication()
//		.withUser("rahul").password("{123").roles("admin")
//		.and()
//		.withUser("anjali").password("123").roles("user")
//		.roles("admin");
		
		//load the info from table
		auth.jdbcAuthentication().dataSource(dataSource)
//		.passwordEncoder(NoOpPasswordEncoder.getInstance());
		.passwordEncoder(passwordEncoder);
		
	}
	
	@Bean
	PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
//		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.authorizeRequests()
//		.anyRequest()					
//		.antMatchers("/customlogin").permitAll()		
//		.antMatchers("/bye").permitAll()
//		.antMatchers("/signup").permitAll()			
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
