package me.wonwoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(HelloProperties.class)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}

@RestController
@RefreshScope
class UserRestController {

	private final HelloProperties helloProperties;

	UserRestController(HelloProperties helloProperties) {
		this.helloProperties = helloProperties;
	}

	@GetMapping("/users")
	public List<User> getUesrs() {
		return Arrays.asList(new User("wonwoo"), new User("kevin"));
	}

	@GetMapping("/users/{name}")
	public User getUser(@PathVariable String name) {
		return new User(name);
	}


	@GetMapping("/hello")
	public String hello() {
		return helloProperties.getName();
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