package com.example.ecommerce;

import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.model.UserRole;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting code");
//		User user=new User();
//		user.setFirstName("Hong");
//		user.setLastName("Phong");
//		user.setUsername("hongphong");
//		user.setPassword(this.bCryptPasswordEncoder.encode("123456"));
//		user.setEmail("hongphong.12012001@gmail.com");
//		user.setProfile("avatar.png");
//		Role role=new Role();
//		role.setRoleId(44L);
//		role.setRoleName("ADMIN");
//		Set<UserRole> userRoleSet=new HashSet<>();
//		UserRole userRole=new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//		this.userService.createUser(user,userRoleSet);
	}

}
