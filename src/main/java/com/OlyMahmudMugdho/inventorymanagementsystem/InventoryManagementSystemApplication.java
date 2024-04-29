package com.OlyMahmudMugdho.inventorymanagementsystem;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.Role;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.RoleRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryManagementSystemApplication {
	@Autowired
	private final RoleService roleService;

	public InventoryManagementSystemApplication(RoleService roleService) {
		this.roleService = roleService;
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner startup() {
		return args -> {
			Role user = new Role(1,"USER");
			Role admin = new Role(2,"ADMIN");
			roleService.createRole(user);
			roleService.createRole(admin);
			System.out.println("role created");
		};
	}
}
