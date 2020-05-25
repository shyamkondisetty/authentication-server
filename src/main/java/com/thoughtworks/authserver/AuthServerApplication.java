package com.thoughtworks.authserver;

import com.thoughtworks.authserver.Permission.Permission;
import com.thoughtworks.authserver.Role.Role;
import com.thoughtworks.authserver.User.User;
import com.thoughtworks.authserver.Permission.PermissionService;
import com.thoughtworks.authserver.Role.RoleService;
import com.thoughtworks.authserver.User.UserService;
import com.thoughtworks.authserver.customclientdetails.CustomClient;
//import com.thoughtworks.authserver.customclientdetails.CustomClientService;
import com.thoughtworks.authserver.customclientdetails.CustomClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserService userService, RoleService roleService, PermissionService permissionService, CustomClientService customClientService) {
		return (args) -> {
			Permission permissionOne = permissionService.create(new Permission("CREATE_PAYMENT"));
			Permission permissionTwo = permissionService.create(new Permission("EDIT_PAYMENT"));
			Permission permissionThree = permissionService.create(new Permission("DELETE_PAYMENT"));
			Permission permissionFour = permissionService.create(new Permission("VIEW_ALL_PAYMENT"));
			Permission permissionFive = permissionService.create(new Permission("VIEW_PAYMENT"));

			Role roleOne = roleService.create(new Role("ADMINISTRATOR", Arrays.asList(permissionOne, permissionTwo,permissionThree,permissionFour,permissionFive)));
			Role roleTwo = roleService.create(new Role("AUDITOR", Arrays.asList(permissionFour,permissionFive)));

			userService.create(new User("user1","user1@gmail.com","$2a$10$jbIi/RIYNm5xAW9M7IaE5.WPw6BZgD8wcpkZUg0jm8RHPtdfDcMgm",Arrays.asList(roleOne)));
			userService.create(new User("user2","user2@gmail.com","$2a$10$jbIi/RIYNm5xAW9M7IaE5.WPw6BZgD8wcpkZUg0jm8RHPtdfDcMgm",Arrays.asList(roleTwo)));


			System.out.println(userService.findById(1));
			System.out.println(userService.findByEmailId("user2@gmail.com"));


			CustomClient customClientOne=new CustomClient(
					"adminclient",
					"$2a$10$jnaG1GUuIywGbwdkQibcgOY7mWnRdF//CqxBQ9Wne1mzfNcSc3iri",
					new HashSet<String>(Arrays.asList("read", "write")),
					new HashSet<String>(Arrays.asList("password", "refresh_token")),
					3600,
					18000,
					new ArrayList<String>(Arrays.asList("ROLE_ADMIN"))
					);
			customClientService.create(customClientOne);

			CustomClient customClientTwo=new CustomClient(
					"serviceclient",
					"$2a$10$q/LAw1TCNgLwdFWeZpC4weojmWwjgkIVCFtcOIyzN6tWeJnI6Zkg.",
					new HashSet<String>(Arrays.asList("read", "write")),
					new HashSet<String>(Arrays.asList("password", "refresh_token")),
					3600,
					18000,
					new ArrayList<String>(Arrays.asList("ROLE_CLIENT"))
			);
			customClientService.create(customClientTwo);

		};
	}

}
