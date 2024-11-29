package com.codeneeti.technexushub;

import com.codeneeti.technexushub.entities.Role;
import com.codeneeti.technexushub.entities.User;
import com.codeneeti.technexushub.repositories.RoleRepository;
import com.codeneeti.technexushub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.PasswordAuthentication;
import java.util.UUID;

@SpringBootApplication
@RestController
public class TechNexusHubApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(TechNexusHubApplication.class, args);
	}
	@GetMapping("/hi")
	public String welcomeMsg(){

		return "Welcome back";
	}
	@GetMapping("/hello")
	public String welcomeMsg2(){

		return "Welcome authorized back";
	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("Kabeer@12"));


		Role role1=roleRepository.findByName("ROLE_ADMIN").orElse(null);
	if (role1==null){
		role1 =new Role();
		role1.setName("ROLE_ADMIN");
		role1.setRoleId(UUID.randomUUID().toString());
		roleRepository.save(role1);
	}
		Role role2=roleRepository.findByName("ROLE_GUEST").orElse(null);
		if (role2 == null) {
			role2 =new Role();
			role2.setName("ROLE_GUEST");
			role2.setRoleId(UUID.randomUUID().toString());
			roleRepository.save(role2);
		}
		User user=userRepository.findByUserName("ram").orElse(null);
		if (user==null){
			user=new User();
			user
		}



	}
}
