package com.curs.admin_panel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AdminPanelApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminPanelApplication.class, args);
	}

}
