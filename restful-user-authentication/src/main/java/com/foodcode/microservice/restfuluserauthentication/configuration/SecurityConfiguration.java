package com.foodcode.microservice.restfuluserauthentication.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
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
    
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
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

    	 http
         	.authorizeRequests()
         		.antMatchers("/auth/users/logmein").permitAll()
         		.antMatchers("/auth/users/logmeout").authenticated()
         		.antMatchers("/auth/users/get/**").authenticated()
         		.antMatchers("/jpa/users/register").permitAll()
         		.antMatchers("/jpa/users/get/**").permitAll()
         		.antMatchers("/jpa/users/delete/**").authenticated()
         		.antMatchers("/jpa/users/create/**").authenticated()
         		.antMatchers("/jpa/users/create/**","/jpa/users/delete/**").hasRole("ADMIN").anyRequest()
         		.authenticated().and().csrf().disable()
         		.headers().frameOptions().disable();
//    	 http

//      	.logout()
//       		.logoutUrl("/jpa/users/logmeout").clearAuthentication(true).logoutSuccessUrl("/auth/users/logmeout")
//       		.and()
//      	.authorizeRequests()
//      		.antMatchers("/jpa/users/login").permitAll()
//      		.antMatchers("/").permitAll()
//      		.antMatchers("/login").permitAll()
//      		.antMatchers("/registration").permitAll()
//      		.antMatchers("/jpa/users/get/**").permitAll()
//      		.antMatchers("/admin/**","/jpa/users/create/**","/jpa/users/delete/**").hasAuthority("ADMIN").anyRequest()
//      		.authenticated().and().csrf().disable().formLogin()
//      	.loginPage("/jpa/users/login").failureUrl("/login?error=true")
//      		.defaultSuccessUrl("/admin/home")
//      		.usernameParameter("username")
//      		.passwordParameter("password")
//      	.and().logout()
//      		.logoutUrl("/jpa/users/logmeout")
//      	.and()
//      		.headers().frameOptions().disable();
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
