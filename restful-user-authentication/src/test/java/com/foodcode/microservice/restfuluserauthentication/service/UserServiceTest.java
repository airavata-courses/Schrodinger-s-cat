package com.foodcode.microservice.restfuluserauthentication.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.foodcode.microservice.restfuluserauthentication.persistence.model.Role;
import com.foodcode.microservice.restfuluserauthentication.persistence.model.User;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.RoleRepository;
import com.foodcode.microservice.restfuluserauthentication.persistence.repository.UserRepository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Mock
	private UserRepository mockUserRepository;
	@Mock
	private RoleRepository mockRoleRepository;
	@Mock
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;
	@Mock
	private AuthenticationManager mockAuthenticationManager;
	
	private UserService userServiceUnderTest;
	private User user;
	
	@Before
	public void setUp() {
		initMocks(this);
		userServiceUnderTest = new UserService(mockUserRepository,mockRoleRepository,mockBCryptPasswordEncoder, mockAuthenticationManager);
		user = new User();
		user.setActive(1);
		user.setEmail("test@example.com");
		user.setFirstName("test");
		user.setLastName("test");
		Optional<Role> userRole = mockRoleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole.get())));
		user.setPassword(mockBCryptPasswordEncoder.encode("test@1234"));
		
		Mockito.when(mockUserRepository.save(any()))
        .thenReturn(user);
		 Mockito.when(mockUserRepository.findByEmail(anyString()))
         .thenReturn(user);
	}
	
	@Test
    public void testFindUserByEmail() {
        // Setup
        final String email = "test@example.com";

        // Run the test
        final User result = userServiceUnderTest.findUserByEmail(email);

        // Verify the results
        //System.out.println("find by email id");
        assertEquals(email, result.getEmail());
    }
	
	@Test
    public void testSaveUser() {
        // Setup
        final String email = "test@example.com";

        // Run the test
        User result = userServiceUnderTest.saveUser(user);

        // Verify the results
        //System.out.println("testing save user");
        assertEquals(email, result.getEmail());
    }

}
