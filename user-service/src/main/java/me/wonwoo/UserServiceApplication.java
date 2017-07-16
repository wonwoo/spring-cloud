package me.wonwoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}


@RestController
class UserRestController {

	@GetMapping("/users")
	public List<User> getUesrs() {
		return Arrays.asList(new User("wonwoo"), new User("kevin"));
	}

	@GetMapping("/users/{name}")
	public User getUser(@PathVariable String name) {
		return new User(name);
	}
}


class User {
	private String name;

	User() {

	}
	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}