package com.tms.taskmanagementapi;

import com.tms.taskmanagementapi.entity.Task;
import com.tms.taskmanagementapi.repository.TaskRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
public class TaskManagementApiApplication implements CommandLineRunner {

	@Autowired
	private TaskRepository taskRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Task> tasks = Stream.of(new Task(1L, "Task Name", "Task Description", 5, false, "Pending"),
						new Task(2L, "Task Name2", "Task Description2", 1, false, "Pending"),
						new Task(3L, "Task Name3", "Task Description3", 10, true, "Completed"),
						new Task(4L, "Thermal1", "Task Description2", 1, false, "Pending"),
						new Task(5L, "Maths2", "Task Description2", 1, false, "Pending"))
				.collect(Collectors.toList());

		taskRepository.saveAll(tasks);
	}
}
