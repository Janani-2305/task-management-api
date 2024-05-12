package com.tms.taskmanagementapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@OpenAPIDefinition(
		info = @Info(
				title = "Task Management System",
				description = "Task Management System Rest APIs to serve the UI",
				version = "v1",
				contact = @Contact(
						name = "Janani K",
						email = "jananikaliya23@gmail.com"
				),
				license = @License(
						name = "Not Licensed"
				)
		)
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class TaskManagementApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApiApplication.class, args);
	}

}
