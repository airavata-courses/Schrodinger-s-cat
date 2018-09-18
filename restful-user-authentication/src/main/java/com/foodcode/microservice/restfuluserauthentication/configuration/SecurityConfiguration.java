package com.foodcode.microservice.restfuluserauthentication.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.foodcode.microservice.restfuluserauthentication.controller.exception.JDBCConnectionFailed;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;
    
    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	auth
    		.jdbcAuthentication()
    		.usersByUsernameQuery(usersQuery)
    		.authoritiesByUsernameQuery(rolesQuery)
    		.dataSource(dataSource)
    		.passwordEncoder(bCryptPasswordEncoder);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
//		http
//		.httpBasic()
//		.and()
//		.authorizeRequests().antMatchers("/jpa/users/get/**").permitAll()
//		.and().authorizeRequests().antMatchers("/jpa/users/create/**","/jpa/users/delete/**").hasRole("USER")
//		.antMatchers("/**").hasRole("ADMIN")
//		.and().csrf().disable().headers().frameOptions().disable();
//		
//		.and()
//		 .authorizeRequests()
//         .antMatchers("/").permitAll()
//         .antMatchers("/login").permitAll()
//         .antMatchers("/registration").permitAll()
//         .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
//         
//         .authenticated().and().csrf().disable().formLogin()
//         .loginPage("/login").failureUrl("/login?error=true")
//         .defaultSuccessUrl("/admin/home")
//         .usernameParameter("email")
//         .passwordParameter("password")
//         .and().logout()
//         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//         .logoutSuccessUrl("/").and().exceptionHandling()
//         .accessDeniedPage("/access-denied");
		
//    	http
//    		.authorizeRequests()
//    		.antMatchers("/").permitAll()
//            .antMatchers("/login").permitAll()
//            .antMatchers("/registration").permitAll()
//            .antMatchers("/jpa/users/get/**").hasAuthority("ADMIN").anyRequest().authenticated()
//            .antMatchers("/jpa/users/create/**","/jpa/users/delete/**").hasAuthority("ADMIN").anyRequest().authenticated()//.antMatchers("/**").hasAuthority("ADMIN").anyRequest().authenticated()
//            .and().csrf().disable().formLogin()
//            .loginPage("/login").failureUrl("/login?error=true")
//            .defaultSuccessUrl("/admin/home")
//            .usernameParameter("email")
//            .passwordParameter("password")
//            .and().logout()
//            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//            .logoutSuccessUrl("/").and().exceptionHandling()
//            .accessDeniedPage("/access-denied");
    	 http
         	.authorizeRequests()
         		.antMatchers("/").permitAll()
         		.antMatchers("/login").permitAll()
         		.antMatchers("/registration").permitAll()
         		.antMatchers("/jpa/users/get/**").permitAll()
         		.antMatchers("/admin/**","/jpa/users/create/**","/jpa/users/delete/**").hasAuthority("ADMIN").anyRequest()
         		.authenticated().and().csrf().disable().formLogin()
         	.loginPage("/login").failureUrl("/login?error=true")
         		.defaultSuccessUrl("/admin/home")
         		.usernameParameter("email")
         		.passwordParameter("password")
         	.and().logout()
         		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         		.logoutSuccessUrl("/").and().exceptionHandling()
         		.accessDeniedPage("/access-denied")
         	.and()
         		.headers().frameOptions().disable();
	}
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
    
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth
//		.inMemoryAuthentication().withUser("user1").password("{noop}secret1").roles("USER")
//		.and()
//		.withUser("admin1").password("{noop}secret1").roles("USER","ADMIN");
//	}
}
